package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.bean.CompanySearch;
import cn.cuit.crm.domain.Company;
import cn.cuit.crm.domain.SysUser;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ICompanyService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.CompanyServiceImpl";

	/**
	 * @param string
	 * @return
	 */
	public abstract String getCodeByTabName(String tabName);

	/**
	 * 保存客户信息
	 * @param cuSysUser  处理日志用的
	 * @param company
	 */
	public abstract void saveCompany(SysUser cuSysUser, Company company);

	/**
	 * 通过条件查询客户
	 * @param curSysUser  当前用户
	 * @param companySearch 查询条件
	 * @return
	 */
	public abstract List<Company> findCompanysByCondition(SysUser curSysUser, CompanySearch companySearch);

	/**
	 * 通过客户id获取客户信息
	 * @param id
	 * @return
	 */
	public abstract Company findCompanyById(Integer id);

	/**
	 * 修改客户信息
	 * @param cuSysUser
	 * @param company
	 */
	public abstract void updateCompany(SysUser cuSysUser, Company company);

	/**
	 * 批量删除客户信息
	 * @param ids
	 */
	public abstract void deleteCompanyByIds(Integer[] ids);

	/**
	 * 增减共享
	 * @param s_module  模块名称
	 * @param id 客户id
	 * @param uids 用户id的数组
	 */
	public abstract void addUpdateShareSetOne(String s_module, Integer id, Integer[] uids);

	/**
	 * 减少共享
	 * @param s_module 模块名称
	 * @param id 客户id
	 * @param uids 用户id的数组
	 */
	public abstract void minusUpdateShareSetOne(String s_module, Integer id, Integer[] uids);

	/**
	 * 取消共享
	 * @param id 客户id
	 * @param s_module 模块名称
	 */
	public abstract void updateShareCancelOne(Integer id, String s_module);

	/**
	 * 通过客户id获取对应的共享的用户信息
	 * @param id
	 * @return
	 */
	public abstract List<SysUser> findSysUsersBySharedIds(Integer id);

	/**
	 * 修改客户的下次联系时间
	 * @param ids  客户的id（数组）
	 * @param next_touch_date 下次联系时间
	 */
	public abstract void updateNextTouchTime(Integer[] ids, java.sql.Date next_touch_date);

	/**
	 * 变更客户的所属人
	 * @param ids
	 * @param new_owner
	 */
	public abstract void changeHandler(Integer[] ids, Integer new_owner);

}
