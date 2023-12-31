package com.aveepb.j0rn4l.security.jwt;

import com.aveepb.j0rn4l.security.user.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        //Validate request form.
        if (authHeader != null && authHeader.startsWith(PREFIX)) {

            //Extract important data from raw request.
            String token = authHeader.substring(PREFIX.length());
            Optional<String> username = this.jwtService.fetchUsername(token);

            //Check user authentication status.
            if (username.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = this.userService.loadUserByUsername(username.get());

                //Check user token.
                if (this.jwtService.isTokenValid(token, user)) {

                    //Create authentication for current request.
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //Update security context.
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        //Run next filter.
        filterChain.doFilter(request, response);
    }
}
