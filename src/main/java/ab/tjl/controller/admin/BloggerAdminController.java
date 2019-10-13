package ab.tjl.controller.admin;

import ab.tjl.entity.Blogger;
import ab.tjl.service.BloggerService;
import ab.tjl.util.Const;
import ab.tjl.util.CryptographyUtil;
import ab.tjl.util.DateUtil;
import ab.tjl.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author:tangjilin
 * @Description:控制层：后台博主
 * @Date:Created in 15:31 2019/8/10
 * @Modified By:
 */
@Controller
@RequestMapping({"/admin/blogger"})
public class BloggerAdminController {
    /**
     * Descrption:依赖注入
     */
    @Resource
    private BloggerService bloggerService;

    /**
     * Descrption: 保存修改博主信息
     * @Param: [imageFile, blogger, request, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/save"})
    public String save(@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(!imageFile.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/");
            String imageName = DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
            blogger.setImageName(imageName);
        }
        int resultTotal = bloggerService.update(blogger);

        StringBuffer result = new StringBuffer();
        if(resultTotal>0) {
            result.append("<script lauguage='javascript'>alert('修改成功');</script>");
        }else {
            result.append("<script lauguage='javascript'>alert('修改失败');</script>");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * Descrption: 获取博主的json格式
     * @Param: [response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/find"})
    public String find(HttpServletResponse response) throws Exception {
        Blogger blogger = (Blogger)SecurityUtils.getSubject().getSession().getAttribute(Const.CURRENT_USER);
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * Descrption: 修改密码
     * @Param: [id, newPassword, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/modifyPassword"})
    public String modifyPassword(@RequestParam("id")String id, @RequestParam("newPassword")String newPassword, HttpServletResponse response) throws Exception {
        Blogger blogger = new Blogger();
        blogger.setId(Integer.parseInt(id));
        blogger.setPassword(CryptographyUtil.md5(newPassword,"java1234"));
        int resultTotal = bloggerService.update(blogger);
        JSONObject result = new JSONObject();
        if(resultTotal>0) {
            result.put("success", Boolean.TRUE);
        }else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * Descrption: 用户退出
     * @Param: []
     * @Return: java.lang.String
     */
    @RequestMapping({"/logout"})
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
