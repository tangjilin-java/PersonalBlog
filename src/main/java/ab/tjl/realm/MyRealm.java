package ab.tjl.realm;

import ab.tjl.entity.Blogger;
import ab.tjl.service.BloggerService;
import ab.tjl.util.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author:tangjilin
 * @Description:验证用户登录
 * @Date:Created in 15:58 2019/8/11
 * @Modified By:
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private BloggerService bloggerService;

    /**
     * Descrption: 获取授权信息
     * @Param: [principals]
     * @Return: org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Descrption: 登陆验证
     *             token:令牌，基于用户名密码的令牌
     * @Param: [token]
     * @Return: org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从令牌中取出用户名
        String userName = (String)token.getPrincipal();
        //让Shiro去验证账号密码
        Blogger blogger = bloggerService.getByUserName(userName);
        if(blogger!=null) {
            SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, blogger);
            AuthenticationInfo authenInfo = new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(),getName());
            return authenInfo;
        }
        return null;
    }
}
