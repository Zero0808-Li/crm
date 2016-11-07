package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.bean.SysUserGroupSearch;
import cn.cuit.crm.domain.SysUserGroup;

/**
 * <P>Desc: 部门业务层接口 </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
public interface ISysUserGroupService {
	
	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysUserGroupServiceImpl";
	
	public abstract void saveSysUserGroup(SysUserGroup sysUserGroup);

	/**
	 * @测试方法
	 * @param name
	 * @param principal
	 * @return
	 */
	public abstract List<SysUserGroup> findSysUserGroups(String name, String principal);
	
	/**
	 * @按照条件进行查询 
	 * @param sysUserGroupSearch（封装的查询条件）
	 * @return
	 */
	public abstract List<SysUserGroup> findSysUserGroups(SysUserGroupSearch sysUserGroupSearch);

	/**
	 * @通过ID查询部门信息
	 * @param id
	 * @return
	 */
	public abstract SysUserGroup findSysUserGroupById(Integer id);

	/**
	 * @保存更新的部门信息
	 * @param sysUserGroup
	 */
	public abstract void updateSysUserGroup(SysUserGroup sysUserGroup);

	/**
	 * @通过ID可批量删除部门信息
	 * @param ids
	 */
	public abstract void deleteSysUserGroupsByIds(Integer... ids);

	/**
	 * @查询所有的部门
	 * @return
	 */
	public abstract List<SysUserGroup> findAllSysUserGroups();
}
