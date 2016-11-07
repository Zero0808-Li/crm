package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.domain.City;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ICityService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.CityServiceImpl";

	/**
	 * 通过省的id获取该省下的城市
	 * @param id
	 * @return
	 */
	public abstract List<City> findCitiesByPid(Integer id);
}
