package com.template.common;

import com.template.common.util.InvalidParameterException;
import com.template.common.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;

@ControllerAdvice
public class ErrorPageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    /*
    // %Tomcat_Home%/conf/web.xml 에서 설정.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFound(){
        System.err.println("not found..........");
        return "/error/error-404";
    }*/

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runTimeException(HttpServletRequest request, HttpServletResponse response, Exception ex){
        logger.error(" RuntimeException........ ");
        logger.error(" message >>> " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("/error/error-500");
        modelAndView.addObject("message",ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultData dataAccessException(HttpServletRequest request, HttpServletResponse response, DataAccessException ex){
        logger.error(" DataAccessException........ ");
        logger.error(" message >>> " + ex.getCause());
        return new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getCause().toString());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData bindException(HttpServletRequest request, HttpServletResponse response, BindException ex){
        logger.error(" BindException........ ");
        logger.error(" message >>> " + ex.getCause());
        return new ResultData(HttpStatus.BAD_REQUEST.value(),ex.getCause().toString());
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData invalidParameterException(HttpServletRequest request, HttpServletResponse response, InvalidParameterException ex){
        logger.error(" InvalidParameterException........ ");
        logger.error(" message >>> " + ex.getMessage());
        return new ResultData(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData unexpectedTypeException(HttpServletRequest request, HttpServletResponse response, UnexpectedTypeException ex){
        logger.error(" UnexpectedTypeException........ ");
        logger.error(" message >>> " + ex.getMessage());
        return new ResultData(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ResultData httpRequestMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response, HttpRequestMethodNotSupportedException ex) {
        logger.error(" HttpRequestMethodNotSupportedException........ ");
        logger.error(" message >>> " + ex.getMessage());
        return new ResultData(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
    }
}
