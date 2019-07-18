//package com.bigdata.railway.security_config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Configuration
//@EnableWebSecurity
//public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Bean
//    public AuthenticationProvider authProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        return provider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()// 表单登录  来身份认证
////                .loginPage("/login")// 自定义登录页面
//                .loginProcessingUrl("/authentication/form").permitAll()// 自定义登录路径
//                .successHandler(new MySuccessHandler())
//                .and()
//                .authorizeRequests()// 对请求授权
//                // error  127.0.0.1 将您重定向的次数过多
//                .antMatchers( "/login","/authentication/form","/upload/1").permitAll()// 这些页面不需要身份认证,其他请求需要认证
//                .antMatchers( "/upload/**").hasRole("admin")
//                .anyRequest()//.permitAll() // 任何请求
//                .authenticated() // 都需要身份认证
//                .and()
//                .csrf().disable()// 禁用跨站攻击
//                .cors();   //跨域支持！
//        super.configure(http);
//    }
//
//
//    public class MySuccessHandler implements AuthenticationSuccessHandler {
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest request,
//                                            HttpServletResponse response, Authentication authentication) throws IOException {
//
////            HttpSession session = request.getSession();
////            JwtUser authUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////            session.setAttribute("username", authUser.getUsername());
////            session.setAttribute("authorities", authentication.getAuthorities());
//
//            //set our response to OK status
//            String url = request.getHeader("Origin");
//            if (!StringUtils.isEmpty(url)) {
//                String val = response.getHeader("Access-Control-Allow-Origin");
//                if (StringUtils.isEmpty(val)) {
//                    response.setHeader("Access-Control-Allow-Headers", "*");
//                    response.addHeader("Access-Control-Allow-Origin", "http://localhost:8081");
//                    response.addHeader("Access-Control-Allow-Credentials", "true");
//                }
//            }
//            response.setStatus(HttpServletResponse.SC_OK);
//
//        }
//    }
//}
