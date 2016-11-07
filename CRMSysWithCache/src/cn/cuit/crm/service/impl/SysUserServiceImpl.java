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

import cn.cuit.crm.bean.SysUserSearch;
import cn.cuit.crm.dao.ISysUserDao;
import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.service.ISysUserService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@Transactional(readOnly = true)
@Service(ISysUserService.SERVICE_NAME)
public class SysUserServiceImpl implements ISysUserService {

	@Resource(name = ISysUserDao.SERVICE_NAME)
	private ISysUserDao sysUserDao;

	@Override
	public SysUser findSysUserByNameAndPassword(String name, String password) {
		/* 组织条件 */
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
			String whereHql = " and o.name=? and o.password=? ";
			Object[] params = { name, password };
			List<SysUser> list = sysUserDao.findObjectsByConditionWithNoPage(whereHql, params);

			if (list != null && list.size() == 1) {
				return list.get(0);
			}
		}

		return null;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveSysUser(SysUser sysUser) {
		sysUserDao.save(sysUser);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<SysUser> findSysUsersByCondition(SysUserSearch sysUserSearch) {
		if (sysUserSearch == null) {
			throw new RuntimeException("Exception:传递给业务层用户查询条件的对象为空");
		}

		/* 组织查询条件 */
		String whereHql = "";
		@SuppressWarnings("rawtypes")
		List paramList = new ArrayList<String>();

		if (StringUtils.isNotBlank(sysUserSearch.getName())) {
			whereHql = whereHql + " and o.name like ? ";
			paramList.add("%" + sysUserSearch.getName().trim() + "%");
		}

		if (StringUtils.isNotBlank(sysUserSearch.getCnname())) {
			whereHql = whereHql + " and o.cnname like ? ";
			paramList.add("%" + sysUserSearch.getCnname().trim() + "%");
		}
		
		if (StringUtils.isNotBlank(sysUserSearch.getStatus())) {
			whereHql = whereHql + " and o.status = ? ";
			paramList.add(sysUserSearch.getStatus().trim());
		}
		
		/* 这里判断！=0是因为转换器好像是有默认值为0 */
		if (sysUserSearch.getGroupId() != null && sysUserSearch.getGroupId() != 0) {
			whereHql = whereHql + " and o.sysUserGroup.id = ? ";
			paramList.add(sysUserSearch.getGroupId());
		}
		
		Object[] params = paramList.toArray();
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id", "asc");

		return sysUserDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteSysUsersByIds(Integer[] ids) {
		sysUserDao.deleteByIds((Serializable[])ids);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void enableSysUsersByIds(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				SysUser sysUser = sysUserDao.findObjectById(ids[i]);
				sysUser.setStatus("Y");
				sysUserDao.update(sysUser);
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void disableSysUsersByIds(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				SysUser sysUser = sysUserDao.findObjectById(ids[i]);
				sysUser.setStatus("N");
				sysUserDao.update(sysUser);
			}
		}
	}

	@Override
	public SysUser findSysUserById(Integer id) {
		return sysUserDao.findObjectById(id);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateSysUser(SysUser newSysUser) {
		sysUserDao.update(newSysUser);
	}

	@Override
	public List<SysUser> findAllSysUsers() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id", "asc");

		return sysUserDao.findObjectsByConditionWithNoPage(orderby);
	}

}
