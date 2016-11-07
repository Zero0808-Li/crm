package cn.cuit.crm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：自定义注解用于权限控制  */
@Retention(RetentionPolicy.RUNTIME) //使用这个注解修饰我们定义的注解用于在运行时能够反射出该我们定义的注解
public @interface Limit {

	/* 模块名称 */
	String module();  /* 注解中的属性是以方法的形式写的 */
	
	/* 操作名称 */
	String privilege();  /* 注解中的属性是以方法的形式写的 */
}
