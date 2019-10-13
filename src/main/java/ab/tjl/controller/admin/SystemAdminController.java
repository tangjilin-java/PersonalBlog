package ab.tjl.controller.admin;

import ab.tjl.entity.Blog;
import ab.tjl.entity.BlogType;
import ab.tjl.entity.Blogger;
import ab.tjl.entity.Link;
import ab.tjl.service.BlogService;
import ab.tjl.service.BlogTypeService;
import ab.tjl.service.BloggerService;
import ab.tjl.service.LinkService;
import ab.tjl.util.Const;
import ab.tjl.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author:tangjilin
 * @Description:控制层：系统缓存
 * @Date:Created in 15:31 2019/8/10
 * @Modified By:
 */
@Controller
@RequestMapping({"/admin/system"})
public class SystemAdminController {
    /**
     * Descrption:依赖注入
     */
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private BloggerService bloggerService;
    @Resource
    private BlogService blogService;
    @Resource
    private LinkService linkService;

    /**
     * Descrption: 刷新系统缓存
     * @Param: [request, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/refreshSystem"})
    public String refreshSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();
        //博客类别
        List<BlogType> list = blogTypeService.countList();
        application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, list);

        //博主信息
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        application.setAttribute(Const.BLOGGER, blogger);

        //按年月分类的博客数量
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);

        //友情链接
        List<Link> linkList = linkService.list(null);
        application.setAttribute(Const.LINK_LIST,linkList);

        JSONObject result = new JSONObject();
        result.put("success", Boolean.TRUE);
        ResponseUtil.write(response, result);
        return null;
    }
}

