package handler;


import annotation.MyException;
import annotation.MyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 *@ClassName GlobalExceptionHandlerImpl
 *@Description TODO
 *@Author DL
 *@Date 2019/6/26 15:51    
 *@Version 1.0
 */
@RestControllerAdvice
@ConditionalOnBean({ResponseResultInterceptor.class})
public class GlobalExceptionHandlerImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerImpl.class);

    @ExceptionHandler(value = {MyException.class})
    public MyResult<Object> handleSaeException(MyException myException, HttpServletRequest request) {

        LOGGER.error(myException.getCode(),myException.getMsg());
        return new MyResult(myException.getCode(), myException.getMsg(), (Object)null);
    }
}
