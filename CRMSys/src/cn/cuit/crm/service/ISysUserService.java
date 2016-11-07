package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.bean.SysUserSearch;
import cn.cuit.crm.domain.SysUser;

/**
 * @author CUIT-LLB
 *
 */

/* 说明：  */
public interface ISysUserService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysUserServiceImpl";

	/**
	 * 通过用户名和密码查询用户信息
	 * @param name
	 * @param password
	 * @return
	 */
	public abstract SysUser findSysUserByNameAndPassword(String name, String password);

	/**
	 * 保存用户
	 * @param sysUser
	 */
	public abstract void saveSysUser(SysUser sysUser);

	/**
	 * 通过条件查询用户信息
	 * @param sysUserSearch
	 * @return
	 */
	public abstract List<SysUser> findSysUsersByCondition(SysUserSearch sysUserSearch);

	/**
	 * 通过ID批量删除用户
	 * @param ids
	 */
	public abstract void deleteSysUsersByIds(Integer[] ids);

	/**
	 * 通过ID启用用户
	 * @param ids
	 */
	public abstract void enableSysUsersByIds(Integer[] ids);

	/**
	 * 通过ID停用用户
	 * @param ids
	 */
	public abstract void disableSysUsersByIds(Integer[] ids);

	/**
	 * 通过ID查询用户信息
	 * @param id
	 * @return
	 */
	public abstract SysUser findSysUserById(Integer id);

	/**
	 * 更新用户信息
	 * @param newSysUser
	 */
	public abstract void updateSysUser(SysUser newSysUser);

	/**
	 * 查询所有的用户
	 * @return
	 */
	public abstract List<SysUser> findAllSysUsers();

}
