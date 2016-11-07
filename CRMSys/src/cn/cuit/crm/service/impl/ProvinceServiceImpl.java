package cn.cuit.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.dao.IProvinceDao;
import cn.cuit.crm.domain.Province;
import cn.cuit.crm.service.IProvinceService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Transactional(readOnly = true)
@Service(value = IProvinceService.SERVICE_NAME)
public class ProvinceServiceImpl implements IProvinceService {

	@Resource(name=IProvinceDao.SERVICE_NAME)
	private IProvinceDao provinceDao;

	@Override
	public List<Province> findAllProvinces() {
		LinkedHashMap<String , String> orderby = new LinkedHashMap<String , String>();
		orderby.put("o.id", "asc");
		return provinceDao.findObjectsByConditionWithNoPage(orderby);
	}

	@Override
	public Province findProvinceByName(String name) {
		if (StringUtils.isNotBlank(name)) {
			String whereHql = " and o.name = ? ";
			Object[] params = { name };
			List<Province> list = provinceDao.findObjectsByConditionWithNoPage(whereHql, params);
			if (list != null && list.size() == 1) {
				return list.get(0);
			}
		}
		return null;
	}
}
