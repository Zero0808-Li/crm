package cn.cuit.crm.dao;

import cn.cuit.crm.domain.SysUserGroup;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 用于操作SysUserGroup的DAO接口，他继承了通用DAO*/
public interface ISysUserGroupDao extends ICommonDao<SysUserGroup> {
	
	public final static String SERVICE_NAME = "cn.cuit.crm.dao.impl.SysUserGroupDaoImpl";
}
