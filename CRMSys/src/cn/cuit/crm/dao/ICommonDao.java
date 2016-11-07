package cn.cuit.crm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 
 * <P>Desc: 最通用的DAO接口, 他有一些通用的一些抽象方法 </P>
 * @author CUITLLB
 * @version 1.0
 * @param <T>
 */
public interface ICommonDao<T> {

	public abstract void save(T entity);

	public abstract void update(T entity);

	public abstract T findObjectById(Serializable id);

	public abstract void deleteByIds(Serializable... ids);

	public abstract void deleteAllObject(Collection<T> entities);

	/* 方法的重载 */
	public abstract List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] params,
			LinkedHashMap<String, String> orderby);

	/* 方法的重载 */
	public abstract List<T> findObjectsByConditionWithNoPage(String whereHql, Object... params);

	/* 方法的重载 */
	public abstract List<T> findObjectsByConditionWithNoPage();

	/* 方法的重载 */
	public abstract List<T> findObjectsByConditionWithNoPage(LinkedHashMap<String, String> orderby);

	/* 使用缓存 */
	public abstract List<T> findObjectsByConditionWithNoPageCache(String whereHql, Object[] params,
			LinkedHashMap<String, String> orderby);
}
