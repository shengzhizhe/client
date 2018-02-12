package org.client.com;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.client.com.api.AccountInterface;
import org.client.com.api.TokenInterface;
import org.client.com.api.model.AccountModel;
import org.client.com.util.algorithm.Algorithm;
import org.client.com.util.base64.Base64Util;
import org.client.com.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 用于判断user
 */
@Configuration
public class MyShiroRealm2 extends AuthorizingRealm {

    @Autowired
    private AccountInterface anInterface;
    @Autowired
    private TokenInterface tkInterface;

    @Override
    public String getName() {
        return "user";
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        MyUsernamePasswordToken myToken = (MyUsernamePasswordToken) token;
        if (myToken.getUsername() != null && !myToken.getUsername().isEmpty()) {
            //获取用户的输入的账号.
            String username = (String) myToken.getPrincipal();
            //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
            ResponseResult<AccountModel> result = anInterface.getAccount(username);
            if (!result.isSuccess()) {
                throw new UnknownAccountException();
            }
            //此处使用的是user对象，不是username
            return new SimpleAuthenticationInfo(
                    result.getData(),
                    result.getData().getPassword(),
                    getName()
            );
        } else {
            if (myToken == null || myToken.getSignature() == null || myToken.getSignature().isEmpty()) {
                //请从新登录;
                throw new UnknownAccountException();
            }
            String[] split = myToken.getSignature().split("_");
            String tokens = split[0];
            String account = split[1];
            String author = split[2];
            String type = split[3];
            String times = split[4];

//            密钥是否是本系统签发
            String t = Base64Util.decode(times);
            String s = Base64Util.decode(author);
            s = Algorithm.en(s);
            t = "The survival of the dead " + t;
            if (!t.equals(s)) {
                //请从新登录;
                throw new UnknownAccountException();
            }
//        密钥是否过期
            long endTimes = Long.parseLong(Base64Util.decode(times));
            long now_times = System.currentTimeMillis();
            if (endTimes <= 0) {
                //请从新登录;
                throw new UnknownAccountException();
            }
            if (endTimes < now_times) {
//                密钥过期
                //请从新登录;
                throw new UnknownAccountException();
            }
//            ResponseResult<AccountModel> result1 = anInterface.getAccount(Base64Util.decode(account));
//            if (!result1.isSuccess()) {
//                请从新登录;
//                throw new UnknownAccountException();
//            }
            myToken.setUsername(Base64Util.decode(account));
            return new SimpleAuthenticationInfo(
                    myToken,
                    myToken.getSignature(),
                    getName()
            );
        }
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("user");
        return info;
    }

}