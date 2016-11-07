package cn.cuit.crm.util;

import java.lang.reflect.ParameterizedType;


/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：获取父类的泛型具体类型 */
public class GenericClass {

	/* 获取父类泛型的具体类型 */
	@SuppressWarnings({ "rawtypes" })
	public static Class getGenericClass(Class clazz) {
		
		/* 使用泛型反射获取T的具体类型，在下面的注释中 */
		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
		
		/*
		 * cn.cuit.crm.dao.impl.CommonDaoImpl<cn.cuit.crm.domain.SysUserGroup>
		 * CommonDaoImpl<SysUserGroup>
		 */
		//System.out.println("type  "+type)
		
		/* 获取泛型的实际参数类型即<>里面的，因为泛型的参数有多个，所以是个数组 */
		Class entityClass = (Class) type.getActualTypeArguments()[0];
		
		//cn.cuit.crm.domain.SysUserGroup
		//System.out.println("entityClass  "+entityClass);
		
		return entityClass;
	}

}
