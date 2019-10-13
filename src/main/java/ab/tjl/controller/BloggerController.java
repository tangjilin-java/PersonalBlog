package ab.tjl.controller;

import ab.tjl.entity.Blogger;
import ab.tjl.service.BloggerService;
import ab.tjl.util.CryptographyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:tangjilin
 * @Description:控制层：前台博主
 * @Date:Created in 15:29 2019/8/10
 * @Modified By:
 */

@Controller
@RequestMapping("/blogger")
public class BloggerController {
    /**
     * Descrption:依赖注入
     */
    @Resource
    private BloggerService bloggerService;

    /**
     * Descrption: 登录
     * @Param: [blogger, request]
     * @Return: java.lang.String
     */
    @RequestMapping("/login")
    public String login(Blogger blogger, HttpServletRequest request) {
        /**用户名*/
        String userName = blogger.getUserName();
        /**密码*/
        String password = blogger.getPassword();
        String pw = CryptographyUtil.md5(password, "java1234");

        /**Subject*/
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,pw);
        try {
            //传递token给shiro的realm
            subject.login(token);
            return "redirect:/admin/main.jsp";
        }catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            request.setAttribute("erroInfo", "用户名或密码错误！");
        }

        return "login";
    }

    /**
     * Descrption:关于博主
     * @Param: []
     * @Return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/aboutMe")
    public ModelAndView aboutMe() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("blogger",bloggerService.find());
        mav.addObject("mainPage","foreground/blogger/info.jsp");
        mav.addObject("title","关于博主_个人博客系统");
        mav.setViewName("index");
        return mav;
    }
}

