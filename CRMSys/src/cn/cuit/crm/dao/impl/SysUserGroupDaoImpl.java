package cn.cuit.crm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.cuit.crm.dao.ISysUserGroupDao;
import cn.cuit.crm.domain.SysUserGroup;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 用于操作SysUserGroup的DAO实现 */
/* 该注解就相当于配置了一个bean，获取名称为 ISysUserGroupDao.SERVICE_NAME*/
@Repository(ISysUserGroupDao.SERVICE_NAME)
public class SysUserGroupDaoImpl extends CommonDaoImpl<SysUserGroup>implements ISysUserGroupDao {

	/* 像这种通用的方法，在父类CommonDaoImpl中已经实现，本DAO实现拥有了这些方法,所有下面的方法可以不要了 */
	/*
		@Override
		public void save(SysUserGroup entity) {
		
		}
	*/
}
