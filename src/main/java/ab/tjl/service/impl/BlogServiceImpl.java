package ab.tjl.service.impl;

import ab.tjl.dao.BlogDao;
import ab.tjl.dao.CommentDao;
import ab.tjl.entity.Blog;
import ab.tjl.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:业务层：博客相关接口实现类
 * @Date:Created in 15:02 2019/8/10
 * @Modified By:
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
    /**
     * Descrption: 注入依赖
     */
    @Resource
    private BlogDao blogDao;
    @Resource
    private CommentDao commentDao;

    /**
     * Descrption:无参数查询博客列表(供首页使用)
     * @Param: []
     * @Return: java.util.List<ab.tjl.entity.Blog>
     */
    @Override
    public List<Blog> countList() {
        return blogDao.countList();
    }

    /**
     * Descrption: 带参数查询博客列表
     * @Param: [map]
     * @Return: java.util.List<ab.tjl.entity.Blog>
     */
    @Override
    public List<Blog> list(Map<String, Object> map) {
        return blogDao.list(map);
    }

    /**
     * Descrption: 带参数查询博客数量
     * @Param: [map]
     * @Return: java.lang.Long
     */
    @Override
    public Long getTotal(Map<String, Object> map) {
        return blogDao.getTotal(map);
    }

    /**
     * Descrption: 根据主键查询一条博客信息
     * @Param: [id]
     * @Return: ab.tjl.entity.Blog
     */
    @Override
    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    /**
     * Descrption: 添加一条博客
     * @Param: [blog]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer add(Blog blog) {
        return blogDao.add(blog);
    }

    /**
     * Descrption: 修改一条博客
     * @Param: [blog]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer update(Blog blog) {
        return blogDao.update(blog);
    }

    /**
     * Descrption: 根据主键删除一条博客
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer delete(Integer id) {
        commentDao.deleteByBlogId(id);
        return blogDao.delete(id);
    }

    /**
     * Descrption: 根据类型查询博客数量
     * @Param: [typeId]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer getBlogByTypeId(Integer typeId) {
        return blogDao.getBlogByTypeId(typeId);
    }

    /**
     * Descrption: 上一篇
     * @Param: [id]
     * @Return: ab.tjl.entity.Blog
     */
    @Override
    public Blog getLastBlog(Integer id) {
        return blogDao.getLastBlog(id);
    }

    /**
     * Descrption: 下一篇
     * @Param: [id]
     * @Return: ab.tjl.entity.Blog
     */
    @Override
    public Blog getNextBlog(Integer id) {
        return blogDao.getNextBlog(id);
    }

}