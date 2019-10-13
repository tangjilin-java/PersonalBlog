package ab.tjl.service.impl;

import ab.tjl.dao.BloggerDao;
import ab.tjl.entity.Blog;
import ab.tjl.entity.Blogger;
import ab.tjl.service.BlogService;
import ab.tjl.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:业务层：博主相关接口实现类
 * @Date:Created in 15:02 2019/8/10
 * @Modified By:
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    /**
     * Descrption: 注入依赖
     */
    @Resource
    private BloggerDao bloggerDao;

    /**
     * Descrption: 根据登录名查询博主对象
     * @Param: [userName]
     * @Return: ab.tjl.entity.Blogger
     */
    @Override
    public Blogger getByUserName(String userName) {
        return bloggerDao.getByUserName(userName);
    }

    /**
     * Descrption: 更新博主对象
     * @Param: [blogger]
     * @Return: java.lang.Integer
     */
    @Override
    public Integer update(Blogger blogger) {
        SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
        return bloggerDao.update(blogger);
    }

    /**
     * Descrption:查询博主
     * @Param: []
     * @Return: ab.tjl.entity.Blogger
     */
    @Override
    public Blogger find() {
        return bloggerDao.find();
    }

}
