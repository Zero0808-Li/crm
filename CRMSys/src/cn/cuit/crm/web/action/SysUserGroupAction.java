package cn.cuit.crm.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

import cn.cuit.crm.annotation.Limit;
import cn.cuit.crm.bean.SysUserGroupSearch;
import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.SysUserGroup;
import cn.cuit.crm.service.ISysUserGroupService;
import cn.cuit.crm.util.DataType;
import cn.cuit.crm.web.form.SysUserGroupForm;

/**
 * <P>
 * Desc:
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 利用VO对象SysUserGroupForm里面的值来填充实体, 模型驱动对象其实也可以放到BaseAction中，参考OA项目 */
@SuppressWarnings("serial")
public class SysUserGroupAction extends BaseAction implements ModelDriven<SysUserGroupForm> {

	private SysUserGroupForm sysUserGroupForm = new SysUserGroupForm();

	private ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvinder
			.getService(ISysUserGroupService.SERVICE_NAME);;

	@Override
	public SysUserGroupForm getModel() {
		return sysUserGroupForm;
	}

	/* 保存部门信息 */
	@Limit(module = "group", privilege = "save")
	public String save() throws IllegalAccessException, InvocationTargetException {
		/* 实例化对应的PO对象 */
		SysUserGroup sysUserGroup = new SysUserGroup();

		/* BeanUtils是有Spring提供的 将VO中的值填充到PO中 */
		BeanUtils.copyProperties(sysUserGroup, sysUserGroupForm);

		/*
		 * 获取业务层对象（这里不采用注入的方式来获取，否则Struts2就和Spring耦合在一起了，我们采用分离式（解耦方式）的方式），
		 * 即写一个简单的容器对Spring进行简单的封装，在该容器中加载beans.xml文件，创建Spring容器,
		 * 用我们自己对Spring封装的容器来提供服务
		 */
		// sysUserGroupService = (ISysUserGroupService)
		// ServiceProvinder.getService(ISysUserGroupService.SERVICE_NAME);

		/* 调用业务层保存PO对象的数据 */
		sysUserGroupService.saveSysUserGroup(sysUserGroup);

		return "listAction";
	}

	/* 保存部门更新信息 */
	@Limit(module = "group", privilege = "update")
	public String update() throws IllegalAccessException, InvocationTargetException {
		/* 实例化对应的PO对象 */
		SysUserGroup sysUserGroup = new SysUserGroup();

		/* BeanUtils是有Spring提供的 将VO中的值填充到PO中 */
		BeanUtils.copyProperties(sysUserGroup, sysUserGroupForm);

		/*
		 * 获取业务层对象（这里不采用注入的方式来获取，否则Struts2就和Spring耦合在一起了，我们采用分离式（解耦方式）的方式），
		 * 即写一个简单的容器对Spring进行简单的封装,用我们自己对Spring封装的容器来提供服务
		 */
		// sysUserGroupService = (ISysUserGroupService)
		// ServiceProvinder.getService(ISysUserGroupService.SERVICE_NAME);

		/* 调用业务层保存PO对象的数据 */
		sysUserGroupService.updateSysUserGroup(sysUserGroup);

		return "listAction";
	}

	/* 删除部门（可批量） */
	@Limit(module = "group", privilege = "delete")
	public String delete() {
		/* 获取部门IDS */
		String[] sids = request.getParameterValues("ids");
		Integer[] ids = DataType.converterStringArray2IntegerArray(sids);

		if (ids != null) {
			/* 删除 */
			sysUserGroupService.deleteSysUserGroupsByIds(ids);
			return "listAction";
		}
		return null;
	}

	/* 显示部门查询页面 */
	@Limit(module = "group", privilege = "list")
	public String list() {
		/* 实例化封装查询搜索条件的JavaBean(查询条件单独封装，增加独立性和重用性),有条件就按条件查询，
		 * 这里的有条件是指SysUserGroupSearch中的条件属性有值，没有条件就查询所有数据，
		 * 没有条件是指SysUserGroupSearch中的条件属性没有值，而不是指SysUserGroupSearch为空 */
		SysUserGroupSearch sysUserGroupSearch = new SysUserGroupSearch();
		sysUserGroupSearch.setName(sysUserGroupForm.getName());

		/* 调用业务层的方法查询(将查询条件传给业务层组织) */
		List<SysUserGroup> sysUserGroups = sysUserGroupService.findSysUserGroups(sysUserGroupSearch);
		request.setAttribute("sysUserGroups", sysUserGroups);

		return "list";
	}

	/* 显示部门信息修改页面（数据回显） */
	@Limit(module = "group", privilege = "edit")
	public String edit() throws IllegalAccessException, InvocationTargetException {
		/* 获取部门的id */
		String sid = sysUserGroupForm.getId(); // 或者使用request来获取

		if (StringUtils.isNotBlank(sid)) {
			Integer id = Integer.parseInt(sid);
			/* 调用业务层的方法通过部门的ID获取部门的信息 ，用于回显 */
			SysUserGroup sysUserGroup = sysUserGroupService.findSysUserGroupById(id);

			/* 处理部门修改页面要编辑的信息,放在栈顶，标签解析时自动在栈顶找值sysUserGroupForm就在栈顶 */
			BeanUtils.copyProperties(sysUserGroupForm, sysUserGroup);
			return "edit";
		}

		return null;
	}

	/* 显示部门信息添加页面 */
	@Limit(module = "group", privilege = "add")
	public String add() {
		return "add";
	}

}
