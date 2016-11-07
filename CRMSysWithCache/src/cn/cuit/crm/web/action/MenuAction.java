package cn.cuit.crm.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.SysMenu;
//import cn.cuit.crm.service.ISysMenuPrivilegeService;
import cn.cuit.crm.service.ISysMenuService;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：处理菜单的Action */
@SuppressWarnings("serial")
public class MenuAction extends BaseAction {
	/* 获取操作菜单表的业务层对象 */
	private ISysMenuService  sysMenuService = (ISysMenuService) ServiceProvinder.getService(ISysMenuService.SERVICE_NAME);
	
	/* 主页面头部 */
	public String top() {
		return "top";
	}

	/* 主页面左边菜单栏(要根据用户的对菜单的权限来动态的显示) */
	public String left() {
		/* 查询所有的菜单功能（来源于sys_menu表） */
		//List<SysMenu> sysMenus = sysMenuService.findAllSysMenus();
		List<SysMenu> sysMenus = sysMenuService.findAllSysMenusCache();
		
		/* 所有客户都要用 */
		ServletContext sc = ServletActionContext.getServletContext();
		sc.setAttribute("sysMenus", sysMenus);
		
		return "left";
	}
}
