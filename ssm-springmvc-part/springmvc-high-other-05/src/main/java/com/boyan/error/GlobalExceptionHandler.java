package com.boyan.error;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 *  @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 *                          @ControllerAdvice 代表当前类的异常处理controller!
 *
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常处理handler
     * @ExceptionHandler(HttpMessageNotReadableException.class)
     * 该注解标记异常处理Handler,并且指定发生异常调用该方法!
     *
     *
     * @param e 获取异常对象!
     * @return 返回handler处理结果!
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handlerJsonDateException(HttpMessageNotReadableException e){

        return null;
    }

    /**
     * 当发生空指针异常会触发此方法!
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public Object handlerNullException(NullPointerException e){

        return null;
    }

    /**
     * 所有异常都会触发此方法!但是如果有具体的异常处理Handler!
     * 具体异常处理Handler优先级更高!
     * 例如: 发生NullPointerException异常!
     *       会触发handlerNullException方法,不会触发handlerException方法!
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){

        return null;
    }
}
