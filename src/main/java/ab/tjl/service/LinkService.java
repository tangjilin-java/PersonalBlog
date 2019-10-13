package ab.tjl.service;

import ab.tjl.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:业务层接口：友情链接相关接口
 * @Date:Created in 14:52 2019/8/10
 * @Modified By:
 */
public interface LinkService {
    /**
     * Descrption: 根据id查询一条友情链接
     * @Param: [id]
     * @Return: ab.tjl.entity.Link
     */
    public Link findById(Integer id);

    /**
     * Descrption: 不固定参数查询友情链接列表
     * @Param: [paramMap]
     * @Return: java.util.List<ab.tjl.entity.Link>
     */
    public List<Link> list(Map<String, Object> paramMap);

    /**
     * Descrption: 不固定参数查询友情链接数
     * @Param: [paramMap]
     * @Return: java.lang.Long
     */
    public Long getTotal(Map<String, Object> paramMap);

    /**
     * Descrption: 添加一条友情链接
     * @Param: [link]
     * @Return: java.lang.Integer
     */
    public Integer add(Link link);

    /**
     * Descrption: 修改一条友情链接
     * @Param: [link]
     * @Return: java.lang.Integer
     */
    public Integer update(Link link);

    /**
     * Descrption: 删除一条友情链接
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    public Integer delete(Integer id);
}
