package cn.cuit.crm.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.bean.SysUserGroupSearch;
import cn.cuit.crm.dao.ISysUserGroupDao;
import cn.cuit.crm.domain.SysUserGroup;
import cn.cuit.crm.service.ISysUserGroupService;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：业务层,都是采用面向接口的 */
/* 类上面的事务一般都是只读的，在具体方法上再去修改 */
@Transactional(readOnly=true)
@Service(ISysUserGroupService.SERVICE_NAME)
public class SysUserGroupServiceImpl implements ISysUserGroupService {
	/* 注入ISysUserGroupDao，写的是接口，而实际注入的是他的实现类 */
	@Resource(name=ISysUserGroupDao.SERVICE_NAME)
	private ISysUserGroupDao sysUserGroupDao;
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void saveSysUserGroup(SysUserGroup sysUserGroup) {
		sysUserGroupDao.save(sysUserGroup);
	}

	@Override
	public List<SysUserGroup> findSysUserGroups(SysUserGroupSearch sysUserGroupSearch) {
		/* 搜索条件对象为空，在Action层已经new了一个SysUserGroupSearch对象，其实下面的检查可以不用 */
		if (sysUserGroupSearch == null) {
			throw new RuntimeException("传递给业务层部门查询条件的对象为空");
		}
		
		/* 组织查询条件 */
		String whereHql = "";
		
		/* 定义封装查询条件的参数的List */
		List<String> paramList = new ArrayList<String>();
		
		/* 有搜索条件 */
		if (StringUtils.isNotBlank(sysUserGroupSearch.getName())) {
			whereHql = " and o.name like ? ";
			paramList.add("%"+ sysUserGroupSearch.getName().trim() +"%");
		}
		
		Object[] params = paramList.toArray();
		
		/* 组织排序 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id", "asc");
		
		return sysUserGroupDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
	}

	
	/**
	 * 测试方法
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SysUserGroup> findSysUserGroups(String name, String principal) {

		/*分析：
		 * SQL语句出现在业务（客户要求的）层是不合理的，它最好在DAO层
		 * HQL语句是面向对象的，可以出现在业务层，我们最终操作的是JavaBean
		 * Sql语句：
		 * 		select * from sys_user_group o where o.name like '%销售部%' and o.principal='Tom' order by o.id asc, o.name desc;
		 * Hql语句:
		 * 		select * from SysUserGroup o where 在DAO层产生
		 * 		o.name like '%销售部%' and o.principal='Tom' 在业务层组织产生（因为这些都是客户要求的，属于业务的一部分）
		 * 	    order by o.id asc, o.name desc; 在业务层组织产生  
		 * 
		 */
		
		/* 在业务层组织HQL语句 ， 封装的是查询条件*/
		String whereHql = "";
		/* 查询条件需要的参数,如果不需要条件查询了，只需要把下面的代码注释到就可以了,这里也是一个好的测试，如果后台报错也有可能是条件传入有误，将条件注释在测试 */
		List paramList = new ArrayList<>();
		if (StringUtils.isNotBlank(name)) {
			whereHql = " and o.name like ? ";
			paramList.add("%"+ name +"%");
		}
		
		if (StringUtils.isNotBlank(principal)) {
			whereHql = whereHql + " and o.principal = ? ";
			paramList.add(principal);
		}
		
		/* HQL中的?的替换参数 */
		Object[] params = paramList.toArray();
		
		/* 组织排序条件(HQL排序) */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("o.id", "asc");
		//orderby.put("o.name", "desc");
		
		/* 将组织的HQL语句发送给DAO层 */
		List<SysUserGroup> list = sysUserGroupDao.findObjectsByConditionWithNoPage(whereHql, params, orderby);
		
		return list;
	}
	
	
	@Override
	public SysUserGroup findSysUserGroupById(Integer id) {
		return sysUserGroupDao.findObjectById(id);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void updateSysUserGroup(SysUserGroup sysUserGroup) {
		sysUserGroupDao.update(sysUserGroup);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deleteSysUserGroupsByIds(Integer... ids) {
		sysUserGroupDao.deleteByIds((Serializable[])ids);
	}

	@Override
	public List<SysUserGroup> findAllSysUserGroups() {
		/* 组织排序 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.id", "asc");
		return sysUserGroupDao.findObjectsByConditionWithNoPage(orderby);
	}

}
