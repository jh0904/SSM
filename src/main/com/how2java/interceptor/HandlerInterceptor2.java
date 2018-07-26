package com.how2java.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.how2java.interceptor
 *
 * @author jh
 * @date 2018/7/25 9:54
 * description:测试的拦截器1
 */
public class HandlerInterceptor2 implements HandlerInterceptor {
    /**进入handler之前执行
     * 身份认证，身份授权；认证不通过，表示当前用户没有登录此方法不在往下执行。
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println ("handler2------------>preHandle");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println ("handler2------------>postHandle");
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println ("handler2------------>afterCompletion");
    }
}
