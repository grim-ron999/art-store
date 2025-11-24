package com.artstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.admin.username:admin}")
    private String adminUsername;

    @Value("${security.admin.password:changeme}")
    private String adminPassword;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Use property-backed admin credentials. In production set ADMIN_PASSWORD env var.
        auth.inMemoryAuthentication()
                .withUser(adminUsername)
                // {noop} means no password encoder; set a real encoder for production if storing encoded passwords
                .password("{noop}" + adminPassword)
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/css/**", "/js/**", "/", "/about", "/contact", "/detail/**").permitAll()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                .logout().permitAll();
    }
}
