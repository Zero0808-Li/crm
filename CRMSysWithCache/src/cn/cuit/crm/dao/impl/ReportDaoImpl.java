package cn.cuit.crm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.cuit.crm.bean.ReportBean;
import cn.cuit.crm.dao.IReportDao;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Repository(IReportDao.SERVICE_NAME)
public class ReportDaoImpl extends CommonDaoImpl<ReportBean> implements IReportDao {

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<ReportBean> findReportBeans() {
		List<ReportBean> list = (List<ReportBean>) this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				/**
				 * SQL:
				 * 	select grade, count(*) from c_company group by grade;
				 * HQL:
				 * 	select o.grade, count(*) from Company o group by o.grade;
				 */
				//Hibernate的投影查询，返回值是一个数组,注意这里要写类的全路径
				String hql = "select new cn.cuit.crm.bean.ReportBean (o.grade, count(*)) from Company o group by o.grade";
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		return list;
	}
}
