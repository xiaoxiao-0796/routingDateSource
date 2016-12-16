package com.xiaofei.dataSource.service.aspect;

import com.xiaofei.dataSource.service.db.DataSource;
import com.xiaofei.dataSource.service.db.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/12/15 0015.
 * 注解切面编程测试
 * 需要在配置文件中添加<aop:aspectj-autoproxy />
 */
@Component
@Aspect
@Slf4j
public class DemoAspect implements Ordered{

    @Pointcut("@annotation(com.xiaofei.dataSource.service.db.DataSource)")
    public void pointcut(){}


    @Before("pointcut()")
    public void excute(JoinPoint joinPoint){
        log.debug("DemoAspect excute start");
        Class<?> target = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());

    }

    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                DataSourceContextHolder.setDataSource(source.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                DataSourceContextHolder.setDataSource(source.value());
            }
        }catch (Exception e){

        }
    }


    public int getOrder() {
        return -1;
    }
}
