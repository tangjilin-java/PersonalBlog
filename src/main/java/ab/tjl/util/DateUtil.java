package ab.tjl.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:tangjilin
 * @Description:日期工具类
 * @Date:Created in 15:39 2019/8/10
 * @Modified By:
 */
public class DateUtil {
    /**
     * 得到当前到秒的时间字符串
     */
    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }

    public static String formatDate(Date date,String format) {
        String result="";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(date!=null) {
            result=sdf.format(date);
        }
        return result;
    }
}
