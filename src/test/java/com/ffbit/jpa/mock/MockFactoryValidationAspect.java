package com.ffbit.jpa.mock;

import java.util.Set;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void validate(Object entity) {
        Set failures = validator.validate(entity);

        if (failures.isEmpty()) {
            return;
        }

        String message = String.format(
                "Validation failed for [%s] after build", entity.getClass());
        // TODO: Spring intercepts this kind of Exceptions, so it woun't be printed. 
        throw new ConstraintViolationException(message, failures);

    }

}
