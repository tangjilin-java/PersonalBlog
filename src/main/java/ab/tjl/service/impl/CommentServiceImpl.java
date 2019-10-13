package ab.tjl.service.impl;

import ab.tjl.dao.CommentDao;
import ab.tjl.entity.Comment;
import ab.tjl.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:业务层：评论相关接口实现类
 * @Date:Created in 15:02 2019/8/10
 * @Modified By:
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    /**
     * Descrption:依赖注入
     */
    @Resource
    private CommentDao commentDao;

    /**
     * Descrption: 添加一条评论
     * @Param: [comment]
     * @Return: int
     */
    @Override
    public int add(Comment comment) {
        return commentDao.add(comment);
    }

    /**
     * Descrption: 更新一条评论
     * @Param: [comment]
     * @Return: int
     */
    @Override
    public int update(Comment comment) {
        return commentDao.update(comment);
    }

    /**
     * Descrption: 评论查询
     * @Param: [map]
     * @Return: java.util.List<ab.tjl.entity.Comment>
     */
    @Override
    public List<Comment> list(Map<String, Object> map) {
        return commentDao.list(map);
    }

    /**
     * Descrption: 评论数量
     * @Param: [map]
     * @Return: java.lang.Long
     */
    @Override
    public Long getTotal(Map<String, Object> map) {
        return commentDao.getTotal(map);
    }

    /**
     * Descrption: 删除评论
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer delete(Integer id) {
        return commentDao.delete(id);
    }

}
