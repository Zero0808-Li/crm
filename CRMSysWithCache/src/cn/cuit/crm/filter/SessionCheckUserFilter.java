package cn.cuit.crm.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.util.SessionUtil;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：检查用户是否登录的过滤器，在web.xml配置过滤器要注意顺序 */
public class SessionCheckUserFilter implements Filter {

	private List<String> list;

	@Override
	public void init(FilterConfig config) throws ServletException {
		list = new ArrayList<String>();
		/* 这些可以放在一个资源文件里，使用流来读取 */
		list.add("/image.jsp");
		list.add("/index.jsp");
		list.add("/WEB-INF/page/login.jsp"); 
		list.add("/sys/sysUserAction_isLogin.do");
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		/* 获取请求路径 */
		String path = request.getServletPath();
		
		/*
		 * 对 /image.jsp /index.jsp /WEB-INF/page/login.jsp
		 * /sys/sysUserAction_isLogin.do路径不进行拦截，直接放行(因为是登录)
		 */
		if (list != null && list.contains(path)) {
			chain.doFilter(request, response);
			return;
		}
		
		/* 否则从Session中获取当前登录用户 */
		SysUser sysUser = SessionUtil.getSysUserFromSession(request);
		
		/* 如果不为null，表明用户已经登录，直接放行 */
		if (sysUser != null) {
			chain.doFilter(request, response);
			return;
		}
		
		/* 如果获取的sysUser是null，表明用户没有登录，重定向到login.jsp登录页面 或者index.jsp页面*/
		if (sysUser == null) {
			response.sendRedirect(request.getContextPath());  //默认是index.jsp 也可以在后面加上 + "/index.jsp1"
		}
		
	}

}
