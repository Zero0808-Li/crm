package cn.cuit.crm.junit;

import org.junit.Test;

import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.dao.ISysUserDao;
import cn.cuit.crm.domain.SysRole;
import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.domain.SysUserGroup;
import cn.cuit.crm.util.MD5keyBean;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  因为SysUser字段过多，所以测试下*/
public class TestSysUser {

	@Test
	public void testSaveSysUser() {
		ISysUserDao sysUserDao = (ISysUserDao) ServiceProvinder.getService(ISysUserDao.SERVICE_NAME);
		
		SysUser sysUser = new SysUser();
		
		sysUser.setName("cuit");
		sysUser.setCnname("系统管理员");
		
		SysUserGroup sysUserGroup = new SysUserGroup();
		sysUserGroup.setId(20);
		sysUser.setSysUserGroup(sysUserGroup);
		
		SysRole sysRole = new SysRole();
		sysRole.setId("402881e43250ee0b013250eee1450002");
		sysUser.setSysRole(sysRole);
		
		/* 密码 */
		MD5keyBean m = new MD5keyBean();
		String md5 = m.getkeyBeanofStr("123456");
		
		sysUser.setPassword(md5);
		
		sysUserDao.save(sysUser);
	}
}
