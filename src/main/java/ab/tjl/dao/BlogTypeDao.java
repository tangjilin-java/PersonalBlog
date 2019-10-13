package ab.tjl.dao;

import ab.tjl.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:持久层接口：博客类型
 * @Date:Created in 15:19 2019/8/10
 * @Modified By:
 */
public interface BlogTypeDao {
    /**
     * Descrption: 无参数查询所有博客类型列表
     * @Param: []
     * @Return: java.util.List<ab.tjl.entity.BlogType>
     */
    public List<BlogType> countList();

    /**
     * Descrption: 根据id查询一条博客类型
     * @Param: [id]
     * @Return: ab.tjl.entity.BlogType
     */
    public BlogType findById(Integer id);

    /**
     * Descrption: 不固定参数查询博客类型列表
     * @Param: [paramMap]
     * @Return: java.util.List<ab.tjl.entity.BlogType>
     */
    public List<BlogType> list(Map<String, Object> paramMap);

    /**
     * Descrption: 不固定参数查询博客类型数
     * @Param: [paramMap]
     * @Return: java.lang.Long
     */
    public Long getTotal(Map<String, Object> paramMap);

    /**
     * Descrption: 添加一条博客类型
     * @Param: [blogType]
     * @Return: java.lang.Integer
     */
    public Integer add(BlogType blogType);

    /**
     * Descrption: 修改一条博客类型
     * @Param: [blogType]
     * @Return: java.lang.Integer
     */
    public Integer update(BlogType blogType);

    /**
     * Descrption: 删除一条博客类型
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    public Integer delete(Integer id);
}
