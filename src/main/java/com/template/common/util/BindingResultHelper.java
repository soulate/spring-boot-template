package com.template.common.util;

import oracle.jdbc.proxy.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BindingResultHelper {

    private static Logger logger = LoggerFactory.getLogger(BindingResultHelper.class);

    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    public void init(){
        StaticMessageSource.setMessageSource(messageSource);
    }

    public static void invalidParameterException(BindingResult bindingResult) {

        // 개별 필드 오류 처리(ex: startDate에 20181322와 같은 값이 들어왔을 때)
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            String message = fieldErrors.stream()
                    .map(e -> String.format("%s : '%s'", e.getField(), (e.getDefaultMessage()==null)?e.getObjectName():StaticMessageSource.getMessage(e.getDefaultMessage(), null) ))
                    .collect(Collectors.joining(" , "));

            logger.error("message :::::::::: " + message);
            throw new InvalidParameterException(message);
        }

        // 개별 필드 외의 오류 처리(ex: startDate와 endDate의 차이가 90일을 넘어갈 때)
        /*
        List<ObjectError> objectErrors = bindingResult.getAllErrors();
        if (!objectErrors.isEmpty()) {
            String message = objectErrors.stream()
                    .map(e -> String.format("'%s': %s", e.getObjectName(), e.getDefaultMessage()))
                    .collect(Collectors.joining(" , "));

            logger.error("message :::::::::: " + message);
            throw new InvalidParameterException(message);
        }
        */

        logger.error("message :::::::::: " + "입력값을 확인해 주십시오.");
        throw new InvalidParameterException("입력값을 확인해 주십시오.");
    }
}
