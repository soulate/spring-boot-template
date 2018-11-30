package com.template.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageSourceConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        //SessionLocaleResolver slr = new SessionLocaleResolver();
        //slr.setDefaultLocale(Locale.KOREAN);
        //return slr ;

        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.KOREA);
        clr.setCookieName("lang");
        clr.setCookieMaxAge(60*60*24); // 1Day
        return clr;
    }

    @Bean
    public LocaleChangeInterceptor LocaleChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci ;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LocaleChangeInterceptor());
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("classpath:/i18n/messages");
        //messageSource.setCacheSeconds(3600); //refresh cache once per hour
        return messageSource;
    }
}
