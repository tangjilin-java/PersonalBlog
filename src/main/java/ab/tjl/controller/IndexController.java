package ab.tjl.controller;

import ab.tjl.entity.Blog;
import ab.tjl.entity.PageBean;
import ab.tjl.service.BlogService;
import ab.tjl.util.PageUtil;
import ab.tjl.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:首页展示
 * @Date:Created in 15:29 2019/8/10
 * @Modified By:
 */
@Controller
public class IndexController {
    /**
     * Descrption:依赖注入
     */
    @Resource
    private BlogService blogService;

    /**
     * Descrption: 首页展示
     * @Param: [page, typeId, releaseDateStr, request]
     * @Return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping({"/index"})
    public ModelAndView index(@RequestParam(value="page",required=false)String page,
                              @RequestParam(value="typeId",required=false)String typeId,
                              @RequestParam(value="releaseDateStr",required=false)String releaseDateStr,
                              HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title","个人博客系统");
        if(StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),10);
        Map<String,Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr",releaseDateStr);

        List<Blog> list = blogService.list(map);

        StringBuffer param = new StringBuffer();
        if(StringUtil.isNotEmpty(typeId)) {
            param.append("typeId="+typeId+"&");
        }

        if(StringUtil.isNotEmpty(releaseDateStr)) {
            param.append("releaseDateStr="+releaseDateStr+"&");
        }
        mav.addObject("mainPage","foreground/blog/list.jsp");
        String pageCode = PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString());
        mav.addObject("pageCode",pageCode);
        mav.addObject("blogList",list);
        return mav;
    }
}

