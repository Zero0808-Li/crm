package cn.cuit.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.dao.ICityDao;
import cn.cuit.crm.domain.City;
import cn.cuit.crm.service.ICityService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Transactional(readOnly = true)
@Service(value = ICityService.SERVICE_NAME)
public class CityServiceImpl implements ICityService {

	@Resource(name=ICityDao.SERVICE_NAME)
	private ICityDao cityDao;

	@Override
	public List<City> findCitiesByPid(Integer id) {
		if (id != null) {
			String whereHql = " and o.pid = ? ";
			Object[] params = {id};
			LinkedHashMap<String , String> orderby = new LinkedHashMap<String , String>();
			orderby.put("o.id", "asc");
			return cityDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
		}
		return null;
	}
	
	
	
}
