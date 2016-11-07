package cn.cuit.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.dao.ISysMenuDao;
import cn.cuit.crm.domain.SysMenu;
import cn.cuit.crm.service.ISysMenuService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@Transactional(readOnly = true)
@Service(value = ISysMenuService.SERVICE_NAME)
public class SysMenuServiceImpl implements ISysMenuService {

	@Resource(name = ISysMenuDao.SERVICE_NAME)
	private ISysMenuDao sysMenuDao;

	@Override
	public List<SysMenu> findAllSysMenus() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		orderby.put("o.sort", "asc");

		return sysMenuDao.findObjectsByConditionWithNoPage(orderby);
	}

	@Override
	public List<SysMenu> findAllSysMenusCache() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		orderby.put("o.sort", "asc");

		return sysMenuDao.findObjectsByConditionWithNoPageCache(null, null, orderby);
	}

}
