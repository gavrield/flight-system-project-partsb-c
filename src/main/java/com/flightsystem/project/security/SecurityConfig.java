package com.flightsystem.project.security;

import com.flightsystem.project.business_logic.Role;
import com.flightsystem.project.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select \"username\", \"password\", 'true' as \"enabled\" from get_users()" +
                        "where \"username\" = ?")
                .authoritiesByUsernameQuery("select \"username\", \"role_name\" as \"role\" from get_users()" +
                        "where \"username\" = ?")
                .rolePrefix("ROLE_");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable().authorizeHttpRequests()
                .antMatchers("/admin/*").hasRole("administrator")
                .antMatchers("/airline-company/*").hasRole("airline_company")
                .antMatchers("/customer/*").hasRole("customer")
                .antMatchers("/*").permitAll()
                .and()
                .httpBasic().and()
                .headers().frameOptions().disable();
    }

//    @Bean
//    GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
//    }

}
