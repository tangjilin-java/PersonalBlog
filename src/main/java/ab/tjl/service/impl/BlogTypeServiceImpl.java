package ab.tjl.service.impl;

import ab.tjl.dao.BlogTypeDao;
import ab.tjl.entity.BlogType;
import ab.tjl.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:业务层：博客类型相关接口实现类
 * @Date:Created in 15:02 2019/8/10
 * @Modified By:
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {
    /**
     * Descrption: 注入依赖
     */
    @Resource
    private BlogTypeDao blogTypeDao;

    /**
     * Descrption: 无参数查询所有博客类型列表
     * @Param: []
     * @Return: java.util.List<ab.tjl.entity.BlogType>
     */
    @Override
    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }

    /**
     * Descrption: 根据id查询一条博客类型
     * @Param: [id]
     * @Return: ab.tjl.entity.BlogType
     */
    @Override
    public BlogType findById(Integer id) {
        return blogTypeDao.findById(id);
    }

    /**
     * Descrption: 不固定参数查询博客类型列表
     * @Param: [paramMap]
     * @Return: java.util.List<ab.tjl.entity.BlogType>
     */
    @Override
    public List<BlogType> list(Map<String, Object> paramMap) {
        return blogTypeDao.list(paramMap);
    }

    /**
     * Descrption: 不固定参数查询博客类型数
     * @Param: [paramMap]
     * @Return: java.lang.Long
     */
    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return blogTypeDao.getTotal(paramMap);
    }

    /**
     * Descrption: 添加一条博客类型
     * @Param: [blogType]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }

    /**
     * Descrption: 修改一条博客类型
     * @Param: [blogType]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }

    /**
     * Descrption: 删除一条博客类型
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }

}

