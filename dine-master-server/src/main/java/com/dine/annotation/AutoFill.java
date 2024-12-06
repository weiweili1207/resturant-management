package com.dine.annotation;

import com.dine.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * customize annotation label the method that need to be autofilled
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //database ddl: update / insert
    // Declares an annotation element of type OperationType
    OperationType value();
}
