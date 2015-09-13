package com.datapine.loggingAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

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
        System.out.println("AspectJ: User:"+ joinPoint.getArgs()[0] +" is trying to login.");
    }


//    /**
//     * This method will be called after loadUserByUsername() and get results of that method
//     * @param result - return value from loadUserByUsername() method
//     */
//    @AfterReturning(pointcut = "execution(* com.datapine.service.TestUserDetailsService.loadUserByUsername(..))",
//            returning = "result")
//    public void logAfterReturning(UserDetails result) {
//        System.out.println("AspectJ: logAfterReturning() is running!");
//        System.out.println("AspectJ: Logged as: " +result.getUsername() +
//        " with authorities: " +result.getAuthorities().iterator().next());
//    }

    /**
     * This method will be called after onAuthenticationSuccess()
     */
    @After("execution(* com.datapine.service.PostSuccessfulAuthenticationHandler.onAuthenticationSuccess(..))")
    public void logAfterSuccessLogin(JoinPoint joinPoint) {
        System.out.println("AspectJ: logAfterSuccessLogin() is running!");
        Authentication auth = (Authentication)joinPoint.getArgs()[2];
        System.out.println("AspectJ: Logged as: " +auth.getName() +
                " with authorities: " +auth.getAuthorities().iterator().next());

    }


    /**
     * This method will be called after onAuthenticationFailure()
     */
    @After("execution(* com.datapine.service.PostFailureAuthenticationHandler.onAuthenticationFailure(..))")
    public void logAfterFailureLogin(JoinPoint joinPoint) {
        System.out.println("AspectJ: logAfterFailureLogin(() is running!");
        String username = ((HttpServletRequest)joinPoint.getArgs()[0]).getParameter("j_username");
        System.out.println("AspectJ: Authentication of user:"+username+" failed!");
    }
}
