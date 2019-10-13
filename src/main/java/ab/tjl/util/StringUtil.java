package ab.tjl.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:tangjilin
 * @Description:操作字符串的工具类
 * @Date:Created in 15:39 2019/8/10
 * @Modified By:
 */
public class StringUtil {
    /**
     * Descrption: 在字符串前后加%
     * @Param: [str]
     * @Return: java.lang.String
     */
    public static String formatLike(String str) {
        if(isNotEmpty(str)) {
            return "%"+str+"%";
        }
        return null;
    }

    /**
     * Descrption: 判断字符串是否不为空
     * @Param: [str]
     * @Return: boolean
     */
    public static boolean isNotEmpty(String str) {
        if(str!=null&&!"".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * Descrption: 判断字符串是否为空
     * @Param: [str]
     * @Return: boolean
     */
    public static boolean isEmpty(String str) {
        if(str==null||"".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     *
     */
    public static List<String> filterWhite(List<String> list){
        List<String> resultList = new ArrayList<>();
        for(String l:list) {
            if(isNotEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }
}
