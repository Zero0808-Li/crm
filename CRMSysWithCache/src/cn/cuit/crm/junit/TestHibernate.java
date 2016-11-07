package cn.cuit.crm.junit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.cuit.crm.domain.SysUserGroup;

/**
 * 
 * <P>Desc: 测试hibernate连接数据库 </P>
 * @author CUITLLB
 * @version 1.0
 */

public class TestHibernate {
	@Test
	public void testHibernateConf() {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		SysUserGroup sysUserGroup = new SysUserGroup();
		sysUserGroup.setName("销售部01");
		sysUserGroup.setPrincipal("部门负责人01");
		sysUserGroup.setIncumbent("部门职能01");
		sysUserGroup.setRemark("备注：测试数据01");
		session.save(sysUserGroup);
		transaction.commit();
		session.close();
	}
}
