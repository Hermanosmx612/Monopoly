package edu.proyecto.monopoly.proyecto_monopoly.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.proyecto.monopoly.proyecto_monopoly.srv.JwtService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsServiceImpl;
    }



    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest req, @Nonnull HttpServletResponse res, @Nonnull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = req.getHeader("Authorization");
        final String jwt;
        final String nickname;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(req, res);
            return;
        }
        jwt = authHeader.substring(7);

        try{
            nickname = jwtService.getNicknameUsuarioFromToken(jwt);

            if(nickname != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(nickname);
                if(jwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }catch(MalformedJwtException e){

        }catch(UnsupportedJwtException e){

        }catch(ExpiredJwtException e){

        }catch(IllegalArgumentException e){

        }catch(SecurityException e){

        }

        filterChain.doFilter(req, res);
    }
    
}
