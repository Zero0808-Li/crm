package cn.cuit.crm.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

import org.apache.commons.lang.StringUtils;

import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.SysMenuPrivilege;
import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.service.ISysMenuPrivilegeService;
import cn.cuit.crm.util.SessionUtil;


/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  自定义标签类，用于菜单权限的判断，动态输入登录者拥有的菜单权限对应的左侧的菜单类别 */
public class CheckMenuTag implements SimpleTag {

	private PageContext pageContext;
	private JspFragment jspFragment; //代表标签体
	/* 标签的属性 */
	private String module;
	private String privilege;
	
	public void setModule(String module) {
		this.module = module;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public void doTag() throws JspException, IOException {
		/* 获取request对象 */
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		/* 获取当前登录用户 */
		SysUser sysUser = SessionUtil.getSysUserFromSession(request);
		if (sysUser == null) {
			return ;
		}
		if (sysUser.getSysRole() == null) {
			return ;
		}
		
		/* 获取当前登录用户对应的菜单的权限组ID */
		String roleId = sysUser.getSysRole().getId();
		
		/* 获取菜单权限操作的业务层（sys_menu_privilege） */
		ISysMenuPrivilegeService  sysMenuPrivilegeService = (ISysMenuPrivilegeService) ServiceProvinder.getService(ISysMenuPrivilegeService.SERVICE_NAME);

		/* 获取菜单操作权限的所有数据（sys_menu_privilege），返回一个List集合，将来使用二级缓存 */
		//List<SysMenuPrivilege> list = sysMenuPrivilegeService.findAllSysMenuPrivileges();
		List<SysMenuPrivilege> list = sysMenuPrivilegeService.findAllSysMenuPrivilegesCache();
	
		/* 遍历集合 */
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SysMenuPrivilege s = list.get(i);
				if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(module) && StringUtils.isNotBlank(privilege)) {
					/* 比对权限id + 模块名称 + 操作名称 */
					boolean flag01 = roleId.equals(s.getId().getRoleId());
					boolean flag02 = module.equals(s.getId().getMenuModule());
					boolean flag03 = privilege.equals(s.getId().getMenuPrivilege());
					if (flag01 && flag02 && flag03) {
						/* 如果在List中存在，输出标签体 */
						this.jspFragment.invoke(pageContext.getOut());
						//this.jspFragment.invoke(null); //或者null默认就是pageContext.getOut()
						break;
					}
				}
			}
		}
	}

	@Override
	public JspTag getParent() {

		return null;
	}

	@Override
	public void setJspBody(JspFragment jspFragment) {
		this.jspFragment = jspFragment;
	}

	@Override
	public void setJspContext(JspContext jspContext) {
		this.pageContext = (PageContext) jspContext;
	}

	@Override
	public void setParent(JspTag jspTag) {
	}

}
