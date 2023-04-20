package com.shop.shoes.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class SuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        boolean hasAdmin = false;
        boolean hasUser = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for(GrantedAuthority grantedAuthority : authorities){
            if(grantedAuthority.getAuthority().equals("ROLE_USER")){
                hasUser = true;
                break;
            }
            else if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                hasAdmin = true;
                break;
            }
        }
        if(hasUser){
            redirectStrategy.sendRedirect(request,response,"/checkout");
        } else if (hasAdmin) {
            redirectStrategy.sendRedirect(request,response,"/admin/home");
        }else{
            throw new IllegalStateException();
        }
    }
}
