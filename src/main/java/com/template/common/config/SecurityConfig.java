package com.template.common.config;

import com.template.common.enums.SocialType;
import com.template.common.util.CustomAuthenticationFailHandler;
import com.template.common.util.CustomAuthenticationSuccessHandler;
import com.template.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.template.common.enums.SocialType.GOOGLE;
import static com.template.common.enums.SocialType.FACEBOOK;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    CustomAuthenticationFailHandler customAuthenticationFailHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //허용경로.
        web.ignoring().antMatchers("/resources/**","/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable() // 개발때만 해제 할것.

                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)

                 //   .and()
                .authorizeRequests()
                    // ROLE_USER, ROLE_ADMIN으로 권한 분리 URL 정의
                    .antMatchers("/", "/login", "/error**","/locale").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/user/**").hasAuthority("USER")
                    .antMatchers("/mypage/**").hasAuthority("USER")
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/error/error-access-denied");

                // 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의

            http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/internal/login")
                .failureUrl("/error/error-login")

                .defaultSuccessUrl("/")

                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailHandler)

                .usernameParameter("email")
                .passwordParameter("password");

            // 로그아웃 관련 설정
            http.logout()

                .logoutUrl("/logout")
                //.clearAuthentication(true)
                    //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

            //http.authenticationProvider(authProvider);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(userService.passwordEncoder())
        ;
    }

}
