package com.thopham.projects.research.apiservice.securities

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenResolveFilter: OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorization: String? = request.getHeader("Authorization")
        if(authorization != null) {
            val authorizations = authorization.split(" ")
            if (authorizations.size == 2) {
                val token = authorizations[1]
                request.setAttribute("token", token)
                SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                        token,
                        null,
                        listOf(SimpleGrantedAuthority("ROLE_USER"))
                )
            }
        }
        filterChain.doFilter(request, response)
    }
}