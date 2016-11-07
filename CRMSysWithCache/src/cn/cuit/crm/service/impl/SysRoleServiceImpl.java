package cn.cuit.crm.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.bean.SysRoleSearch;
import cn.cuit.crm.dao.ISysRoleDao;
import cn.cuit.crm.domain.SysRole;
import cn.cuit.crm.service.ISysRoleService;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@Transactional(readOnly = true)
@Service(value = ISysRoleService.SERVICE_NAME)
public class SysRoleServiceImpl implements ISysRoleService {

	/* 把DAO的实现注入 */
	@Resource(name = ISysRoleDao.SERVICE_NAME)
	private ISysRoleDao sysRoleDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveSysRole(SysRole sysRole) {
		sysRoleDao.save(sysRole);
	}

	@Override
	public List<SysRole> findSysRoles(SysRoleSearch sysRoleSearch) {
		/* 组织查询条件，封装查询条件需要的数据，注：查询条件有时会按条件查询，没有条件的就是显示全部信息 */
		if (sysRoleSearch == null) {
			throw new RuntimeException("传递给业务层权限组查询条件的对象为空");
		}

		String whereHql = "";

		List<String> paramList = new ArrayList<String>();

		if (StringUtils.isNotBlank(sysRoleSearch.getName())) {
			whereHql = " and o.name like ? ";
			paramList.add("%" + sysRoleSearch.getName().trim() + "%");
		}

		Object[] params = paramList.toArray();

		/* 排序 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id", "asc");

		return sysRoleDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
	}

	@Override
	public SysRole findSysRoleById(String id) {
		return sysRoleDao.findObjectById(id);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateSysRole(SysRole sysRole) {
		sysRoleDao.update(sysRole);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteSysRolesByIds(String...ids) {
		sysRoleDao.deleteByIds((Serializable[])ids);
	}

	@Override
	public List<SysRole> findAllSysRoles() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id", "asc");
		return sysRoleDao.findObjectsByConditionWithNoPage(orderby);
	}
}
