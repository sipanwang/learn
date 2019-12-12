package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            // TODO Auto-generated method stub
            //super.configure(http);
            //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
            //.loginPage("/RequestMaplogin") 代表 @PostMapping(value = "/RequestMaplogin")
            //.loginProcessingUrl("/FormloginAction") 代表 <form th:action="@{/FormloginAction}">
            //.failureUrl("/login-error") 代表 登陆失败跳转login-error.html
            //http.formLogin().loginPage("/RequestMaplogin").loginProcessingUrl("/FormloginAction").failureUrl("/login-error").permitAll()
            http.formLogin().loginPage("/loginAction").loginProcessingUrl("/loginAction").failureUrl("/loginError").permitAll()
             .and()
             .authorizeRequests().anyRequest().authenticated()
             .and()
             .csrf().disable();
        }
}
