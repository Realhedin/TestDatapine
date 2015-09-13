package com.datapine.loggingAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Aspect for logging login attempts using AspectJ
 *
 * Created by Dmitrii on 9/13/15.
 */
@Aspect
public class LoggingAspect {

    /**
     * This method will be called before loadUserByUsername() in the application
     * @param joinPoint - loadUserByUsername() method
     */
    @Before("execution(* com.datapine.service.TestUserDetailsService.loadUserByUsername(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("AspectJ: logBefore() is running!");
        System.out.println("AspectJ: Logging attempt from "+joinPoint.getSignature().getName());
    }


    /**
     * This method will be called after loadUserByUsername() and get results of that method
     * @param result - return value from loadUserByUsername() method
     */
    @AfterReturning(pointcut = "execution(* com.datapine.service.TestUserDetailsService.loadUserByUsername(..))",
            returning = "result")
    public void logAfterReturning(UserDetails result) {
        System.out.println("AspectJ: logAfterReturning() is running!");
        System.out.println("AspectJ: Logged as: " +result.getUsername() +
        " with authorities: " +result.getAuthorities().iterator().next());
    }
}
