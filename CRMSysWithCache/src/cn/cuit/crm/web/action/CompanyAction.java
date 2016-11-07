package cn.cuit.crm.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.opensymphony.xwork2.ModelDriven;

import cn.cuit.crm.bean.CompanySearch;
import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.domain.City;
import cn.cuit.crm.domain.Company;
import cn.cuit.crm.domain.Province;
import cn.cuit.crm.domain.SysDictionaryType;
import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.domain.SysUserGroup;
import cn.cuit.crm.service.ICityService;
import cn.cuit.crm.service.ICompanyService;
import cn.cuit.crm.service.IProvinceService;
import cn.cuit.crm.service.ISysDictionaryTypeService;
import cn.cuit.crm.service.ISysUserGroupService;
import cn.cuit.crm.service.ISysUserService;
import cn.cuit.crm.util.DataType;
import cn.cuit.crm.util.Global;
import cn.cuit.crm.util.PingyinUtils;
import cn.cuit.crm.util.SQLDateConverter;
import cn.cuit.crm.util.SessionUtil;
import cn.cuit.crm.web.form.CompanyForm;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 客户Action */
@SuppressWarnings("serial")
public class CompanyAction extends BaseAction implements ModelDriven<CompanyForm> {

	private CompanyForm companyForm = new CompanyForm();

	@Override
	public CompanyForm getModel() {
		return companyForm;
	}

	/* 获取客户的业务层对象 */
	private ICompanyService companyService = (ICompanyService) ServiceProvinder
			.getService(ICompanyService.SERVICE_NAME);

	/* 获取处理客户信息添加时的下拉选列表的业务层 */
	private ISysDictionaryTypeService sysDictionaryTypeService = (ISysDictionaryTypeService) ServiceProvinder
			.getService(ISysDictionaryTypeService.SERVICE_NAME);

	/* 获取操作省份的业务层对象 */
	private IProvinceService provinceService = (IProvinceService) ServiceProvinder
			.getService(IProvinceService.SERVICE_NAME);

	/* 获取操作部门的业务层对象 */
	private ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvinder
			.getService(ISysUserGroupService.SERVICE_NAME);

	/* 获取操作用户的业务层对象 */
	private ISysUserService sysUserService = (ISysUserService) ServiceProvinder
			.getService(ISysUserService.SERVICE_NAME);

	/* 获取操作省下城市的业务层对象 */
	private ICityService cityService = (ICityService) ServiceProvinder.getService(ICityService.SERVICE_NAME);

	/**
	 * 列出所有客户
	 * 
	 * @return
	 */
	public String list() {
		/* 处理查询条件的下拉选 */
		/* 处理客户等级 */
		List<SysDictionaryType> gradesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.GRADE);
		request.setAttribute("gradesSelect", gradesSelect);

		/* 处理客户来源 */
		List<SysDictionaryType> sourcesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.SOURCE);
		request.setAttribute("sourcesSelect", sourcesSelect);

		/* 处理客户性质 */
		List<SysDictionaryType> qualitySelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.QUALITY);
		request.setAttribute("qualitySelect", qualitySelect);

		/* 实例化条件搜索对象 */
		CompanySearch companySearch = new CompanySearch();
		companySearch.setCode(companyForm.getCode());
		companySearch.setName(companyForm.getName());
		companySearch.setPycode(companyForm.getPycode());
		companySearch.setGrade(companyForm.getGrade());
		companySearch.setTel1(companyForm.getTel1());
		companySearch.setSource(companyForm.getSource());
		companySearch.setQuality(companyForm.getQuality());

		/* 获取当前登录用户 */
		SysUser curSysUser = SessionUtil.getSysUserFromSession(request);
		if (curSysUser != null) {
			List<Company> companyList = companyService.findCompanysByCondition(curSysUser, companySearch);
			request.setAttribute("companyList", companyList);
			
			return "list";
		}

		return null;
	}

	public String edit() throws IllegalAccessException, InvocationTargetException {
		/* 处理客户等级的下拉选 */
		List<SysDictionaryType> gradesSelect = sysDictionaryTypeService
				.findSysDictionaryTypeByCode(Global.GRADE);
		request.setAttribute("gradesSelect", gradesSelect);

		/* 处理区域名称的下拉选 */
		List<SysDictionaryType> regionNamesSelect = sysDictionaryTypeService
				.findSysDictionaryTypeByCode(Global.REGIONNAME);
		request.setAttribute("regionNamesSelect", regionNamesSelect);

		/* 获取所有的省的信息 */
		List<Province> provincesSelect = provinceService.findAllProvinces();
		request.setAttribute("provincesSelect", provincesSelect);

		/* 获取客户的id */
		String sid = request.getParameter("id");
		if (StringUtils.isNotBlank(sid)) {
			Integer id = Integer.parseInt(sid);
			/* 通过客户的id获取客户的信息 */
			Company company = companyService.findCompanyById(id);
			/* 赋值客户的信息到VO（VO是栈顶的） */
			BeanUtils.copyProperties(companyForm, company);

			/* 特殊处理(省) */
			String pname = company.getProvince();

			Province province = provinceService.findProvinceByName(pname);
			if (province != null) {
				/* 通过省的id获取对应该省的城市信息 */
				List<City> citiesSelect = cityService.findCitiesByPid(province.getId());
				request.setAttribute("citiesSelect", citiesSelect);
			}

			if (company.getSysUser() != null) {
				/* 处理所属人的id */
				companyForm.setOwnerUser(company.getSysUser().getId() + "");

				/* 处理所属人的日期（即获得客户的日期） */
				//栈顶已经处理
			}

			return "edit";
		}

		return null;
	}

	public String save() throws IllegalAccessException, InvocationTargetException {
		/* 实例化PO对象 */
		Company company = new Company();
		/* 注册日期转化器 */
		ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);
		/* VO --> PO */
		BeanUtils.copyProperties(company, companyForm);
		/* 处理特殊情况 */
		// * 1:处理客户的所属人的id（谁登录就属于谁）
		String userId = companyForm.getOwnerUser();
		if (StringUtils.isNotBlank(userId)) {
			SysUser sysUser = new SysUser();
			sysUser.setId(Integer.parseInt(userId));
			company.setSysUser(sysUser);
		}

		// * 2:设置分配给所属人的日期
		company.setDispenseDate(companyForm.getCreateTime());
		// * 默认客户都不共享
		company.setShareFlag('N');

		// * 3:保存(保存之前获取当前的登录用户)
		SysUser cuSysUser = SessionUtil.getSysUserFromSession(request);
		if (cuSysUser != null) {
			// * 4:传第一个参数的目的是为了以后的日志
			companyService.saveCompany(cuSysUser, company);
			return "listAction";
		}
		return null;
	}

	//修改时，修改日期好像有点问题
	public String update() throws IllegalAccessException, InvocationTargetException {
		/* 实例化PO对象 */
		Company company = new Company();
		/* 注册日期转化器 */
		ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);
		/* VO --> PO */
		BeanUtils.copyProperties(company, companyForm);
		/* 处理特殊情况 */

		// * 1:处理客户的所属人的id（谁登录就属于谁）
		String userId = companyForm.getOwnerUser();
		if (StringUtils.isNotBlank(userId)) {
			SysUser sysUser = new SysUser();
			sysUser.setId(Integer.parseInt(userId));
			company.setSysUser(sysUser);
		}

		// * 2:设置分配给所属人的日期(这里的日期不用处理)（使用的隐藏域，栈顶已经处理）
		// * company.setDispenseDate(companyForm.getCreateTime());
		company.setShareFlag('N');

		// * 3:保存(保存之前获取当前的登录用户)
		SysUser cuSysUser = SessionUtil.getSysUserFromSession(request);
		if (cuSysUser != null) {
			// * 4:传第一个参数的目的是为了以后的日志
			companyService.updateCompany(cuSysUser, company);
			
			return "listAction";
		}
		return null;
	}

	public String delete() {
		String[] sids = request.getParameterValues("ids");
		if (sids != null && sids.length > 0) {
			Integer[] ids = DataType.converterStringArray2IntegerArray(sids);
			companyService.deleteCompanyByIds(ids);
			return "listAction";
		}

		return null;
	}

	/**
	 * 查看共享页面
	 * 
	 * @return
	 */
	public String showShareViewOne() {
		/* 获取客户的id */
		String sid = request.getParameter("id");
		if (StringUtils.isNotBlank(sid)) {
			Integer id = Integer.parseInt(sid);
			Company company = companyService.findCompanyById(id);
			request.setAttribute("company", company);
			/* 获取该客户共享的用户信息 */
			List<SysUser> sysUsers = companyService.findSysUsersBySharedIds(id);
			request.setAttribute("sysUsers", sysUsers);

			return "showShareViewOne";
		}

		return null;
	}

	/**
	 * 显示取消共享页面（即所有人都不共享了，只有拥有客户的人使用）
	 * 
	 * @return
	 */
	public String showShareCancelOne() {
		/* 获取客户的id */
		String sid = request.getParameter("id");
		if (StringUtils.isNotBlank(sid)) {
			Integer id = Integer.parseInt(sid);
			Company company = companyService.findCompanyById(id);
			request.setAttribute("company", company);

			return "showShareCancelOne";
		}

		return null;
	}
	
	/**
	 * 取消共享
	 */
	public String updateShareCancelOne() {
		/* 获取客户的id */
		String sid = request.getParameter("id");
		if (StringUtils.isNotBlank(sid)) {
			Integer id = Integer.parseInt(sid);
			/* 获取模块名称 */
			String s_module = request.getParameter("s_module");
			if (StringUtils.isNotBlank(s_module)) {
				companyService.updateShareCancelOne(id, s_module);
			}

			return "updateShareCancelOne";
		}
		return null;
	}

	/**
	 * 显示增加共享和减少共享设置页面（只对共享人的部分进行增减）
	 * 
	 * @return
	 */
	public String showShareSetOne() {
		/* 获取客户的id */
		String sid = request.getParameter("id");
		if (StringUtils.isNotBlank(sid)) {
			Integer id = Integer.parseInt(sid);
			Company company = companyService.findCompanyById(id);
			request.setAttribute("company", company);

			/* 获取部门的信息 */
			List<SysUserGroup> sysUserGroups = sysUserGroupService.findAllSysUserGroups();
			request.setAttribute("sysUserGroups", sysUserGroups);

			/* 获取用户的信息 */
			List<SysUser> sysUsers = sysUserService.findAllSysUsers();
			request.setAttribute("sysUsers", sysUsers);

			return "showShareSetOne";
		}

		return null;
	}
	
	/**
	 * 更新共享
	 * @return
	 */
	public String updateShareSetOne() {
		/* 获取客户id */
		String sid = request.getParameter("id");
		if (StringUtils.isNotBlank(sid.trim())) {
			Integer id = Integer.parseInt(sid);
			/* 获取模块名称 */
			String s_module = request.getParameter("s_module");
			if (StringUtils.isNotBlank(s_module)) {
				/* 获取用户id */
				String[] suids = request.getParameterValues("uid");
				Integer[] uids = DataType.converterStringArray2IntegerArray(suids);
				/* 获取是增减还是减少共享 */
				String sharetype = request.getParameter("sharetype");
				if (StringUtils.isNotBlank(sharetype)) {
					/* 增减共享 */
					if ("add".equals(sharetype)) {
						companyService.addUpdateShareSetOne(s_module, id, uids);
					}

					/* 减少共享 */
					if ("minus".equals(sharetype)) {
						companyService.minusUpdateShareSetOne(s_module, id, uids);
					}
				}

				return "updateShareSetOne";
			}
		}

		return null;
	}

	/**
	 * 显示客户添加页面（注：还有一些下来选没有处理，还是使用的静态数据，写法和区域类似，自行处理）
	 * 
	 * @return
	 */
	public String add() {
		/* 处理客户的编码（编号）较麻烦 */
		String code = companyService.getCodeByTabName("c_company");
		request.setAttribute("code", code);

		/* 处理客户信息添加时的下拉选列表 */
		/* 处理客户等级的下拉选 */
		// List<SysDictionaryType> gradesSelect =
		// sysDictionaryTypeService.findSysDictionaryTypeByCode("grade");
		List<SysDictionaryType> gradesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.GRADE);
		request.setAttribute("gradesSelect", gradesSelect);

		/* 处理区域名称的下拉选 */
		// List<SysDictionaryType> regionNamesSelect =
		// sysDictionaryTypeService.findSysDictionaryTypeByCode("regionName");
		List<SysDictionaryType> regionNamesSelect = sysDictionaryTypeService
				.findSysDictionaryTypeByCode(Global.REGIONNAME);
		request.setAttribute("regionNamesSelect", regionNamesSelect);

		/********************还有一些下拉选没有处理，还是使用的静态数据，写法和区域类似，自行处理*********************/
		
		/* 获取所有的省的信息（意味着就要建立省份的业务层） */
		List<Province> provincesSelect = provinceService.findAllProvinces();
		request.setAttribute("provincesSelect", provincesSelect);

		/* 获取当前登录用户 */
		SysUser curSysUser = SessionUtil.getSysUserFromSession(request);
		if (curSysUser != null) {
			/* 处理创建人、 修改人、 创建时间、 修改时间、 所属人(客户所属人)、 所属人的id */
			companyForm.setCreater(curSysUser.getCnname());
			companyForm.setUpdater(curSysUser.getCnname());
			companyForm.setDispensePerson(curSysUser.getCnname());
			companyForm.setOwnerUser(curSysUser.getId()+"");
			/* 使用工具类将日期转化为字符串，下面的代码转移带save方法中 */
			String curDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			companyForm.setCreateTime(curDate);
			companyForm.setUpdateTime(curDate);

			return "add";
		}

		return null;
	}

	/**
	 * 解决汉语拼音的（这里是使用的AJAX请求）
	 * 
	 * @return
	 * @throws IOException
	 */
	public String pinyin() throws IOException {
		/* 获取客户的名称 */
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			String pin = PingyinUtils.converterToFirstSpell(name).trim();
			//写回给客户端，即写回页面
			response.getWriter().println(pin);
		}

		return null; // AJAX请求时Action返回null
	}

	/**
	 * 显示省下对应的城市（使用的JAXA请求,二级联动）
	 * 
	 * @return
	 * @throws IOException
	 */
	public String showCity() throws IOException {
		/* 获取省的名称 */
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			/* 通过省的名称获取省的id */
			Province province = provinceService.findProvinceByName(name);
			if (province != null) {
				/* 通过省的id获取对应该省的城市信息 */
				List<City> citiesSelect = cityService.findCitiesByPid(province.getId());
				/* 将list格式的数据转化为JSON格式的数据，JSON格式的数据和Map类似 */
				/* 设置不包含的属性 */
				JsonConfig config = new JsonConfig();
				config.setExcludes(new String[] { "id", "pid", "pycode", "areacode" });
				JSONArray jsonArray = JSONArray.fromObject(citiesSelect, config); // 也可以不要第二个参数，就是全部显示
				// * System.out.println(jsonArray.toString());
				/* 设置编码，并将数据响应到页面 */
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(jsonArray.toString());
			}
		}
		/* AJAX请求返回值一般都是null，页面请求需要的数据都是通过response对象响应用流把数据写回页面 */
		return null;
	}

	/**
	 * 显示下次联系时间页面
	 * 
	 * @return
	 */
	public String showNextTouchTime() {

		return "showNextTouchTime";
	}

	/**
	 * 修改下次联系时间
	 * 
	 * @return
	 */
	public String updateNextTouchTime() {
		/* 获取客户id */
		String sid = request.getParameter("ids");
		if (StringUtils.isNotBlank(sid)) {
			String[] sids = sid.split(",");
			Integer[] ids = DataType.converterStringArray2IntegerArray(sids);
			/* 获取下次联系时间 */
			String snext_touch_date = request.getParameter("next_touch_date");
			java.sql.Date next_touch_date = java.sql.Date.valueOf(snext_touch_date);

			companyService.updateNextTouchTime(ids, next_touch_date);

			return "updateNextTouchTime";
		}

		return null;
	}

	/**
	 * 显示经手人变更页面
	 * 
	 * @return
	 */
	public String showChangePerson() {
		/* 获取系统的所有用户 */
		List<SysUser> sysUserSelect = sysUserService.findAllSysUsers();
		request.setAttribute("sysUserSelect", sysUserSelect);

		return "showChangePerson";
	}

	/**
	 * 处理经手人变更(变更客户的所属人)
	 * 
	 * @return
	 */
	public String changeHandler() {
		/* 获取客户id */
		String sid = request.getParameter("ids");
		if (StringUtils.isNotBlank(sid)) {
			String[] sids = sid.split(",");
			Integer[] ids = DataType.converterStringArray2IntegerArray(sids);

			/* 获取要变更给的用户的id */
			String snew_owner = request.getParameter("new_owner");
			if (StringUtils.isNotBlank(snew_owner)) {
				Integer new_owner = Integer.parseInt(snew_owner.trim());
				companyService.changeHandler(ids, new_owner);
				return "changeHandler";
			}
		}
		return null;
	}
}
