package ab.tjl.lucene;

import ab.tjl.entity.Blog;
import ab.tjl.util.DateUtil;
import ab.tjl.util.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:tangjilin
 * @Description:使用lucene对博客进行增删改查
 * @Date:Created in 15:58 2019/8/11
 * @Modified By:
 */
public class BlogIndex {
    private Directory dir = null;
    private String lucenePath = "C://lucene";

    /**
     * Descrption:获取对lucene的写入方法
     * @Param: []
     * @Return: org.apache.lucene.index.IndexWriter
     */
    private IndexWriter getWriter() throws Exception {
        dir = FSDirectory.open(Paths.get(lucenePath, new String[0]));
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir,iwc);
        writer.commit();
        return writer;
    }

    /**
     * Descrption: 增加索引
     * @Param: [blog]
     * @Return: void
     */
    public void addIndex(Blog blog) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id",String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(),"yyyy-MM-dd"),Field.Store.YES));
        doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));//不带HTML格式
        doc.add(new TextField("keyWord",blog.getKeyWord(),Field.Store.YES));
        writer.addDocument(doc);
        writer.commit();
        writer.close();
    }

    /**
     * Descrption: 更新索引
     * @Param: [blog]
     * @Return: void
     */
    public void updateIndex(Blog blog) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
        doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
        doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(),"yyyy-MM-dd"),Field.Store.YES));
        doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));//不带HTML格式
        doc.add(new TextField("keyWord",blog.getKeyWord(),Field.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(blog.getId())),doc);
        writer.commit();
        writer.close();
    }

    /**
     * Descrption: 删除索引
     * @Param: [blogId]
     * @Return: void
     */
    public void deleteIndex(String blogId) throws Exception {
        IndexWriter writer = getWriter();
        writer.deleteDocuments(new Term[] {new Term("id",blogId)});
        writer.forceMergeDeletes();
        writer.commit();
        writer.close();
    }

    /**
     * Descrption:搜索索引
     * @Param: [q]
     * @Return: java.util.List<ab.tjl.entity.Blog>
     */
    public List<Blog> searchBlog(String q) throws Exception{
        List<Blog> blogList = new LinkedList<>();
        dir = FSDirectory.open(Paths.get(lucenePath, new String[0]));
        //获取reader
        IndexReader reader = DirectoryReader.open(dir);
        //获取流
        IndexSearcher is = new IndexSearcher(reader);
        //放入查询条件
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();//支持中文
        QueryParser parser = new QueryParser("title",analyzer);
        Query query = parser.parse(q);//把条件装载进Lucene
        QueryParser parser2 = new QueryParser("content",analyzer);
        Query query2 = parser2.parse(q);
        QueryParser parser3 = new QueryParser("keyWord",analyzer);
        Query query3 = parser3.parse(q);

        /**
         * Descrption: BooleanClause.Occur.SHOULD 这是一个枚举 里面还有几个属性SHOULD只是其中之一
         *                     这里的SHOULD表示或者有这个条件即可 意思就是下面三个条件满足其中一个即可查到数据
         */
        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query3, BooleanClause.Occur.SHOULD);
        //最多返回100条数据
        TopDocs hits = is.search(booleanQuery.build(), 100);

        //高亮显示搜索的字
        QueryScorer scorer = new QueryScorer(query);//得到搜索字
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);//格式化
        //高亮显示的格式为红色加粗
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);//高亮显示
        highlighter.setTextFragmenter(fragmenter);

        //遍历查询结果，放入blogList
        for(ScoreDoc scoreDoc:hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            Blog blog = new Blog();

            blog.setId(Integer.parseInt(doc.get("id")));
            blog.setReleaseDateStr(doc.get("releaseDate"));
            String title = doc.get("title");
            String content = StringEscapeUtils.escapeHtml(doc.get("content"));//因为内容是HTML格式的所以需要转换
            String keyWord = doc.get("keyWord");

            //对title进行高亮显示
            if(title!=null) {
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if(StringUtil.isEmpty(hTitle)) {
                    blog.setTitle(title);
                }else {
                    blog.setTitle(hTitle);
                }
            }

            //对content进行高亮显示
            if(content!=null) {
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if(StringUtil.isEmpty(hContent)) {
                    if(content.length()<=200) {
                        blog.setContent(content);
                    }else {
                        blog.setContent(content.substring(0, 200));//对搜索内容长度进行限制
                    }
                }else {
                    blog.setContent(hContent);
                }
            }

            //对keyWord进行高亮显示
            if(keyWord!=null) {
                TokenStream tokenStream = analyzer.tokenStream("keyWord", new StringReader(keyWord));
                String hKeyWord = highlighter.getBestFragment(tokenStream, keyWord);
                if(StringUtil.isEmpty(hKeyWord)) {
                    blog.setKeyWord(keyWord);
                }else {
                    blog.setKeyWord(hKeyWord);
                }
            }
            blogList.add(blog);
        }
        return blogList;
    }

}

