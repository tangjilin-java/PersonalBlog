package ab.tjl.service.impl;

import ab.tjl.dao.LinkDao;
import ab.tjl.entity.Link;
import ab.tjl.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:业务层：友情链接相关接口实现类
 * @Date:Created in 15:02 2019/8/10
 * @Modified By:
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    /**
     * Descrption:依赖注入
     */
    @Resource
    private LinkDao linkDao;

    /**
     * Descrption: 根据id查询一条友情链接
     * @Param: [id]
     * @Return: ab.tjl.entity.Link
     */
    @Override
    public Link findById(Integer id) {
        return linkDao.findById(id);
    }

    /**
     * Descrption: 不固定参数查询友情链接列表
     * @Param: [paramMap]
     * @Return: java.util.List<ab.tjl.entity.Link>
     */
    @Override
    public List<Link> list(Map<String, Object> paramMap) {
        return linkDao.list(paramMap);
    }

    /**
     * Descrption: 不固定参数查询友情链接数
     * @Param: [paramMap]
     * @Return: java.lang.Long
     */
    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return linkDao.getTotal(paramMap);
    }

    /**
     * Descrption: 添加一条友情链接
     * @Param: [link]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer add(Link link) {
        return linkDao.add(link);
    }

    /**
     * Descrption: 修改一条友情链接
     * @Param: [link]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer update(Link link) {
        return linkDao.update(link);
    }

    /**
     * Descrption: 删除一条友情链接
     * @Param: [id]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer delete(Integer id) {
        return linkDao.delete(id);
    }
}
