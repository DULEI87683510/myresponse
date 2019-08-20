package handler;


import annotation.MyResponse;
import annotation.MyResult;
import annotation.MyResultCode;
import annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 *@ClassName ResponseResultHandler
 *@Description TODO
 *@Author DL
 *@Date 2019/6/25 9:14    
 *@Version 1.0
 */
@ControllerAdvice
@ConditionalOnBean({ResponseResultInterceptor.class})
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseResultHandler.class);
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        MyResponse response= (MyResponse)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getAttribute("RESPONSE-RESULT");
        return response!=null;
        //return false;
    }


    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MyResponse myResponse = (MyResponse)httpRequest.getAttribute("RESPONSE-RESULT");
        Object objBuffer = null;

        try {
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().setHeader("requestId", httpRequest.getHeader("requestId"));
            Result result = (Result)myResponse.value().newInstance();
            result.setCode(MyResultCode.SUCCESS.code());
            result.setMsg(MyResultCode.SUCCESS.message());
            result.setData(o);
            if (!(o instanceof String) && !aClass.isAssignableFrom(StringHttpMessageConverter.class)) {
                if (o instanceof Result) {
                    objBuffer = o;
                } else {
                    objBuffer = result;
                }
            } else {

                objBuffer = result.toJson();
            }
        } catch (IllegalAccessException | InstantiationException  var11) {
            objBuffer = new MyResult(MyResultCode.SYSTEM_INNER_ERROR);
            LOGGER.error("BeforeBodyWrite got failed!", var11);
        }

        return objBuffer;
    }

}
