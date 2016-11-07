package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.bean.SysRoleSearch;
import cn.cuit.crm.domain.SysRole;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ISysRoleService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysRoleServiceImpl";

	/**
	 * @param sysRole
	 */
	public abstract void saveSysRole(SysRole sysRole);

	/**
	 * @通过条件查询权限组的信息
	 * @param sysRoleSearch封装的是查询条件
	 * @return
	 */
	public abstract List<SysRole> findSysRoles(SysRoleSearch sysRoleSearch);

	/**
	 * @param id
	 * @return
	 */
	public abstract SysRole findSysRoleById(String id);

	/**
	 * @param sysRole
	 */
	public abstract void updateSysRole(SysRole sysRole);

	/**
	 * @批量删除
	 * @param ids
	 */
	public abstract void deleteSysRolesByIds(String...ids);

	/**
	 * @return
	 */
	public abstract List<SysRole> findAllSysRoles();

}
