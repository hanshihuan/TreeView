package com.piccfs.myapplication.annntation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)//target:注解添加在哪，类，属性，方法
@Retention(RetentionPolicy.RUNTIME)//注解什么时候可见,:运行时

public @interface NodeLable {

}
