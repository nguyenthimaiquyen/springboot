package com.quyen.test.config;

import com.quyen.test.security.CustomUserDetailsService;
import com.quyen.test.statics.Roles;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/appointments", "/admin", "/admin/products/{id}").hasAnyAuthority(Roles.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/admin/products", "/admin/appointments").hasAnyAuthority(Roles.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/admin/products", "/admin/appointments/{id}", "/admin/appointments/reject/{id}").hasAnyAuthority(Roles.ADMIN.toString())
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().sameOrigin();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
