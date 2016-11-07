package cn.cuit.crm.interceptor;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.cuit.crm.annotation.Limit;
import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.SysPopedomPrivilege;
import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.service.ISysPopedomPrivilegeService;
import cn.cuit.crm.util.SessionUtil;

/**
 * 
 * <P>
 * Desc:
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：权限拦截器 */
@SuppressWarnings("serial")
public class LimitInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		/* 获取请求的Action对象 */
		Object action = invocation.getAction();

		/* 获取请求的方法的名称 */
		String methodName = invocation.getProxy().getMethod();

		/* 获取Action中方法的封装类 (Action中的方法都是没有参数的)所以这里参数为null */
		Method method = action.getClass().getMethod(methodName, null);

		/* 获取request对象 */
		HttpServletRequest request = ServletActionContext.getRequest();

		/* 检查请求方法的注解 */
		boolean flag = isCheckLimit(request, method);

		if (!flag) {
			/* 没有权限 */
			return "popmsg_popedom";
		}

		/* 有权限，调用Action中的对应请求方法 ,两种方式 */
		// method.invoke(action, null);
		String returnValue = invocation.invoke();

		return returnValue;
	}

	/**
	 * @param request
	 * @param method
	 * @return
	 */
	private boolean isCheckLimit(HttpServletRequest request, Method method) {
		if (method == null) {
			return false; // 方法不存在，没有权限
		}

		/* 获取当前登录用户 */
		SysUser sysUser = SessionUtil.getSysUserFromSession(request);
		if (sysUser == null) {
			return false;
		}

		if (sysUser.getSysRole() == null) {
			return false;
		}

		/* 获取当前登录用户的权限组ID */
		String roleId = sysUser.getSysRole().getId();

		/* 处理注解,判断方法上是否存在注解 */
		boolean isAnnotationPresent = method.isAnnotationPresent(Limit.class);
		if (!isAnnotationPresent) {
			return false; // 不存在注解
		}

		/* 存在注解 */
		Limit limit = method.getAnnotation(Limit.class);

		/* 获取注解的属性值 */
		String module = limit.module(); // 模块名称
		String privilege = limit.privilege(); // 操作名称

		/*
		 * 如果登陆用户的角色权限组ID+Limit注解上的module、privilege在sys_popedom_privilege表存在，
		 * flag为true，如果不存在flag为false
		 */
		boolean flag = false;

		/* 查询sys_popedom_privilege表的所有数据(以后会放到二级缓存中，这样效率就高了) */
		ISysPopedomPrivilegeService sysPopedomPrivilegeService = (ISysPopedomPrivilegeService) ServiceProvinder
				.getService(ISysPopedomPrivilegeService.SERVICE_NAME);
		//List<SysPopedomPrivilege> list = sysPopedomPrivilegeService.findAllSysPopedomPrivileges();
		List<SysPopedomPrivilege> list = sysPopedomPrivilegeService.findAllSysPopedomPrivilegesCache();
		if (limit != null && list.size() > 0) {
			/* 遍历比对 */
			for (int i = 0; i < list.size(); i++) {
				SysPopedomPrivilege s = list.get(i);
				if (s != null) {
					boolean flag01 = roleId.equals(s.getId().getRoleId());
					boolean flag02 = module.equals(s.getId().getPopedomModule());
					boolean flag03 = privilege.equals(s.getId().getPopedomPrivilege());
					
					if (flag01 && flag02 && flag03) {
						flag = true;
						break;
					}
				}
			}
		}

		return flag;
	}

}
