package com.diao.course.servlet;

import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebApplication 缁熶竴鐨勫紓甯稿鐞�
 *
 */
@Component
public class MoocExceptionResolver implements HandlerExceptionResolver, Ordered {

    /**
     * 缁熶竴鐨勫紓甯稿鐞�
     *
     * @param request
     * @param response
     * @param handler
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {
        // 瑙嗗浘鏄剧ず涓撻棬鐨勯敊璇〉
        ModelAndView modelAndView = new ModelAndView();
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
        if (httpStatus.is5xxServerError()) {
            //鍦ㄦ湇鍔＄鍙戠敓寮傚父鐨勬儏鍐碉紝杩斿洖error瑙嗗浘
            modelAndView.setViewName("error");
            if (e.getMessage() == null) {
                modelAndView.addObject("msg", "鏈嶅姟鍣ㄥ彂鐢熷紓甯�");
            } else {
                modelAndView.addObject("msg", e.getMessage());
            }
        } else {
            //鍏朵綑鎯呭喌锛堜竴鑸负4XX锛夛紝杩斿洖404瑙嗗浘
            modelAndView.setViewName("404");
        }
        return modelAndView;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
