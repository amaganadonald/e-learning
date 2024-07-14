package com.amagana.e_learning.aspect;

import com.amagana.e_learning.exception.ELearningBusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class LoggingAspect {

    private ObjectMapper objectMapper;

    @Pointcut("execution(* com.amagana.e_learning.controller..*(..))")
    public void allPublicMethods() {}

    @Before(value = "allPublicMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("{}::{} Starting proceed", signature.getDeclaringTypeName(), signature.getName());
    }

    @AfterReturning(value = "allPublicMethods()", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("{}::{} Finished proceed with result {}", signature.getDeclaringTypeName(),
                signature.getName(),result.toString());
    }

    @AfterThrowing(value = "allPublicMethods()", throwing = "error")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Exception error) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.error("{}::{} Finished proceed with error {}", signature.getDeclaringTypeName(),
                signature.getName(), error.getMessage());
        throw new ELearningBusinessException("error occurred with message :" + error.getMessage());
    }
}
