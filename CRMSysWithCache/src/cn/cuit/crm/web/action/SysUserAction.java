package cn.cuit.crm.web.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.opensymphony.xwork2.ModelDriven;

import cn.cuit.crm.annotation.Limit;
import cn.cuit.crm.bean.SysUserSearch;
import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.SysRole;
import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.domain.SysUserGroup;
import cn.cuit.crm.service.ISysRoleService;
import cn.cuit.crm.service.ISysUserGroupService;
import cn.cuit.crm.service.ISysUserService;
import cn.cuit.crm.util.DataType;
import cn.cuit.crm.util.MD5keyBean;
import cn.cuit.crm.util.SQLDateConverter;
import cn.cuit.crm.util.SessionUtil;
import cn.cuit.crm.web.form.SysUserForm;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 人事管理Action */
@SuppressWarnings("serial")
public class SysUserAction extends BaseAction implements ModelDriven<SysUserForm> {

	private ISysUserService sysUserService = (ISysUserService) ServiceProvinder
			.getService(ISysUserService.SERVICE_NAME);

	/* 处理部门下拉选 ，所以要获取部门的业务层对象 */
	private ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvinder
			.getService(ISysUserGroupService.SERVICE_NAME);;

	/* 处理权限组下拉选，所以要获取权限组的业务层对象 */
	private ISysRoleService sysRoleService = (ISysRoleService) ServiceProvinder
			.getService(ISysRoleService.SERVICE_NAME);

	/* 模型驱动 */
	private SysUserForm sysUserForm = new SysUserForm();

	@Override
	public SysUserForm getModel() {
		return sysUserForm;
	}
	
	@Limit(module="user", privilege="update")
	public String update() throws IllegalAccessException, InvocationTargetException {
		/* 实例化PO对象 */
		SysUser newSysUser = new SysUser();

		/* 注册转化器，日期要转化为字符串，即BeanUtils在操作字段时，发现是日期类型，他就会自动的将其转化为String */
		ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);

		/* 将VO对象，填充PO */
		BeanUtils.copyProperties(newSysUser, sysUserForm);

		/* 处理特殊情况，因为PO和VO中的字段是有区别的，查看便知 */
		/* 注意：修改时，密码就不能再去加密了 */
		/* 情况1：权限组 */
		SysRole sysRole = new SysRole();
		sysRole.setId(sysUserForm.getRoleId());
		newSysUser.setSysRole(sysRole);

		/* 情况2：部门 */
		SysUserGroup sysUserGroup = new SysUserGroup();
		/* 这里加判断的理由是：部门ID是整型，而我们获取的是String类型 */
		if (StringUtils.isNotBlank(sysUserForm.getGroupId())) {
			sysUserGroup.setId(Integer.parseInt(sysUserForm.getGroupId()));
		}
		newSysUser.setSysUserGroup(sysUserGroup);

		/* 调用业务层的方法更新 */
		/* 因为修改时，将会改变数据库表中修改人和修改时间 */
		/* 获取当前登录用户  */
		SysUser cuSysUser = SessionUtil.getSysUserFromSession(request);
		if (cuSysUser == null) {
			return "login";
		}
		
		/* 设置修改者 */
		newSysUser.setUpdater(cuSysUser.getCnname());
		/* 设置修改时间  */
		newSysUser.setUpdateTime(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));

		sysUserService.updateSysUser(newSysUser);

		return "listAction";
	}
	
	/**
	 * 去编辑页面
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Limit(module="user", privilege="edit")
	public String edit() throws IllegalAccessException, InvocationTargetException {
		/* 处理权限组下拉选，所以要获取权限组的业务层对象 */
		List<SysRole> sysRoleSelect = sysRoleService.findAllSysRoles();
		request.setAttribute("sysRoleSelect", sysRoleSelect);

		/* 处理部门下拉选 ，同上，页面处理使用Struts2的标签即可完成显示 */
		List<SysUserGroup> sysUserGroupSelect = sysUserGroupService.findAllSysUserGroups();
		request.setAttribute("sysUserGroupSelect", sysUserGroupSelect);

		/* 获取ID */
		String sid = request.getParameter("id");
		
		/* 转化ID为Integer */
		if (StringUtils.isNotBlank(sid)) {
			Integer id = Integer.parseInt(sid.trim());
		
			/* 调用业务层的方法，通过ID查询用户信息，用户回显 */
			SysUser oldSysUser = sysUserService.findSysUserById(id);
		
			/* PO---->VO回显 */
			BeanUtils.copyProperties(sysUserForm, oldSysUser);
			
			/* 特殊处理：处理下拉选的回显，只能是一个，而不是所有的选择项 */
			/* 权限组的回显 */
			if (oldSysUser.getSysRole() != null) {
				//模型驱动对象就在栈顶，设置新值后，Struts2标签自动解析更新
				sysUserForm.setRoleId(oldSysUser.getSysRole().getId());
			}
			
			/* 部门的回显 */
			if (oldSysUser.getSysUserGroup() != null) {
				sysUserForm.setGroupId(oldSysUser.getSysRole().getId());
			}
			
			return "edit";
		}
		
		return null;
	}
	
	/**
	 * 将用户停用和删除方法类似（启用停用也算修改吧，应该也要更新修改人和修改时间吧？）
	 * @return
	 */
	@Limit(module="user", privilege="disable")
	public String disable() {
		/* 获取停用的IDS */
		String[] sids = request.getParameterValues("ids");
		
		/* 转化ID为整型 */
		Integer[] ids = DataType.converterStringArray2IntegerArray(sids);
		
		if (ids != null && ids.length > 0) {
			/* 调用业务层的方法停用 */
			sysUserService.disableSysUsersByIds(ids);
			
			return "listAction";
		}
		
		return null;
	}

	/**
	 * 将用户启用和删除方法类似
	 * @return
	 */
	@Limit(module="user", privilege="enable")
	public String enable() {
		/* 获取启用的IDS */
		String[] sids = request.getParameterValues("ids");
		
		/* 转化ID为整型 */
		Integer[] ids = DataType.converterStringArray2IntegerArray(sids);
		
		if (ids != null && ids.length > 0) {
			/* 调用业务层的方法启用 */
			sysUserService.enableSysUsersByIds(ids);
			
			return "listAction";
		}
		
		return null;
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@Limit(module="user", privilege="delete")
	public String delete() {
		/* 获取删除的IDS */
		String[] sids = request.getParameterValues("ids");
		
		/* 转化ID为整型 */
		Integer[] ids = DataType.converterStringArray2IntegerArray(sids);
		
		if (ids != null && ids.length > 0) {
			/* 调用业务层的方法删除 */
			sysUserService.deleteSysUsersByIds(ids);
			
			return "listAction";
		}
		
		return null;
	}

	/** 显示用户的查询页面 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	@Limit(module="user", privilege="list")
	public String list() throws IllegalAccessException, InvocationTargetException {
		/* 处理部门下拉选 ，同上，页面处理使用Struts2的标签即可完成显示 */
		List<SysUserGroup> sysUserGroupSelect = sysUserGroupService.findAllSysUserGroups();
		request.setAttribute("sysUserGroupSelect", sysUserGroupSelect);

		/* 封装查询条件 */
		SysUserSearch sysUserSearch = new SysUserSearch();
		/* 只有属性名相同就会拷贝，多少不管(查询条件里面全是字符类型所以可以不要转化器) */
		BeanUtils.copyProperties(sysUserSearch, sysUserForm);
		
		List<SysUser> sysUserList = sysUserService.findSysUsersByCondition(sysUserSearch);
		
		/* 用于页面获取 */
		request.setAttribute("sysUserList", sysUserList);
		
		return "list";
	}

	/** 显示用户信息的添加页面 */
	@Limit(module="user", privilege="add")
	public String add() {
		/* 处理权限组下拉选，所以要获取权限组的业务层对象 */
		List<SysRole> sysRoleSelect = sysRoleService.findAllSysRoles();
		request.setAttribute("sysRoleSelect", sysRoleSelect);

		/* 处理部门下拉选 ，同上，页面处理使用Struts2的标签即可完成显示 */
		List<SysUserGroup> sysUserGroupSelect = sysUserGroupService.findAllSysUserGroups();
		request.setAttribute("sysUserGroupSelect", sysUserGroupSelect);

		/* 处理创建人、修改人、创建时间、修改时间，因为他们是变化的 */
		/* 获取当前的登录用户 */
		SysUser sysUser = SessionUtil.getSysUserFromSession(request);

		if (sysUser == null) {
			return "login"; // 说明没有登录，跳转到登录页面
		}

		/* 设置创建人和修改人，因为是添加信息，所以此时的创建人和修改人是相同的 */
		sysUserForm.setCreator(sysUser.getCnname()); // 将数据放在模型驱动中,即放在栈顶，方便页面获取(Struts2标签就会自动解析栈顶的数据)
		sysUserForm.setUpdater(sysUser.getCnname()); // 将数据放在栈顶，方便页面获取

		/* 设置创建时间和修改时间(个人觉得应该是在保存save的时候生成时间（但是这里也要生成，不然很别扭，在save方法中使用最新时间覆盖一下这里设置的时间比较好），而不是在一来到add页面就生成，因为用户可能在此页面停留很久，最长时间为本次Session会话有效时间)，因为是添加信息，所有此时的创建时间和修改时间也是相同的 */
		/* DateFormatUtils类比SimpleDateFormat强，也是一个Java的增强工具类和StringUtils类似 */
		String curDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		sysUserForm.setCreateTime(curDate);
		sysUserForm.setUpdateTime(curDate);

		return "add";
	}

	@Limit(module="user", privilege="save")
	public String save() throws IllegalAccessException, InvocationTargetException {
		/* 设置创建时间和修改时间，参考自己修改的版本 */
		//String curDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		//sysUserForm.setCreateTime(curDate);
		//sysUserForm.setUpdateTime(curDate);
		
		/* 实例化PO对象 */
		SysUser sysUser = new SysUser();

		/* 注册转化器，日期要转化为字符串，即BeanUtils在操作字段时，发现是日期类型，他就会自动的将其转化为String */
		ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);

		/* 将VO对象，填充PO */
		BeanUtils.copyProperties(sysUser, sysUserForm);

		/* 处理特殊情况，因为PO和VO中的字段是有区别的，查看便知 */
		/* 密码加密保存 */
		MD5keyBean md5keyBean = new MD5keyBean();
		String password = md5keyBean.getkeyBeanofStr(sysUserForm.getPassword());
		sysUser.setPassword(password);
		
		/* 情况1：权限组（角色），将ID设置到对象里 */
		SysRole sysRole = new SysRole();
		sysRole.setId(sysUserForm.getRoleId());
		sysUser.setSysRole(sysRole);

		/* 情况2：部门 */
		SysUserGroup sysUserGroup = new SysUserGroup();
		/* 这里加判断的理由是：部门ID是整型，而我们获取的是String类型 */
		if (StringUtils.isNotBlank(sysUserForm.getGroupId())) {
			sysUserGroup.setId(Integer.parseInt(sysUserForm.getGroupId()));
		}
		sysUser.setSysUserGroup(sysUserGroup);

		/* 调用业务层的方法保存 */
		sysUserService.saveSysUser(sysUser);

		return "listAction";
	}

	/**
	 * 验证用户是否登录
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String isLogin() throws UnsupportedEncodingException {
		/* 处理验证码：判断验证码输入的是否正确 ,request对象传过去是因为可以通过他获取Session对象*/
		boolean flag = SessionUtil.isCheckNum(request);

		if (!flag) {
			this.addFieldError("checkNum", "验证码输入错误，请重新输入");
			return "login";
		}

		/* 处理用户名和密码输入是否正确 */
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		/* 加密后和数据库对比 */
		MD5keyBean md5keyBean = new MD5keyBean();
		password = md5keyBean.getkeyBeanofStr(password);

		/* 调用业务层方法 */
		SysUser sysUser = sysUserService.findSysUserByNameAndPassword(name, password);

		/* 验证失败 */
		if (sysUser == null) {
			this.addFieldError("name", "用户名或密码输入有误");
			return "login";
		}

		/* 登录成功,并将当前用户保存到session域中 */
		SessionUtil.setSysUserToSession(request, sysUser);

		/* 处理Cookie：是否记住用户名和密码，注意这里的密码不能使用加密的 */
		addCookie(name, request.getParameter("password"), response, request);

		return "main";
	}

	/**
	 * 保存Cookie对象到客户端，用于记住用户名和密码
	 * 
	 * @param name
	 * @param parameter
	 * @param response
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void addCookie(String name, String password, HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {

		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
			/* 创建Cookie */
			Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8")); // 如果用户名时中午，防止中文乱码，使用URL编码，到页面在解码
			Cookie pswCookie = new Cookie("psw", password);

			/*
			 * 设置Cookie的父路径，这一步根据Cookie保存路径和登录页面路径来确定这一步是否需要，Cookie特性：子路径都可以访问到父路径的Cookie，如果不设置那么Cookie的获取路径就不对，就获取不到
			 * Cookie的特性，放在根目录下时，它的所有子路径都是可以访问的，所以我将其设置为/根路径
			 */
			nameCookie.setPath(request.getContextPath() + "/");
			pswCookie.setPath(request.getContextPath() + "/");

			/* 获取页面是否有记住我, 即是否保存Cookie对象到客户端 */
			String rememberMe = request.getParameter("rememberMe");

			if (rememberMe == null) {
				/* 不保存Cookie */
				nameCookie.setMaxAge(0);
				pswCookie.setMaxAge(0);
			} else {
				/* 保存Cookie一周 */
				nameCookie.setMaxAge(7 * 24 * 60 * 60);
				pswCookie.setMaxAge(7 * 24 * 60 * 60);
			}

			/* 加入Cookie到响应头 */
			response.addCookie(nameCookie);
			response.addCookie(pswCookie);
		}
	}

}
