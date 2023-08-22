package spring.teamproject.dabang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import spring.teamproject.dabang.config.auth.AuthFailureHandler;
import spring.teamproject.dabang.handler.CustomAuthenticationSuccessHandler;
import spring.teamproject.dabang.service.auth.PrincipalOauth2UserService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final PrincipalOauth2UserService principalOauth2UserService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public SecurityConfig(PrincipalOauth2UserService principalOauth2UserService) {
        this.principalOauth2UserService = principalOauth2UserService;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        
        http.authorizeRequests()
                .antMatchers("/api/v1/grant/test/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/grant/test/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/grant/test/admin/**").access("hasRole('ROLE_ADMIN')")
                
                .antMatchers("/welcome/login").permitAll() // 로그인 페이지 접근 허용
                .antMatchers("/index").permitAll()       // 로그인 처리 URL 접근 허용
                .antMatchers("/manage/**")
                .hasRole("USER")

    			
    			
                .antMatchers("/", "/index")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/welcome/login")
                .loginProcessingUrl("/welcome/login")
                
  
                .successHandler(new CustomAuthenticationSuccessHandler()) // 이 부분 추가
                .defaultSuccessUrl("/index")
                .failureHandler(new AuthFailureHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
	            
                .and()
                
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
                
    }

}

