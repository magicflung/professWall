package com.profess.exception;

import com.profess.util.JSONResult;
import com.profess.util.Meta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestControllerAdvice
public class GlobalDefultExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalDefultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public JSONResult defalutErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
        return new JSONResult(Meta.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IOException.class)
    public JSONResult IOErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
        return new JSONResult(new Meta(500, "上传失败"));
    }
}
