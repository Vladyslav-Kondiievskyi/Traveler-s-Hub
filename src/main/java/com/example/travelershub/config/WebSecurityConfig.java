package com.example.travelershub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoderProvider passwordEncoder;

    public WebSecurityConfig(@Qualifier("customUserDetailsService")UserDetailsService userDetailsService,
                                 PasswordEncoderProvider passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder.getPasswordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login();
//        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/orders/my_unconfirmed_orders").authenticated()
                .antMatchers(HttpMethod.POST, "/orders/complete").authenticated()
                .antMatchers("/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/register").permitAll()
//                    // .anyRequest().authenticated()
                    .and()
                    .formLogin()
//                    //                    .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                    .permitAll()
                    .and()
                    .httpBasic()
                    .and()
//                                    .oauth2Login()
//                                    .and()
                    .csrf()
                    .disable();
    }

}
