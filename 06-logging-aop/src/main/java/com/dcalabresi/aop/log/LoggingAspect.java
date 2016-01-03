package com.dcalabresi.aop.log;

import com.dcalabresi.utils.ReflectionHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("@annotation(Loggable)")
    public void methodLoggable() {}

    @Pointcut("publicMethod() && methodLoggable()")
    public void publicAndLoggableMethod() {}

    @Before("publicAndLoggableMethod()")
    public void logServiceCall(JoinPoint joinPoint) throws NoSuchMethodException {
        logger.info("Call      " + this.generateMethodCallDescription(joinPoint) + " - Arguments - " + ReflectionHelper.generateMethodArgumentsDescription(joinPoint));
    }

    @SuppressWarnings("unchecked")
    @AfterReturning(value = "publicAndLoggableMethod()")
    public void logServiceReturn(JoinPoint joinPoint) throws NoSuchMethodException {
        logger.info("Return    " + this.generateMethodCallDescription(joinPoint) + " - Success");
    }

    @AfterThrowing(value = "publicAndLoggableMethod()", throwing = "ex")
    public void logServiceException(JoinPoint joinPoint, Throwable ex) throws NoSuchMethodException {
        logger.info("Exception " + this.generateMethodCallDescription(joinPoint) + " - Error - " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
    }

    @SuppressWarnings("unchecked")
    private String generateMethodCallDescription(JoinPoint joinPoint) throws NoSuchMethodException {
        StringBuilder builder = new StringBuilder();

        Class aClass = joinPoint.getSignature().getDeclaringType();
        MethodSignature method = (MethodSignature) joinPoint.getSignature();

        String className = aClass.getSimpleName();
        String methodName = method.getName();

        builder.append(className);
        builder.append(" - ");
        builder.append(methodName);

        return builder.toString();
    }

}