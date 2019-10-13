package ab.tjl.controller;

import ab.tjl.entity.Blog;
import ab.tjl.entity.Comment;
import ab.tjl.service.BlogService;
import ab.tjl.service.CommentService;
import ab.tjl.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author:tangjilin
 * @Description:控制层：评论
 * @Date:Created in 15:29 2019/8/10
 * @Modified By:
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    /**
     * Descrption:依赖注入
     */
    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;

    /**
     * Descrption: 提交评论
     * @Param: [comment, imageCode, request, response, session]
     * @Return: java.lang.String
     */
    @RequestMapping("/save")
    public String save(Comment comment, @RequestParam("imageCode")String imageCode,
                       HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0;
        if(!imageCode.equals(sRand)) {
            result.put("success", Boolean.FALSE);
            result.put("erroInfo", "验证码填写错误！");
        }else {
            String userIp = request.getRemoteAddr();
            comment.setUserIp(userIp);
            if(comment.getId()==null) {
                resultTotal = commentService.add(comment);

                //给对应的博客评论数加1
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit()+1);
                blogService.update(blog);
            }
        }

        if(resultTotal>0) {
            result.put("success",Boolean.TRUE);
        }else {
            result.put("success",Boolean.FALSE);
        }

        ResponseUtil.write(response, result);
        return null;

    }
}

