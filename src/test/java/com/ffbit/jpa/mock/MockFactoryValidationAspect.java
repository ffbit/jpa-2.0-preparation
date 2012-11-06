package com.ffbit.jpa.mock;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Dmytro Chyzhykov
 */
@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MockFactoryValidationAspect {
    @Autowired
    private Validator validator;

    @Pointcut("this(com.ffbit.jpa.mock.MockFactory))")
    void mockFactory() {
    }

    @Pointcut("execution(* build())")
    void build() {
    }

    @Pointcut("execution(* create())")
    void create() {
    }

    @Pointcut("execution(* build()) || execution(* create())")
    void buildOrCreate() {
    }

    @AfterReturning(pointcut = "mockFactory() && buildOrCreate()",
            returning = "entity")
    public void validate(Object entity) {
        Set<ConstraintViolation<Object>> failures = validator.validate(entity);

        if (failures.isEmpty()) {
            return;
        }

        for (ConstraintViolation e : failures) {
            // Process every failure
        }
    }

}
