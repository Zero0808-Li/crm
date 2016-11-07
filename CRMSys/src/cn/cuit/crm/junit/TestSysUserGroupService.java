package cn.cuit.crm.junit;


import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.SysUserGroup;
import cn.cuit.crm.service.ISysUserGroupService;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
public class TestSysUserGroupService {
	/* 使用自己封装的容器来实现ServiceProvinder*/
	@Test
	public void testSave() {
		/* sysUserGroupService获取的值是一个代理对象 */
		ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvinder.getService(ISysUserGroupService.SERVICE_NAME);
		SysUserGroup sysUserGroup = new SysUserGroup();
		sysUserGroup.setName("销售部03");
		sysUserGroup.setPrincipal("部门负责人03");
		sysUserGroup.setIncumbent("部门职能03");
		sysUserGroup.setRemark("备注：测试数据03");
		sysUserGroupService.saveSysUserGroup(sysUserGroup);
	}
	
	@Test
	public void testSavex() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ctx.getBean(ISysUserGroupService.SERVICE_NAME);
		SysUserGroup sysUserGroup = new SysUserGroup();
		sysUserGroup.setName("销售部04");
		sysUserGroup.setPrincipal("部门负责人04");
		sysUserGroup.setIncumbent("部门职能04");
		sysUserGroup.setRemark("备注：测试数据04");
		sysUserGroupService.saveSysUserGroup(sysUserGroup);
	}
	
	@Test
	/* 该测试类模拟的是Struts2的action层，在该测试类中调用业务层*/
	public void testFindObjectsByConditionWithNoPage() {
		ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvinder.getService(ISysUserGroupService.SERVICE_NAME);
		//条件01，假设的条件
		/* 获取部门名称 */
		String name = "销售部";
		//条件02
		/* 获取部门负责人 */
		String principal = "tom";
		/**
		 * SQL: select * from sys_user_group o where o.name like '%销售部%' and o.principal='tom' order by o.id
		 * HQL: select * from SysUserGroup o where o.name like '%销售部%' and o.principal='tom' order by o.id
		 *		select * from SysUserGroup o where 这半段：在dao层产生
		 *		o.name like '%销售部%' and o.principal='tom' order by o.id 这半段：条件等在业务层组装
		 */
		//这里是模拟，通过条件查询name, principal，我们可以封装一个查询条件的对象SysUserGroupSearch传进去，参考SysUserGroupServiceImpl类
		List<SysUserGroup> list = sysUserGroupService.findSysUserGroups(name, principal);
		System.out.println(list);
	}
}
