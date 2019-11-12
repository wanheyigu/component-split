package com.cc.config.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
 * 自定义注解：
 * @Target 表示此注解要应用的位置(默认不加参数可以应用到任何地方)
 * @Retention 配置注解应用时机(不加参数默认CLASS类加载阶段，三种状态包含关系，RUNTIME为全包含)
 * @Documented 配置生成文档
 * @Inheritance 配置子类是否可以集成
 * 自定义注解一般使用前两个配置即可
 * 注意：每一个注解本事就是要一个接口，每一个注解默认继承java.lang.annotation.Annotation
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectConnection {

	String name() default "";
	
	boolean readOnly() default false; 
	boolean Inter() default true;
	
}
	