package com.portfolio.done.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.portfolio.done.appuser.AppUser;

public class CredentialUtils {
    private CredentialUtils() {
    }

    public static long getUserId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        authentication.getDetails();
        System.out.println(authentication.getPrincipal());
        long UserId = 0;
//        if (authentication != null) {
//                AppUser userDetails = (AppUser) authentication.getPrincipal();
//                UserId =  userDetails.getId();
//       }
        return UserId;
    }
}
