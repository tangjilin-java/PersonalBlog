package ab.tjl.controller.admin;

import ab.tjl.entity.Link;
import ab.tjl.entity.PageBean;
import ab.tjl.service.LinkService;
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
 * @Description:控制层：友情链接
 * @Date:Created in 15:31 2019/8/10
 * @Modified By:
 */
@Controller
@RequestMapping({"/admin/link"})
public class LinkAdminController {
    /**
     * Descrption:依赖注入
     */
    @Resource
    private LinkService linkService;

    /**
      * Descrption: 友情链接列表
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
        //查询友情链接列表
        List<Link> linkList = linkService.list(map);
        //查询总共有多少条数据
        Long total = linkService.getTotal(map);
        //将数据写入response
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * Descrption: 保存友情链接信息
     * @Param: [link, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/save"})
    public String save(Link link, HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        //添加
        if(link.getId()==null) {
            resultTotal = linkService.add(link).intValue();
        }else {
            //更新
            resultTotal = linkService.update(link).intValue();
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
     * Descrption: 删除友情链接
     * @Param: [ids, response]
     * @Return: java.lang.String
     */
    @RequestMapping({"/delete"})
    public String delete(@RequestParam("ids")String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for(int i =0;i<idsStr.length;i++) {
            linkService.delete(Integer.valueOf(idsStr[i]));
        }
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}

