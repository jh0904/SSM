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
public class HandlerInterceptor1 implements HandlerInterceptor {
    /**进入handler之前执行
     * 身份认证，身份授权；认证不通过，表示当前用户没有登录此方法不在往下执行。
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        System.out.println ("handler1------------>preHandle");
        //false不向下放行
        return true;
    }
    /**进入handler之后，返回ModelAndView之前执行
     * 应用从ModelAndView出发，将公用模型（菜单导航）传到视图，统一指定视图，
     * */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println ("handler1------------>postHandle");
    }
    //执行Handler完成执行此方法
    /**
     * 统一的异常处理，通过一的日志处理。
     * */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println ("handler1------------>afterCompletion");
    }
}
