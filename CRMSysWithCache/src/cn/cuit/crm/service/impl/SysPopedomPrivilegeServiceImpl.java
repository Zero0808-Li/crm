package cn.cuit.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.dao.ISysPopedomPrivilegeDao;
import cn.cuit.crm.domain.SysPopedomPrivilege;
import cn.cuit.crm.domain.SysPopedomPrivilegeId;
import cn.cuit.crm.service.ISysPopedomPrivilegeService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@Transactional(readOnly = true)
@Service(value = ISysPopedomPrivilegeService.SERVICE_NAME)
public class SysPopedomPrivilegeServiceImpl implements ISysPopedomPrivilegeService {

	@Resource(name = ISysPopedomPrivilegeDao.SERVICE_NAME)
	private ISysPopedomPrivilegeDao sysPopedomPrivilegeDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updatePopedom(String roleId, String[] popedomModules) {
		/* 因为权限组里面的字段全是主键（即联合主键），所以在更新时要先删除所有的 */
		if (StringUtils.isNotBlank(roleId) && popedomModules != null && popedomModules.length > 0) {
			String whereHql = " and o.id.roleId=? ";
			Object[] params = { roleId };

			List<SysPopedomPrivilege> list = sysPopedomPrivilegeDao.findObjectsByConditionWithNoPage(whereHql, params);

			sysPopedomPrivilegeDao.deleteAllObject(list);
		}

		/* 检查数据合法性 */
		if (StringUtils.isNotBlank(roleId) && popedomModules != null && popedomModules.length > 0) {
			for (int i = 0; i < popedomModules.length; i++) {
				if (StringUtils.isNotBlank(popedomModules[i])) {
					String[] str = popedomModules[i].split(","); // 页面的数据是使用“，”分隔的
					SysPopedomPrivilege sysPopedomPrivilege = new SysPopedomPrivilege();

					/* 创建代表主键的对象 */
					SysPopedomPrivilegeId id = new SysPopedomPrivilegeId();
					id.setRoleId(roleId);
					id.setPopedomModule(str[0]);
					id.setPopedomPrivilege(str[1]);

					sysPopedomPrivilege.setId(id);

					sysPopedomPrivilegeDao.save(sysPopedomPrivilege);
				}
			}
		}
	}

	@Override
	public List<SysPopedomPrivilege> findSysPopedomPrivilegesByRoleId(String roleId) {
		if (StringUtils.isNotBlank(roleId)) {
			String whereHql = " and o.id.roleId=? ";
			Object[] params = { roleId };

			List<SysPopedomPrivilege> list = sysPopedomPrivilegeDao.findObjectsByConditionWithNoPage(whereHql, params);

			return list;
		}

		return null;
	}

	@Override
	public List<SysPopedomPrivilege> findAllSysPopedomPrivileges() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		orderby.put("o.id.roleId", "asc");

		return sysPopedomPrivilegeDao.findObjectsByConditionWithNoPage(orderby);
	}

	@Override
	public List<SysPopedomPrivilege> findAllSysPopedomPrivilegesCache() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		orderby.put("o.id.roleId", "asc");

		return sysPopedomPrivilegeDao.findObjectsByConditionWithNoPageCache(null, null, orderby);
	}
}
