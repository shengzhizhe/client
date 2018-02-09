package org.client.com;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.client.com.util.base64.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
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
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpServletRequest.getCookies();
        String token_str = null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("token")) {
                token_str = cookies[i].getValue();
                continue;
            }
        }
        if (token_str == null || token_str.isEmpty()) {
            onLoginFail(response, "请从新登录");
            return false;
        }
        String[] split = token_str.split("_");
        String uuid = split[0];
        String account = split[1];
        String times = split[2];
        String author = split[3];
        String type = split[4];
        String pwd = split[5];

//            密钥是否是本系统签发
        if (!"The survival of the dead".equals(Base64Util.decode(author))) {
            onLoginFail(response, "非法的密匙");
            return false;
        }
//        密钥是否过期
        long now_times = System.currentTimeMillis();
        if (times == null || times.isEmpty()) {
            onLoginFail(response, "非法的密匙");
            return false;
        }
        try {
            String s = Base64Util.decode(times);
            long my_times = Long.parseLong(s);
            if (now_times - my_times > (5000 * 60 * 60)) {
//                密钥过期
                onLoginFail(response, "登录已过期，请从新登录");
                return false;
            }
        } catch (Exception e) {
//            无法转换long，确定密钥不是合法的
            onLoginFail(response, "非法的密匙");
            return false;
        }

//验证用户和令牌的有效性(此处应该根据uuid取缓存数据然后判断令牌时候有效)
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(Base64Util.decode(account),
                Base64Util.decode(type),
                pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            log.info("令牌验证失败，令牌错误");
            log.info(e.getMessage());
            onLoginFail(response, "非法的密匙");
            return false;
        }
        log.info("令牌验证成功");
        String times2 = Base64Util.encode(String.valueOf(System.currentTimeMillis()));
        token_str = uuid + "_" + account + "_" + times2 + "_" + author + "_" + type + "_" + pwd;
        Cookie cookie = new Cookie("token", token_str);
        cookie.setPath("/");
        cookie.setMaxAge(60);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.addCookie(cookie);
        return true;
    }

    /**
     * 登录失败
     */
    private void onLoginFail(ServletResponse response, String message) throws IOException {
        log.info("设置返回");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write(message);
        httpResponse.sendRedirect("/index");
    }

    /**
     * TODO 跨域请求
     */
    private void dealCrossDomain() {

    }
}
