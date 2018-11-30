package com.template.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;

public class StaticMessageSource {

    private static MessageSource messageSource = null;

    private StaticMessageSource(MessageSource messageSource) {
        StaticMessageSource.messageSource = messageSource;
    }

    /**
     * @return the messageSource
     */
    public static MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * @param messageSource the messageSource to set
     */
    public static void setMessageSource(MessageSource messageSource) {
        StaticMessageSource.messageSource = messageSource;
    }

    /**
     * I18n Support
     * @param code message code
     * @return message or code if none defined
     */
    public static String getMessage(String code) {
        try {
            return messageSource == null ?
                    code : messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        }
        catch (Exception e) {
            e.printStackTrace();
            return code;
        }
    }

    /**
     * I18n Support
     * @param msr message source resolvable
     * @return message or code if none defined
     */
    public static String getMessage(MessageSourceResolvable msr) {
        return messageSource == null ?
                msr.getDefaultMessage() : messageSource.getMessage(msr, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
