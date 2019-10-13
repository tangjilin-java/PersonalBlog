package ab.tjl.controller.admin;

import ab.tjl.entity.Blog;
import ab.tjl.entity.PageBean;
import ab.tjl.lucene.BlogIndex;
import ab.tjl.service.BlogService;
import ab.tjl.util.DateJsonValueProcessor;
import ab.tjl.util.ResponseUtil;
import ab.tjl.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:控制层：后台博客
 * @Date:Created in 15:31 2019/8/10
 * @Modified By:
 */
@Controller
@RequestMapping({"/admin/blog"})
public class BlogAdminController {
    @Resource
    private BlogService blogService;
    private BlogIndex blogIndex = new BlogIndex();

    /**
     * Descrption: 保存一条博客信息 修改一条博客信息
     * @Param: [blog, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/save"})
    public String save(Blog blog,HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        if(blog.getId()==null) {		//添加
            resultTotal = blogService.add(blog);
            blogIndex.addIndex(blog);
        }else {				//修改
            resultTotal = blogService.update(blog);
            blogIndex.updateIndex(blog);
        }

        JSONObject result = new JSONObject();
        if(resultTotal>0) {
            result.put("success",Boolean.valueOf(true));
        }else {
            result.put("success",Boolean.valueOf(false));
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * Descrption: 查询博客信息列表
     * @Param: [page, rows, blog, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/list"})
    public String list(@RequestParam(value="page",required=false)String page,
                       @RequestParam(value="rows",required=false)String rows,Blog blog,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        map.put("title", StringUtil.formatLike(blog.getTitle()));
        //分页查询博客信息列表
        List<Blog> list = blogService.list(map);
        //获取共有多少条博客信息
        Long total = blogService.getTotal(map);

        //封装到json
        JSONObject result = new JSONObject();
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(list,config);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * Descrption: 根据主键查询一条博客信息
     * @Param: [id, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"findById"})
    public String findById(@RequestParam("id")String id,HttpServletResponse response) throws Exception {
        Blog blog = blogService.findById(Integer.parseInt(id));
        JSONObject jsonObject = JSONObject.fromObject(blog);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * Descrption: 删除博客信息
     * @Param: [ids, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"delete"})
    public String delete(@RequestParam("ids")String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for(int i = 0;i<idsStr.length;i++) {
            blogService.delete(Integer.parseInt(idsStr[i]));
            blogIndex.deleteIndex(idsStr[i]);
        }
        JSONObject result = new JSONObject();
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}

