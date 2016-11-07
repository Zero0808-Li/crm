package cn.cuit.crm.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

import cn.cuit.crm.annotation.Limit;
import cn.cuit.crm.bean.SysRoleSearch;
import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.SysMenu;
import cn.cuit.crm.domain.SysMenuPrivilege;
import cn.cuit.crm.domain.SysPopedom;
import cn.cuit.crm.domain.SysPopedomPrivilege;
import cn.cuit.crm.domain.SysRole;
import cn.cuit.crm.service.ISysMenuPrivilegeService;
import cn.cuit.crm.service.ISysMenuService;
import cn.cuit.crm.service.ISysPopedomPrivilegeService;
import cn.cuit.crm.service.ISysPopedomService;
import cn.cuit.crm.service.ISysRoleService;
import cn.cuit.crm.web.form.SysRoleForm;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SysRoleAction extends BaseAction implements ModelDriven<SysRoleForm> {

	/* 获取操作权限组的业务层对象 */
	private ISysRoleService  sysRoleService = (ISysRoleService) ServiceProvinder.getService(ISysRoleService.SERVICE_NAME);
	/* 获取操作功能的业务层对象 */
	private ISysPopedomService  sysPopedomService = (ISysPopedomService) ServiceProvinder.getService(ISysPopedomService.SERVICE_NAME);
	/* 获取操作权限表的业务层对象 */
	private ISysPopedomPrivilegeService  sysPopedomPrivilegeService = (ISysPopedomPrivilegeService) ServiceProvinder.getService(ISysPopedomPrivilegeService.SERVICE_NAME);
	/* 获取操作菜单表的业务层对象 */
	private ISysMenuService  sysMenuService = (ISysMenuService) ServiceProvinder.getService(ISysMenuService.SERVICE_NAME);
	/* 获取操作菜单权限的业务层对象 */
	private ISysMenuPrivilegeService  sysMenuPrivilegeService = (ISysMenuPrivilegeService) ServiceProvinder.getService(ISysMenuPrivilegeService.SERVICE_NAME);
	
	/* 模型驱动 */
	private SysRoleForm sysRoleForm = new SysRoleForm();

	/*
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public SysRoleForm getModel() {
		return sysRoleForm;
	}

	/**
	 * 更新菜单权限
	 * @return
	 */
	/* 这里的privilege的名称要和数据库里面的一样，由于这里方法名就和数据库一样所以就是updateMenu，不是说是和方法名一样 */
	@Limit(module="role", privilege="updateMenu")
	public String updateMenu() {
		/* 获取权限组id */
		String roleId = request.getParameter("roleId");
		if (StringUtils.isNotBlank(roleId)) {
			/* 通过权限组id查询权限组的信息 */
			SysRole sysRole = sysRoleService.findSysRoleById(roleId);
			
			/* 将权限组信息放在request */
			request.setAttribute("sysRole", sysRole);
			
			/* 获取选中的（菜单操作权限）复选框的值 */
			String[] menuModules = request.getParameterValues("menuModule");
			
			/* 获取设置菜单权限的业务层对象，保存更新 */
			sysMenuPrivilegeService.updateMenu(roleId, menuModules);
			
			return "updateMenu";
		}
		
		return null;
	}
	
	/**
	 * 显示菜单（要回显）
	 * @return
	 */
	/* 这里的privilege的名称要和数据库里面的一样，由于这里方法名就和数据库一样所以就是updateMenu，不是说是和方法名一样 */
	@Limit(module="role", privilege="listMenu")
	public String listMenu() {
		/* 获取权限组id */
		String roleId = request.getParameter("roleId");
		if (StringUtils.isNotBlank(roleId)) {
			/* 通过权限组id查询权限组的信息 */
			SysRole sysRole = sysRoleService.findSysRoleById(roleId);
			
			/* 将权限组信息放在request */
			request.setAttribute("sysRole", sysRole);
		
			/* 查询sys_menu表的所有数据  */
			List<SysMenu> sysMenus = sysMenuService.findAllSysMenus();
			
			request.setAttribute("sysMenus", sysMenus);
			
			/* 通过菜单权限组的id查询sys_menu_privilege表 */
			List<SysMenuPrivilege> sysMenuPrivileges =sysMenuPrivilegeService.findSysMenuPrivilegesByRoleId(roleId); 
			request.setAttribute("sysMenuPrivileges", sysMenuPrivileges);
			
			return "listMenu";
		}

		return null;
	}
	
	@Limit(module="role", privilege="updatePopedom")
	public String updatePopedom() {
		/* 获取角色的id */
		String roleId = request.getParameter("roleId");
		/* 通过id获取角色对象 */
		SysRole sysRole = sysRoleService.findSysRoleById(roleId);
		/* 将角色对象放在域对象中，用于显示角色对应的操作 */
		request.setAttribute("sysRole", sysRole);
		
		/* 获取复选框的值 */
		String[] popedomModules = request.getParameterValues("popedomModule");
		
		sysPopedomPrivilegeService.updatePopedom(roleId, popedomModules);
		
		/* 查询该权限组包含的权限（这里边是以权限组（roleId对应的权限组）为基准来查询权限）用于更新后回显 */
		List<SysPopedomPrivilege> sysPopedomPrivileges = sysPopedomPrivilegeService.findSysPopedomPrivilegesByRoleId(roleId);
		request.setAttribute("sysPopedomPrivileges", sysPopedomPrivileges);

		return "updatePopedom";
	}
	
	/**
	 * 显示系统的所有操作功能
	 * @return
	 */
	@Limit(module="role", privilege="listPopedom")
	public String listPopedom() {
		/* 获取角色的id */
		String roleId = request.getParameter("roleId");
		/* 通过id获取角色对象 */
		SysRole sysRole = sysRoleService.findSysRoleById(roleId);
		/* 将角色对象放在域对象中，用于显示角色对应的操作 */
		request.setAttribute("sysRole", sysRole);
		
		/* 获取系统的所有的功能（数据来源于操作功能表） */
		List<SysPopedom> sysPopedoms = sysPopedomService.findAllSysPopedoms();
		request.setAttribute("sysPopedoms", sysPopedoms);
		
		/* 查询该权限组包含的权限 */
		List<SysPopedomPrivilege> sysPopedomPrivileges = sysPopedomPrivilegeService.findSysPopedomPrivilegesByRoleId(roleId);
		request.setAttribute("sysPopedomPrivileges", sysPopedomPrivileges);
		
		return "listPopedom";
	}
	
	/* 查询权限组的信息 */
	@Limit(module="role", privilege="list")
	public String list() {
		/* 实例化搜素添加对象 SysRoleSearch*/
		SysRoleSearch sysRoleSearch = new SysRoleSearch();
		sysRoleSearch.setName(sysRoleForm.getName());
		
		/* 调用业务层方法查询 */
		List<SysRole> sysRoles = sysRoleService.findSysRoles(sysRoleSearch);
		request.setAttribute("sysRoles", sysRoles);
		
		return "list";
	}

	/* 显示添加权限组页面 */
	//@Limit(module="role", privilege="add")
	public String add() {

		return "add";
	}

	/* 保存权限组信息 */
	public String save() throws IllegalAccessException, InvocationTargetException {
		/* 实例化对应的PO对象 */
		SysRole sysRole = new SysRole();

		/* 将VO对象的数据填充给PO */
		BeanUtils.copyProperties(sysRole, sysRoleForm);

		/* 调用权限组的业务层 */
		sysRoleService.saveSysRole(sysRole);
		
		return "listAction";
	}
	
	/* 显示权限组修改页面 */
	@Limit(module="role", privilege="edit")
	public String edit() throws IllegalAccessException, InvocationTargetException {
		/* 获取权限组的ID */
		String id = request.getParameter("id");
		
		/* 根据ID号查询出权限组对象信息 */
		SysRole sysRole = sysRoleService.findSysRoleById(id);
	
		/* 放置权限组对象中的值到模型驱动中，用于回显 (PO -> VO)*/
		BeanUtils.copyProperties(sysRoleForm, sysRole);
		
		return "edit";
	}
	
	@Limit(module="role", privilege="update")
	public String update() throws IllegalAccessException, InvocationTargetException {
		/* 实例化对应的PO对象 */
		SysRole sysRole = new SysRole();

		/* 将VO对象的数据填充给PO */
		BeanUtils.copyProperties(sysRole, sysRoleForm);

		/* 调用权限组的业务层 */
		sysRoleService.updateSysRole(sysRole);
		
		return "listAction";
	}
	
	@Limit(module="role", privilege="delete")
	public String delete() {
		/* 获取删除的权限组的IDS */
		String[] ids = request.getParameterValues("ids");
		
		if (ids != null && ids.length > 0) {
			sysRoleService.deleteSysRolesByIds(ids);
			
			return "listAction";
		}
		
		return null;
	}
}
