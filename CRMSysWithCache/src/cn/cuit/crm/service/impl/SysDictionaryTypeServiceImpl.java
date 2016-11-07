package cn.cuit.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.dao.ISysDictionaryTypeDao;
import cn.cuit.crm.domain.SysDictionaryType;
import cn.cuit.crm.service.ISysDictionaryTypeService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Transactional(readOnly = true)
@Service(ISysDictionaryTypeService.SERVICE_NAME)
public class SysDictionaryTypeServiceImpl implements ISysDictionaryTypeService {

	@Resource(name=ISysDictionaryTypeDao.SERVICE_NAME)
	private ISysDictionaryTypeDao sysDictionaryTypeDao;

	@Override
	public List<SysDictionaryType> findSysDictionaryTypeByCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			String whereHql = " and o.code = ? ";
			Object[] params = { code };
			LinkedHashMap<String, String> orderby = new LinkedHashMap< String, String>();
			orderby.put("o.sort", "asc"); 
			return sysDictionaryTypeDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
		}
		return null;
	}
}
