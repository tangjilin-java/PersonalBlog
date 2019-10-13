package ab.tjl.service.impl;

import ab.tjl.entity.Blog;
import ab.tjl.entity.BlogType;
import ab.tjl.entity.Blogger;
import ab.tjl.entity.Link;
import ab.tjl.service.BlogService;
import ab.tjl.service.BlogTypeService;
import ab.tjl.service.BloggerService;
import ab.tjl.service.LinkService;
import ab.tjl.util.Const;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @author:tangjilin
 * @Description:配置项目启动时存入的数据，方便显示，以达到不重复占用数据库资源的目的
 * @Modified By:
 */
@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        //博客类别
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
        List<BlogType> blogTypeList = blogTypeService.countList();
        application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, blogTypeList);

        //博主信息
        BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        application.setAttribute(Const.BLOGGER, blogger);

        //按年月分类的博客数量
        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);

        //友情链接
        LinkService linkService = (LinkService) applicationContext.getBean("linkService");
        List<Link> linkList = linkService.list(null);
        application.setAttribute(Const.LINK_LIST,linkList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    }
}

