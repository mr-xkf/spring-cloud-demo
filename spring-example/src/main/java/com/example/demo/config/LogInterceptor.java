/**
 * FileName: LogInterceptor
 * Author:   13235
 * Date:     2019/12/1 15:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.example.demo.config;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/12/1
 * @since 1.0.0
 */
@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String REQUEST_ID_KEY = "request_id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("开始进入日志拦截.....");
        MDC.put(REQUEST_ID_KEY, getRequestId(request));
        return true;
    }

    private String getRequestId(HttpServletRequest request) {
        String requestId;
        String parameter = request.getParameter(REQUEST_ID_KEY);
        String headerParamter = request.getHeader(REQUEST_ID_KEY);
        if (StringUtils.isBlank(parameter) && StringUtils.isBlank(headerParamter)) {
            requestId = IdUtil.simpleUUID();
        } else {
            requestId = StringUtils.isNotBlank(parameter) ? parameter : headerParamter;
        }

        return requestId;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) {

        log.info("日志请求完成。。。。释放资源");
        response.addHeader(REQUEST_ID_KEY, MDC.get(REQUEST_ID_KEY));
        MDC.remove(REQUEST_ID_KEY);

    }
}
