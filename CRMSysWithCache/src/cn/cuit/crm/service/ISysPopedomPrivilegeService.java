package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.domain.SysPopedomPrivilege;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ISysPopedomPrivilegeService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysPopedomPrivilegeServiceImpl";

	/**
	 * 设置权限组的权限（即设置可以有哪些操作）
	 * @param roleId
	 * @param popedomModules
	 */
	public abstract void updatePopedom(String roleId, String[] popedomModules);

	/**
	 * 通过权限组的id获取该权限组包含的权限（sys_popedom_privilege表中的数据）
	 * @param roleId
	 * @return
	 */
	public abstract List<SysPopedomPrivilege> findSysPopedomPrivilegesByRoleId(String roleId);

	/**
	 * 查询所有的操作（sys_popedom_privilege表中的数据）
	 * @return
	 */
	public abstract List<SysPopedomPrivilege> findAllSysPopedomPrivileges();

	/**
	 *  查询所有的操作（sys_popedom_privilege表中的数据），使用二级缓存
	 * @return
	 */
	public abstract List<SysPopedomPrivilege> findAllSysPopedomPrivilegesCache();

}
