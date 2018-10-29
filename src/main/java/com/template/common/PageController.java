package com.template.common;

import com.template.common.config.AppConfig;
import com.template.user.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class PageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppConfig appConfig;

    @Autowired
    MessageSource messageSource;

    @RequestMapping("/testException")
    public String error(Model model, Locale locale){

        int i = 0;

        if(i==0)throw new RuntimeException("Test Exception....... ");

        return "error";
    }

    @RequestMapping("/")
    public String main(HttpServletRequest request, Model model, Locale locale){
        //System.err.println("Index..............");

        model.addAttribute("msg","Index Hello ... ");

        logger.info("value ::: " + appConfig.getValue());

        logger.error( " language ::: " + LocaleContextHolder.getLocale().getLanguage() );

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        logger.error( locale.getLanguage() + " , " + messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale ));

        String message = messageSource.getMessage("login.failed.msg", null, locale );

        try{

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("name",user.getUsername());
            logger.error("LOGIN USER INFO >>>>>>>>>>>>>>> "+user);

        }catch(Exception e){

        }
        return "index";
    }

    @RequestMapping("/{depth1}")
    public String main(HttpServletRequest request, @PathVariable String depth1, Model model, Locale locale){

        /*\
        if("login".equals(depth1)){
            logger.info("Login........................ ");
            String referer = request.getHeader("Referer");
            request.getSession().setAttribute("prevPage", referer);
        }
        */

        logger.info("Depth1.................");
        model.addAttribute("msg","Depth1 Hello ... ");
        return depth1;

    }

    @RequestMapping("/{depth1}/{depth2}")
    public String main(@PathVariable String depth1, @PathVariable String depth2, Model model, Locale locale){
        logger.info("Depth2....................");

        return depth1 + "/" + depth2;
    }
}
