package cn.cuit.crm.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cuit.crm.dao.ICommonDao;
import cn.cuit.crm.util.GenericClass;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 * @param <T>
 */

/* 说明：通用DAO的实现,并继承HibernateDaoSupport，它的需要一个sessionFactory（他是由Spring提供的） */
public abstract class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {

	/* 这行代码极其重要 , 获取父类ICommonDao<T>的泛型类型T的真实类型，通过工具抽取的工具类 */
	@SuppressWarnings({ "rawtypes" })
	private Class entityClass = GenericClass.getGenericClass(this.getClass());

	/* 通过重写方法来注入，这里要修改名称，也不叫重写（因为父类源码中setSessionFactory方法是final的），只是参考其源代码写的 */
	@Resource(name = "sessionFactory")
	public void setSessionFactoryDI(SessionFactory sessionFactory) {
		/* 调用父类HibernateDaoSupport的setSessionFactory方法来注入sessionFactory（请看源码），注入过程中父类中会初始化HibernateTemplate */
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(T entity) {
		/*
		 * 使用Spring提供的HibernateDaoSupport 模板来操作
		 * HibernateDaoSupport该类里面就有SessionFactory被注入
		 */
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectById(Serializable id) {
		if (id == null) {
			throw new RuntimeException("您要查找的" + id + "不能为空");
		}

		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	/* 批量删除，通过多个ID删除多条记录，一个也ID也可以删除，比较通用 */
	public void deleteByIds(Serializable... ids) {
		if (ids != null && ids.length > 0) {
			for (Serializable id : ids) {
				Object entity = this.getHibernateTemplate().get(entityClass, id);
				if (entity == null) {
					throw new RuntimeException("您要查找的" + id + "不存在");
				}
				this.getHibernateTemplate().delete(entity);
			}
		}
	}

	@Override
	/* 删除集合中的所有实体对象 */
	public void deleteAllObject(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	@Override
	/* 根据条件查询 */
	public List<T> findObjectsByConditionWithNoPage(String whereHql, final Object[] params,
			LinkedHashMap<String, String> orderby) {

		/* 组织select o from XXX o(别名) ，而且是动态的 ,如果没有条件就是直接查询出所有记录， o where 1=1的目的是为了拼接语句 */
		/***** 千万注意这里的select不能使用“*” 要是使用别名o，否则报hql语法错误 *****/
		String hql = "select o from " + entityClass.getSimpleName() + " o where 1=1 ";

		/* 在hql语句上组织查询条件 */
		if (StringUtils.isNotBlank(whereHql)) {
			hql = hql + whereHql;
		}

		/* 组织排序 */
		String orderbystr = buildOrderBy(orderby);
		hql = hql + orderbystr;

		/* 内部类只能访问final修饰的 */
		final String fhql = hql;
		System.out.println(fhql);

		/* 回调接口,doInHibernate方法的返回值就是this.getHibernateTemplate().execute方法的返回值 */
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(fhql);
				/* 抽取到外部 ，传递引用 */
				setParams(query, params);
				return query.list();
			}
		});
		return list;
	}

	@Override
	/* 上面方法的重载 */
	public List<T> findObjectsByConditionWithNoPage(String whereHql, Object... params) {
		return this.findObjectsByConditionWithNoPage(whereHql, params, null);
	}

	@Override
	/* 上面方法的重载 */
	public List<T> findObjectsByConditionWithNoPage() {
		return this.findObjectsByConditionWithNoPage(null, null, null);
	}

	@Override
	/* 上面方法的重载,支持排序 */
	public List<T> findObjectsByConditionWithNoPage(LinkedHashMap<String, String> orderby) {
		return this.findObjectsByConditionWithNoPage(null, null, orderby);
	}
	
	/* 设置HQL语句需要的参数 */
	private void setParams(Query query, Object[] params) {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}

	/*
	 * 业务层的组织方式，方便这里看 orderby.put("o.id", "asc"); orderby.put("o.name", "desc");
	 */
	/* 组织排序条件,排序条件可根据要求动态增减 */
	private String buildOrderBy(LinkedHashMap<String, String> orderby) {
		StringBuffer sb = new StringBuffer("");
		if (orderby != null && !orderby.isEmpty()) {
			sb.append(" order by ");
			for (Map.Entry<String, String> em : orderby.entrySet()) {
				sb.append(em.getKey() + " " + em.getValue() + ",");
			}
			/* 去掉最后一个逗号 */
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	@Override
	public List<T> findObjectsByConditionWithNoPageCache(String whereHql, final Object[] params,
			LinkedHashMap<String, String> orderby) {
		/* 组织select o from XXX o(别名) ，而且是动态的 ,如果没有条件就是直接查询出所有记录 */
		String hql = "select o from " + entityClass.getSimpleName() + " o where 1=1 ";

		/* 在hql语句上组织查询条件 */
		if (StringUtils.isNotBlank(whereHql)) {
			hql = hql + whereHql;
		}

		/* 组织排序 */
		String orderbystr = buildOrderBy(orderby);
		hql = hql + orderbystr;

		/* 内部类只能访问final修饰的 */
		final String fhql = hql;

		/* 回调接口 */
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(fhql);
				/* 使用查询缓存，必须加这行代码 */
				query.setCacheable(true);
				/* 抽取到外部 ，传递引用 */
				setParams(query, params);
				return query.list();
			}
		});
		return list;
	}

}
