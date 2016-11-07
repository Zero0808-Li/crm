package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.domain.SysDictionaryType;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ISysDictionaryTypeService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysDictionaryTypeServiceImpl";
	
	/**
	 * 通过code（分类标识）来查询sys_dictionary_type表的信息
	 * @param string
	 * @return
	 */
	public abstract List<SysDictionaryType> findSysDictionaryTypeByCode(String string);

}
