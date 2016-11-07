package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.domain.SysMenuPrivilege;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ISysMenuPrivilegeService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysMenuPrivilegeServiceImpl";

	/**
	 * @param roleId
	 * @param menuModules
	 */
	public abstract void updateMenu(String roleId, String[] menuModules);

	/**
	 * @param roleId
	 * @return
	 */
	public abstract List<SysMenuPrivilege> findSysMenuPrivilegesByRoleId(String roleId);

	/**
	 * @return
	 */
	public abstract List<SysMenuPrivilege> findAllSysMenuPrivileges();

	/**
	 * @return
	 */
	public abstract List<SysMenuPrivilege> findAllSysMenuPrivilegesCache();

}
