package com.example.hospital_management_system.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log
public class HospitalLoggerAop {
    @Around("execution(* com.example.hospital_management_system.controller.*.*(..))")
    public Object aroundAllRepositoryMethodsAdvice
            (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getName();
        log.info("start of " + methodName);
        Object targetMethodResult = proceedingJoinPoint.proceed();
        log.info("successful of  " + methodName);
        return targetMethodResult;
    }
}
