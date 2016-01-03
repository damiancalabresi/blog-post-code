package com.dcalabresi.utils;

import com.dcalabresi.aop.log.NotLog;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by damiancalabresi on 05/06/15.
 */
public class ReflectionHelper {

    public static Class getClass(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringType();
    }

    public static MethodSignature getMethod(JoinPoint joinPoint) {
        return (MethodSignature) joinPoint.getSignature();
    }

    public static Map<String, Object> getMethodArguments(JoinPoint joinPoint) {
        MethodSignature method = getMethod(joinPoint);
        String[] parameterNames = method.getParameterNames();
        Object[] params = joinPoint.getArgs();

        Map<String, Object> paramsMap = Maps.newHashMap();
        for (int i=0; i<parameterNames.length; i++) {
            paramsMap.put(parameterNames[i], params[i]);
        }

        return paramsMap;
    }

    @SuppressWarnings("unchecked")
    public static String generateMethodArgumentsDescription(JoinPoint joinPoint) throws NoSuchMethodException {
        StringBuilder builder = new StringBuilder();

        Class aClass = getClass(joinPoint);

        MethodSignature method = getMethod(joinPoint);

        Class<?>[] parameterTypes = method.getParameterTypes();
        Annotation[][] paramAnnotations = getMethodAnnotations(joinPoint);
        Object[] params = joinPoint.getArgs();

        builder.append(" ( ");

        for (int i=0; i<params.length; i++) {
            StringBuilder paramDesc = generateParamDescription(parameterTypes[i],params[i],paramAnnotations[i]);
            builder.append(paramDesc);
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append(" )");

        return builder.toString();

    }

    public static Annotation[][] getMethodAnnotations(JoinPoint joinPoint) throws NoSuchMethodException {
        Class aClass = getClass(joinPoint);
        MethodSignature method = getMethod(joinPoint);
        Class<?>[] parameterTypes = method.getParameterTypes();
        String methodName = method.getName();
        return aClass.getMethod(methodName,parameterTypes).getParameterAnnotations();
    }

    private static StringBuilder generateParamDescription(Class<?> parameterType, Object value, Annotation[] annotations) {
        StringBuilder builder = new StringBuilder();
        builder.append(parameterType.getSimpleName());
        builder.append(": ");

        boolean canBeLogged = true;
        for (Annotation annotation : annotations) {
            if(annotation instanceof NotLog) canBeLogged = false;
        }

        if(canBeLogged) {
            builder.append(getObjectDescription(value));
        } else {
            builder.append("****");
        }

        return builder;
    }

    @SuppressWarnings("unchecked")
    private static String getObjectDescription(Object value) {
        if (value==null) {
            return "null";
        } else if (value instanceof Iterable) {
            Iterable<Object> iterValue = (Iterable<Object>) value;
            return getIterableObjectDescription(iterValue);
        } else {
            return value.toString();
        }
    }

    private static String getIterableObjectDescription(Iterable<Object> iterValue) {
        StringBuilder valueBuilder = new StringBuilder("[ ");
        for (Object element : iterValue) {
            valueBuilder.append(element);
            valueBuilder.append(", ");
        }
        valueBuilder.delete(valueBuilder.length() - 2, valueBuilder.length());
        valueBuilder.append(" ]");
        return valueBuilder.toString();
    }

}
