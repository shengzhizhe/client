package org.client.com;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.client.com.util.base64.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Created by pangkunkun on 2017/11/18.
 */
public class MyAccessControlFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(MyAccessControlFilter.class);


    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     * (感觉这里应该是对白名单（不需要登录的接口）放行的)
     * 如果isAccessAllowed返回true则onAccessDenied方法不会继续执行
     * 这里可以用来判断一些不被通过的链接（个人备注）
     * * 表示是否允许访问 ，如果允许访问返回true，否则false；
     *
     * @param servletRequest
     * @param servletResponse
     * @param object          表示写在拦截器中括号里面的字符串 mappedValue 就是 [urls] 配置中拦截器参数部分
     * @return
     * @throws Exception
     */
    @Override
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object object) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String url = getPathWithinApplication(servletRequest);
        log.info("当前用户正在访问的 url => " + url);
        log.info("subject.isPermitted(url);" + subject.isPermitted(url));
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     * onAccessDenied是否执行取决于isAccessAllowed的值，如果返回true则onAccessDenied不会执行；如果返回false，执行onAccessDenied
     * 如果onAccessDenied也返回false，则直接返回，不会进入请求的方法（只有isAccessAllowed和onAccessDenied的情况下）
     */
    @Override
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String username = request.getParameter("crsKey");
        String signature = request.getParameter("signature");
        String type = request.getParameter("type");
        type = type == null ? "user" : type;

        if (username == null && signature == null) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String token = httpServletRequest.getHeader("token");
            if (token != null && !token.isEmpty()) {
                username = token.split(".")[0];
                type = token.split(".")[1];
                signature = token.split(".")[2];
            }
        }
        signature = Base64Util.encode(signature);

//验证用户和令牌的有效性
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(username, type, signature);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            log.info("登陆失败");
            log.info(e.getMessage());
            onLoginFail(response);
            return false;
        }
        log.info("登陆成功");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("token", Base64Util.encode(username) + "." + Base64Util.encode(type) + "." + signature);
        return true;
    }

    /**
     * 登录失败
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        log.info("设置返回");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }

    /**
     * TODO 跨域请求
     */
    private void dealCrossDomain() {

    }
}
