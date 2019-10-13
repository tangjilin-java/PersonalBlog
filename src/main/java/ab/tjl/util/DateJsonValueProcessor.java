package ab.tjl.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:tangjilin
 * @Description:日期格式化工具类
 * @Date:Created in 15:39 2019/8/10
 * @Modified By:
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    private String format;

    public DateJsonValueProcessor(String format) {
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object arg0, JsonConfig arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if(value==null) {
            return "";
        }
        if(value instanceof Timestamp) {
            String str = new SimpleDateFormat(this.format).format((Timestamp)value);
            return str;
        }
        if(value instanceof Date) {
            String str = new SimpleDateFormat(this.format).format((Date)value);
            return str;
        }

        return value.toString();
    }
}
