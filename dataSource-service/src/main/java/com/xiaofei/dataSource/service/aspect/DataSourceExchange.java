package com.xiaofei.dataSource.service.aspect;

import com.xiaofei.dataSource.service.db.DataSource;
import com.xiaofei.dataSource.service.db.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
@Slf4j
@Component
public class DataSourceExchange  {


        /**
         * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
         *
         * @param point
         * @throws Exception
         */
        public void intercept(JoinPoint point) throws Exception {
            Class<?> target = point.getTarget().getClass();
            MethodSignature signature = (MethodSignature) point.getSignature();
            // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
            for (Class<?> clazz : target.getInterfaces()) {
                resolveDataSource(clazz, signature.getMethod());
            }
            resolveDataSource(target, signature.getMethod());
        }

        /**
         * 提取目标对象方法注解和类型注解中的数据源标识
         *
         * @param clazz
         * @param method
         */
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
            } catch (Exception e) {
                log.error(clazz + ":" + e.getMessage());
            }
        }


}
