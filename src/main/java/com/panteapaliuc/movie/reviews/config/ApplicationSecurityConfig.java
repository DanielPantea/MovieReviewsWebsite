package com.panteapaliuc.movie.reviews.config;

import com.panteapaliuc.movie.reviews.service.UserService;
import com.panteapaliuc.movie.reviews.utility.enUserRole;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .cors()
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/movie/**").permitAll()
                .antMatchers("/api/movie/poster/add/**").hasAnyAuthority(enUserRole.ADMIN.name(), enUserRole.USER.name())
                .antMatchers("/api/movie/**").hasAuthority(enUserRole.ADMIN.name())
                .antMatchers("/api/user/**").hasAnyAuthority(enUserRole.USER.name(), enUserRole.ADMIN.name())
                .antMatchers("/api/register/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().sameOrigin()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
