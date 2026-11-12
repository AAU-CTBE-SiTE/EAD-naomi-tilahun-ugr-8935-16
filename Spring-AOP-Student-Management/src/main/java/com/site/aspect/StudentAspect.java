package com.site.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentAspect {


    @Before("execution(* com.site.service.StudentService.*(..))")
    public void beforeMethod() {

        System.out.println("\n[AOP BEFORE] Student service method is starting...");
    }


    @After("execution(* com.site.service.StudentService.*(..))")
    public void afterMethod() {

        System.out.println("[AOP AFTER] Student service method has finished.");
    }


    @AfterReturning(
            pointcut = "execution(* com.site.service.StudentService.*(..))",
            returning = "result"
    )
    public void afterReturning(Object result) {

        System.out.println("[AOP AFTER RETURNING] Method executed successfully.");

        if (result != null) {
            System.out.println("[AOP] Returned result: " + result);
        }
    }


    @AfterThrowing(
            pointcut = "execution(* com.site.service.StudentService.*(..))",
            throwing = "exception"
    )
    public void afterThrowing(Exception exception) {

        System.out.println("[AOP AFTER THROWING] An exception occurred!");
        System.out.println("[AOP ERROR] " + exception.getMessage());
    }


    @Around("execution(* com.site.service.StudentService.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        System.out.println("\n[AOP AROUND] Executing: "
                + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        System.out.println("[AOP AROUND] Execution time: "
                + (endTime - startTime) + " ms");

        return result;
    }
}
