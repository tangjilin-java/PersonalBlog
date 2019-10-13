package ab.tjl.dao;

import ab.tjl.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:持久层接口：评论
 * @Date:Created in 15:19 2019/8/10
 * @Modified By:
 */
public interface CommentDao {
    /**
     * Descrption: 添加一条评论
     * @Param: [comment]
     * @Return: int
     */
    public int add(Comment comment);

    /**
     * Descrption: 更新一条评论
     * @Param: [comment]
     * @Return: int
     */
    public int update(Comment comment);

    /**
     * Descrption: 评论查询
     * @Param: [map]
     * @Return: java.util.List<ab.tjl.entity.Comment>
     */
    public List<Comment> list(Map<String, Object> map);

    /**
     * Descrption: 评论数量
     * @Param: [map]
     * @Return: java.lang.Long
     */
    public Long getTotal(Map<String, Object> map);

    /**
     * Descrption: 删除评论
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    public Integer delete(Integer id);
    /**
     * Descrption: 根据博客id删除评论
     * @Param: [blogId]
     * @Return: java.lang.Integer
     */
    public Integer deleteByBlogId(Integer blogId);
}
