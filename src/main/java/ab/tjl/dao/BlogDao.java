package ab.tjl.dao;

import ab.tjl.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:持久层接口：博客
 * @Date:Created in 15:19 2019/8/10
 * @Modified By:
 */
public interface BlogDao {
    /**
     * Descrption:无参数查询博客列表(供首页使用)
     * @Param: []
     * @Return: java.util.List<ab.tjl.entity.Blog>
     */
    public List<Blog> countList();

    /**
     * Descrption: 带参数查询博客列表
     * @Param: [map]
     * @Return: java.util.List<ab.tjl.entity.Blog>
     */
    public List<Blog> list(Map<String, Object> map);

    /**
     * Descrption: 带参数查询博客数量
     * @Param: [map]
     * @Return: java.lang.Long
     */
    public Long getTotal(Map<String, Object> map);

    /**
     * Descrption: 根据主键查询一条博客信息
     * @Param: [id]
     * @Return: ab.tjl.entity.Blog
     */
    public Blog findById(Integer id);

    /**
     * Descrption: 添加一条博客
     * @Param: [blog]
     * @Return: java.lang.Integer
     */
    public Integer add(Blog blog);

    /**
     * Descrption: 修改一条博客
     * @Param: [blog]
     * @Return: java.lang.Integer
     */
    public Integer update(Blog blog);

    /**
     * Descrption: 根据主键删除一条博客
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    public Integer delete(Integer id);

    /**
     * Descrption: 根据类型查询博客数量
     * @Param: [typeId]
     * @Return: java.lang.Integer
     */
    public Integer getBlogByTypeId(Integer typeId);

    /**
     * Descrption: 上一篇
     * @Param: [id]
     * @Return: ab.tjl.entity.Blog
     */
    public Blog getLastBlog(Integer id);

    /**
     * Descrption: 下一篇
     * @Param: [id]
     * @Return: ab.tjl.entity.Blog
     */
    public Blog getNextBlog(Integer id);
}
