package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.domain.Province;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface IProvinceService {
	
	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.ProvinceServiceImpl";

	/**
	 * 获取所有省的信息
	 * @return
	 */
	public abstract List<Province> findAllProvinces();

	/**
	 * 通过省的名称获取省的对象
	 * @param name
	 * @return
	 */
	public abstract Province findProvinceByName(String name);
}
