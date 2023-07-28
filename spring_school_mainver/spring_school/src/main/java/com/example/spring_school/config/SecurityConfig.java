package com.example.spring_school.config;

import com.example.spring_school.filter.AuthEndpoint;
import com.example.spring_school.filter.AuthenFilter;
import com.example.spring_school.filter.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AuthenFilter authenFilter() throws Exception {
        AuthenFilter authenFilter = new AuthenFilter();
        authenFilter.setAuthenticationManager(authenticationManager());
        return authenFilter;
    }

    @Bean
    public AuthEndpoint authEndpoint() throws Exception {
        return new AuthEndpoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().ignoringAntMatchers("/api/v1/**");
        // antMatchers: xem đường dẫn có giống với đối số ko
        http.authorizeRequests().antMatchers("/api/v1/login**").permitAll(); // not authen with login api
        http.antMatcher("v1/api/**").httpBasic().authenticationEntryPoint(authEndpoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/v1/").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/api/v1/**").access("hasRole('ROLE_ADMIN')").and()
                .addFilterBefore(authenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

    }
}
