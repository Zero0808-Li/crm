package cn.cuit.crm.junit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cuit.crm.dao.ISysUserGroupDao;
import cn.cuit.crm.domain.SysUserGroup;

/**
 * 
 * <P>Desc: 测试Dao层 </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
public class TestSysUserGroupDao {
	
	@Test
	/* 这个方法有问题,原因请参考（JDK版本的问题，spring2.5要在JDK5/6/7下才好使，同时将项目的编译级别改为对应的）
	 * http://stackoverflow.com/questions/23813369/spring-java-error-namespace-element-annotation-config-on-jdk-1-5-and-high
	 */
	public void testSave() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ctx.getBean(ISysUserGroupDao.SERVICE_NAME);
		SysUserGroup sysUserGroup = new SysUserGroup();
		sysUserGroup.setName("销售部02");
		sysUserGroup.setPrincipal("部门负责人02");
		sysUserGroup.setIncumbent("部门职能02");
		sysUserGroup.setRemark("备注：测试数据02");
		sysUserGroupDao.save(sysUserGroup);
	}
	
	@Test
	public void testUpdate() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ctx.getBean(ISysUserGroupDao.SERVICE_NAME);
		SysUserGroup sysUserGroup = new SysUserGroup();
		sysUserGroup.setId(2);
		sysUserGroup.setName("销售部Update");
		sysUserGroup.setPrincipal("Tom01");
		sysUserGroup.setIncumbent("TTT01");
		sysUserGroup.setRemark("更新测试");
		sysUserGroupDao.update(sysUserGroup);
	}
	
	@Test
	public void testFindSysUserGroupById() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ctx.getBean(ISysUserGroupDao.SERVICE_NAME);
		/* 使用Serializable可以的原因的Integer的父类Number，即String、Long都是是实现了Serializable接口的 ， 所以在Hibernate的查询中Serializable接口是最常用的类型*/
		Serializable id = 20;
		SysUserGroup sysUserGroup = sysUserGroupDao.findObjectById(id);
		System.out.println(sysUserGroup);
	}
	
	@Test
	public void testDeleteByIds() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ctx.getBean(ISysUserGroupDao.SERVICE_NAME);
		/* 使用Serializable可以的原因的Integer的父类Number，即String、Long都是是实现了Serializable接口的 ， 所以在Hibernate的查询中Serializable接口是最常用的类型*/
		Serializable[] ids = {23, 24, 25};
		sysUserGroupDao.deleteByIds(ids);
	}
	
	@Test
	public void testDeleteCollection() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ctx.getBean(ISysUserGroupDao.SERVICE_NAME);
		
		SysUserGroup s1 = new SysUserGroup();
		s1.setId(28);
		s1.setName("销售部Update");
		
		SysUserGroup s2 = new SysUserGroup();
		s2.setId(29);
		s2.setName("销售部Update");
		
		List<SysUserGroup> list = new ArrayList<SysUserGroup>();
		list.add(s1);
		list.add(s2);
		
		sysUserGroupDao.deleteAllObject(list);
	}
}
