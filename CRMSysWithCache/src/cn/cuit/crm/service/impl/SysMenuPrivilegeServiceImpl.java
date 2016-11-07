package cn.cuit.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.dao.ISysMenuPrivilegeDao;
import cn.cuit.crm.domain.SysMenuPrivilege;
import cn.cuit.crm.domain.SysMenuPrivilegeId;
import cn.cuit.crm.service.ISysMenuPrivilegeService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Transactional(readOnly = true)
@Service(value = ISysMenuPrivilegeService.SERVICE_NAME)
public class SysMenuPrivilegeServiceImpl implements ISysMenuPrivilegeService {

	@Resource(name=ISysMenuPrivilegeDao.SERVICE_NAME)
	private ISysMenuPrivilegeDao sysMenuPrivilegeDao;

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void updateMenu(String roleId, String[] menuModules) {
		/* 更新之前好删除表中的数据，因为他们是联合主键，如果插入一次，不删除，就无法再插入（即不能更新了，所以要删除后再更新） */
		if (StringUtils.isNotBlank(roleId) && menuModules != null && menuModules.length > 0) {
			String whereHql = " and o.id.roleId = ? ";
			Object[] params = {roleId};
			List<SysMenuPrivilege> list = sysMenuPrivilegeDao.findObjectsByConditionWithNoPage(whereHql, params);
			sysMenuPrivilegeDao.deleteAllObject(list);
		
			/* 增加菜单权限到权限组中,下面的可以省略，把它包在外面的判断了 */
			//if (StringUtils.isNotBlank(roleId) && menuModules != null && menuModules.length > 0) {
			for (int i = 0; i < menuModules.length; i++) {
				if (StringUtils.isNotBlank(menuModules[i])) {
					String[] str = menuModules[i].split(","); /* 页面的menuModule，menuPrivilege是逗号分隔的形式传参过来的 */
					SysMenuPrivilege s = new SysMenuPrivilege();
					SysMenuPrivilegeId id = new SysMenuPrivilegeId();
					id.setRoleId(roleId);
					id.setMenuModule(str[0]);
					id.setMenuPrivilege(str[1]);
					s.setId(id);
					sysMenuPrivilegeDao.save(s);
				}
			}
		}
	}

	@Override
	public List<SysMenuPrivilege> findSysMenuPrivilegesByRoleId(String roleId) {
		if (StringUtils.isNotBlank(roleId)) {
			String whereHql = " and o.id.roleId = ? ";
			Object[] params = {roleId};
			List<SysMenuPrivilege> sysMenuPrivileges = sysMenuPrivilegeDao.findObjectsByConditionWithNoPage(whereHql, params);
			
			return sysMenuPrivileges;
		}
		return null;
	}

	@Override
	public List<SysMenuPrivilege> findAllSysMenuPrivileges() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id.roleId", "asc");
		return sysMenuPrivilegeDao.findObjectsByConditionWithNoPage(orderby);
	}

	@Override
	public List<SysMenuPrivilege> findAllSysMenuPrivilegesCache() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id.roleId", "asc");
		return sysMenuPrivilegeDao.findObjectsByConditionWithNoPage(null, null, orderby);
	}
	
	
}
