package ab.tjl.controller.admin;

import ab.tjl.entity.BlogType;
import ab.tjl.entity.PageBean;
import ab.tjl.service.BlogService;
import ab.tjl.service.BlogTypeService;
import ab.tjl.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:tangjilin
 * @Description:控制层：博客类型
 * @Date:Created in 15:31 2019/8/10
 * @Modified By:
 */
@Controller
@RequestMapping({"/admin/blogType"})
public class BlogTypeAdminController {
    /**
     * Descrption:注入依赖
     */
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private BlogService blogService;
    /**
     * Descrption: 博客类型列表
     * @Param: [page, rows, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/list"})
    public String list(@RequestParam(value="page",required=false)String page,
                       @RequestParam(value="rows",required=false)String rows,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        //查询博客类型列表
        List<BlogType> blogTypeList = blogTypeService.list(map);
        //查询总共有多少条数据
        Long total = blogTypeService.getTotal(map);
        //将数据写入response
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * Descrption: 保存博客类别信息
     * @Param: [blogType, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/save"})
    public String save(BlogType blogType,HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        //添加
        if(blogType.getId()==null) {
            resultTotal = blogTypeService.add(blogType).intValue();
        }else {
            //更新
            resultTotal = blogTypeService.update(blogType).intValue();
        }

        JSONObject result = new JSONObject();
        if(resultTotal>0) {
            result.put("success", Boolean.valueOf(true));
        }else {
            result.put("success", Boolean.valueOf(false));
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * Descrption: 删除博客类型
     * @Param: [ids, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/delete"})
    public String delete(@RequestParam("ids")String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for(int i =0;i<idsStr.length;i++) {
            int sum = blogService.getBlogByTypeId(Integer.valueOf(idsStr[i]));
            if(sum>0) {
                result.put("exist", "博客类别下有博客，不能删除");
            }else {
                blogTypeService.delete(Integer.valueOf(idsStr[i]));
            }
        }
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}
