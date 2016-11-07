package cn.cuit.crm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import cn.cuit.crm.domain.SysUser;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 处理session范围的工具类,通用的 */
public class SessionUtil {

	/**
	 * 处理验证码
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isCheckNum(HttpServletRequest request) {
		/* 获取已经存在的Session对象，因为在生成验证码的时候我们已经把验证码存放在了Session域中 */
		HttpSession session = request.getSession(false);

		if (session == null) {
			return false; // 返回false都说明验证码有误
		}

		String check_number_key = (String) session.getAttribute("CHECK_NUMBER_KEY");

		if (StringUtils.isBlank(check_number_key)) {
			return false;
		}

		/* 获取JSP页面文本框中输入的验证码值 */
		String saved = request.getParameter("checkNum");

		if (StringUtils.isBlank(saved)) {
			return false;
		}

		/* 比对session中存放的值和页面文本框输入的验证码的值 */
		return check_number_key.equalsIgnoreCase(saved);
	}

	/**
	 * 保存当前登录的用户信息到Session
	 * @param request
	 * @param sysUser
	 */
	public static void setSysUserToSession(HttpServletRequest request, SysUser sysUser) {
		HttpSession session = request.getSession();

		if (sysUser == null) {
			return;
		}
		
		session.setAttribute("sysUserKey", sysUser);
	}

	/**
	 * 从已经存在的Session中获取当前登录用户的信息
	 * @param request
	 * @return
	 */
	public static SysUser getSysUserFromSession(HttpServletRequest request) {
		/* 获取已经存在的session */
		HttpSession session = request.getSession(false);

		if (session == null) {
			return null;
		}
		
		return (SysUser) session.getAttribute("sysUserKey");
	}

}
