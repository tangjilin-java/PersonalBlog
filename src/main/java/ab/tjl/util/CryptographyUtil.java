package ab.tjl.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author:tangjilin
 * @Description:md5加密工具类
 * @Date:Created in 15:39 2019/8/10
 * @Modified By:
 */
public class CryptographyUtil {
    /**
     * md5加密
     */
    public static String md5(String str,String salt) {
        return new Md5Hash(str,salt).toString();
    }


    public static void main(String[] args) {
        System.out.println(md5("12345678","java1234"));
    }

}
