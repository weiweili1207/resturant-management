package com.dine.aspect;

import com.dine.annotation.AutoFill;
import com.dine.constant.AutoFillConstant;
import com.dine.context.BaseContext;
import com.dine.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * customize autofill aspect: auto fill logic
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * cut point
     */
    @Pointcut("execution(* com.dine.mapper.*.*(..)) && @annotation(com.dine.annotation.AutoFill)")
    public void autoFillPointCut() {}

    /**
     * set notification before, assign autofill value in notification
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException {
        log.info("starting auto fill...");
        //Retrieve the database operation type of the currently intercepted method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); //method signature object
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class); //get annotation object
        OperationType operationType = autoFill.value();//Retrieve the database operation type

        //Retrieve parameter - object of the currently intercepted method
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];

        //prepare value to assign
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //Assign values to the corresponding properties using reflection based on the current operation type
        if (operationType == OperationType.INSERT) {
            //autofill for four common properties
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //assign value to object property by using  Mapping
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (operationType == OperationType.UPDATE) {
            //autofill for four common properties
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //assign value to object property by using  Mapping
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
