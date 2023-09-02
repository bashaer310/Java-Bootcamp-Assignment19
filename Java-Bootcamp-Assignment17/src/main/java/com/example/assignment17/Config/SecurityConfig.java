package com.example.assignment17.Config;

import com.example.assignment17.Service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/expert/get","/api/v1/expert/getByUsername/{username}","/api/v1/expert/getByMajor/{major}","/api/v1/customer/get","/api/v1/customer/getByUsername/{username}","/api/v1/project/get","/api/v1/project/getById/{id}","/api/v1/project/getByCategory/{category}","/api/v1/project/getByStatus/{status}","/api/v1/project/getByPrice/{price}","/api/v1/project/getByDate/{date}","/api/v1/offer/get","/api/v1/offer/getByProjectId/{projectId}","/api/v1/offer/getByExpertId/{expertId}","/api/v1/offer/getByProjectIdAndPrice/{projectId}/{price}").permitAll()
                .requestMatchers("/api/v1/expert/add","/api/v1/expert/update","/api/v1/expert/delete","/api/v1/offer/add/{id}","/api/v1/offer/update/{projectId}/{offerId}","/api/v1/offer/delete/{projectId}/{offerId}").hasAuthority("EXPERT")
                .requestMatchers("/api/v1/customer/add","/api/v1/customer/update","/api/v1/customer/delete","/api/v1/project/add","/api/v1/project/update","/api/v1/project/delete","/api/v1/project/changeStatus/{id}").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
                return httpSecurity.build();
    }

}
