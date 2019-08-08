package com.thopham.projects.research.apiservice.securities

import com.fasterxml.jackson.databind.ObjectMapper
import com.thopham.projects.research.apiservice.core.Response
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfigs(): WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .sessionAuthenticationFailureHandler{request, response, exception ->
                        response.setHeader("Content-Type", "application/json;charset=utf-8")
                        response.writer.write(
                                ObjectMapper().writeValueAsString(Response(
                                        false, "Quyền ?", null
                                ))
                        )
                    }
                .and()
                .addFilterAfter(TokenResolveFilter(), UsernamePasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                    .antMatchers("/organizations", "/statistics")
                        .hasAnyRole("USER")
                    .anyRequest()
                        .permitAll()
    }
}