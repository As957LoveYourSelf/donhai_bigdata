package com.zcq.travelweb.Configures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StopWatchHandlerInterceptor implements HandlerInterceptor {
    private NamedThreadLocal<Long> startTime = new NamedThreadLocal<>("StopWatch-StartTime");
    private Logger logger = LoggerFactory.getLogger(StopWatchHandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTime.set(beginTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = startTime.get();
        long consumeTime = endTime - beginTime;
        if (consumeTime > 500){
            logger.info(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
        }
    }
}
