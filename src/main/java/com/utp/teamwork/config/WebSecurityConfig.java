/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author AdamPrzedlacki
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception{
      
       httpSecurity
            .csrf().disable()
               .authorizeRequests()   
               .antMatchers("/resources/**", "/css/**", "/js/**", "/img/**",
                       "/loginError", "/api/**").permitAll()
               .antMatchers("/admin/**").hasAuthority("ADMIN")
               .antMatchers("/user/**").authenticated()
               .anyRequest().authenticated()
               .and()
            .formLogin()
               .loginPage("/login")
               .successHandler(new CustomAuthenticationSuccess())
               .failureHandler(new CustomAuthenticationFailure())
               .permitAll()
               .and()
            .logout()
               .permitAll()
               .and()
               .exceptionHandling().accessDeniedPage("/403");
   }
   
   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").authorities("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("user").password("user").authorities("USER");
        auth.userDetailsService(userDetailsService);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
}
