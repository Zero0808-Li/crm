package cn.cuit.crm.util;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 日期转化器， 将日期转化为String */
public class SQLDateConverter implements Converter{

	@SuppressWarnings("rawtypes")
	@Override
	/* type表示要转化的类型 value表示要转化的值*/
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}
		
		if (type == null) {
			return null;
		}
		
		if (type != java.sql.Date.class) {
			return null;
		}
		
		if (value instanceof String) {
			/* 防止字符串为空字符，即空格 */
			String str = (String) value;
			if (StringUtils.isNotBlank(str)) {
				return java.sql.Date.valueOf((String) value);
			}
		}
		
		return null;
	}

}
