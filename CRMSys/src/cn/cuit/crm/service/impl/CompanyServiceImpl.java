package cn.cuit.crm.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.bean.CompanySearch;
import cn.cuit.crm.dao.ICompanyDao;
import cn.cuit.crm.dao.ISysCodeRuleDao;
import cn.cuit.crm.dao.ISysOperateLogDao;
import cn.cuit.crm.dao.ISysUserDao;
import cn.cuit.crm.domain.Company;
import cn.cuit.crm.domain.SysCodeRule;
import cn.cuit.crm.domain.SysOperateLog;
import cn.cuit.crm.domain.SysUser;
import cn.cuit.crm.service.ICompanyService;
import cn.cuit.crm.util.DataType;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@Transactional(readOnly = true)
@Service(value = ICompanyService.SERVICE_NAME)
public class CompanyServiceImpl implements ICompanyService {

	@Resource(name = ICompanyDao.SERVICE_NAME)
	private ICompanyDao companyDao;

	@Resource(name = ISysCodeRuleDao.SERVICE_NAME)
	private ISysCodeRuleDao sysCodeRuleDao;

	@Resource(name = ISysUserDao.SERVICE_NAME)
	private ISysUserDao sysUserDao;
	
	@Resource(name = ISysOperateLogDao.SERVICE_NAME)
	private ISysOperateLogDao sysOperateLogDao;
	
	/* 该方法有对数据库的写操作，所以要加上事务 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public String getCodeByTabName(String tabName) {
		/* 获取代码规则, 查询sys_code_rule */
		String whereHql = " and o.tabName = ? ";
		Object[] params = { tabName };
		List<SysCodeRule> list = sysCodeRuleDao.findObjectsByConditionWithNoPage(whereHql, params);
		/* 数据库里面的tabName即表名，我们是唯一的，所以查出来的数据只能是一条 */
		if (list == null || list.size() != 1) {
			throw new RuntimeException("不能生成客户的编码");
		}

		SysCodeRule sysCodeRule = list.get(0);

		/* 获取 是否被修改过或新添加的 字段的值,如果 是否被修改过或新添加的=='Y' */
		if (sysCodeRule.getAvailable().equals("Y")) {
			/* 获取 流水位=3（位数） */
			Integer glideBit = sysCodeRule.getGlideBit();

			/* 生成第一个流水号 001 */
			String firstGlideBitNumber = DataType.genFirstGlideNumber(glideBit);
			/* 当前的，这样好理解 */
			String currentGlideBitNumber = firstGlideBitNumber;

			/* 计算下一个流水号 002 */
			String nextGlideBitNumber = DataType.genNextGlideNumber(currentGlideBitNumber);

			/* 获取系统的当前时间 格式yyyyMMdd 20110914 */
			String curDate = DateFormatUtils.format(new Date(), "yyyyMMdd");

			/* 生成客户编码 */
			/*
			 * 格式：编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001 c-2011-09-14-001
			 */
			String code = sysCodeRule.getAreaPrefix() + "-"
					+ DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime()) + "-" + firstGlideBitNumber;

			/* 修改代码规则表， */
			/* 下一个序列号="002" */
			sysCodeRule.setNextseq(nextGlideBitNumber);

			/* 当前日期 20110914 */
			sysCodeRule.setCurDate(curDate);

			/* 是否被修改过='N' */
			sysCodeRule.setAvailable("N");

			/* 更新到数据库 */
			sysCodeRuleDao.update(sysCodeRule);

			return code;

		} else {/* 是否被修改过或新添加的=='N' */

			// * 获取代码规则表中的当前日期字段的值
			String curDate = sysCodeRule.getCurDate();

			/* 获取系统的当前日期 */
			String sysCurDate = DateFormatUtils.format(new Date(), "yyyyMMdd");

			/* 如果代码规则表中的当前日期字段的值==系统的当前日期，同一天 */
			if (curDate.equals(sysCurDate)) {

				/* 获取下一个序列号 ="002" */
				String nextseq = sysCodeRule.getNextseq();

				/* 计算新的流水号 003 */
				String nextGlideNumber = DataType.genNextGlideNumber(nextseq);

				/* 生成客户编码 */
				/* 编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001 */
				String code = sysCodeRule.getAreaPrefix() + "-"
						+ DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime()) + "-" + nextseq;

				/* 修改代码规则表 */
				/* 下一个序列号="003" */
				sysCodeRule.setNextseq(nextGlideNumber);

				/* 当前日期 20110914 */
				/* 是否被修改过='N' */
				sysCodeRuleDao.update(sysCodeRule);

				return code;

			} else { /* 如果代码规则表中的当前日期字段的值!=系统的当前日期，新的一天 */

				/* 获取 流水位=3 */
				Integer glideBit = sysCodeRule.getGlideBit();

				/* 生成第一个流水号 001 */
				String firstGlideNumber = DataType.genFirstGlideNumber(glideBit);

				/* 计算下一个流水号 002 */
				String nextGlideNumber = DataType.genNextGlideNumber(firstGlideNumber);

				/* 生成客户编码 */
				/* 编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001 */
				String code = sysCodeRule.getAreaPrefix() + "-"
						+ DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime()) + "-" + firstGlideNumber;

				/* 修改代码规则表 */
				/* 下一个序列号="002" */
				sysCodeRule.setNextseq(nextGlideNumber);

				/* 当前日期 20110915 */
				sysCodeRule.setCurDate(sysCurDate);

				/* 是否被修改过='N' */
				sysCodeRule.setAvailable("N");
				sysCodeRuleDao.update(sysCodeRule);

				return code;
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveCompany(SysUser cuSysUser, Company company) {
		if (cuSysUser != null) {

			companyDao.save(company);

			/* 处理日志：写日志到数据库， 其他的日志类似处理就是了,这里只是一个示范 ，这个也可以封装成一下，不然每个操作都写，很日狗啊*/
			SysOperateLog log = new SysOperateLog();
			log.setUserName(cuSysUser.getName());
			log.setCnname(cuSysUser.getCnname());
			log.setActionType("添加"); // * 是什么就写什么
			String actionContent = "添加一个客户信息[ID：" + company.getId() + ", 客户名称：" + company.getName() + ", 客户编码："
					+ company.getCode() + "]";
			log.setActionContent(actionContent);
			log.setActionDate(DateFormatUtils.format(new  Date(), "yyyy-MM-dd HH:mm:ss"));
		
			sysOperateLogDao.save(log);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findCompanysByCondition(SysUser curSysUser, CompanySearch companySearch) {
		if (curSysUser != null && companySearch != null) {
			String whereHql = "";
			@SuppressWarnings("rawtypes")
			List paramList = new ArrayList();

			/* 查询我自己的 */
			if (curSysUser.getId() != null) {
				whereHql = whereHql + " and o.sysUser.id = ? ";
				paramList.add(curSysUser.getId());
			}

			if (StringUtils.isNotBlank(companySearch.getCode())) {
				whereHql = whereHql + " and o.code like ? ";
				paramList.add("%" + companySearch.getCode().trim() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getName())) {
				whereHql = whereHql + " and o.name like ? ";
				paramList.add("%" + companySearch.getName().trim() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getPycode())) {
				whereHql = whereHql + " and o.pycode like ? ";
				paramList.add("%" + companySearch.getPycode().trim() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getTel1())) {
				whereHql = whereHql + " and o.tel1 like ? ";
				paramList.add("%" + companySearch.getTel1().trim() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getSource())) {
				whereHql = whereHql + " and o.source like ? ";
				paramList.add("%" + companySearch.getSource().trim() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getGrade())) {
				whereHql = whereHql + " and o.grade like ? ";
				paramList.add("%" + companySearch.getGrade().trim() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getQuality())) {
				whereHql = whereHql + " and o.quality like ? ";
				paramList.add("%" + companySearch.getQuality().trim() + "%");
			}

			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("o.id", "asc");

			return companyDao.findObjectsByConditionWithNoPage(whereHql, paramList.toArray(), orderby);
		}
		return null;
	}

	@Override
	public Company findCompanyById(Integer id) {
		return companyDao.findObjectById(id);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateCompany(SysUser cuSysUser, Company company) {
		companyDao.update(company);
		
		/* 处理日志：写日志到数据库， 其他的日志类似处理就是了,这里只是一个示范 */
		SysOperateLog log = new SysOperateLog();
		log.setUserName(cuSysUser.getName());
		log.setCnname(cuSysUser.getCnname());
		log.setActionType("修改"); // * 是什么就写什么
		String actionContent = "修改一个客户信息[ID：" + company.getId() + ", 客户名称：" + company.getName() + ", 客户编码："
				+ company.getCode() + "]";
		log.setActionContent(actionContent);
		log.setActionDate(DateFormatUtils.format(new  Date(), "yyyy-MM-dd HH:mm:ss"));
	
		sysOperateLogDao.save(log);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteCompanyByIds(Integer[] ids) {
		companyDao.deleteByIds((Serializable[]) ids);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void addUpdateShareSetOne(String s_module, Integer id, Integer[] uids) {
		if (StringUtils.isNotBlank(s_module) && id != null && uids != null && uids.length > 0) {
			if ("c_company".equals(s_module)) {
				Company company = companyDao.findObjectById(id);
				if (company != null) {
					/* 处理用户，使用#来拼接: 1#2#3# */
					StringBuffer buf = new StringBuffer();
					for (int i = 0; i < uids.length; i++) {
						buf.append(uids[i] + "#");
					}

					if ('N' == company.getShareFlag()) {
						company.setShareFlag('Y');
						company.setShareIds("#" + buf.toString());
						companyDao.update(company);
					} else {
						company.setShareFlag('Y');
						company.setShareIds(company.getShareIds() + buf.toString());
						companyDao.update(company);
					}
				}
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void minusUpdateShareSetOne(String s_module, Integer id, Integer[] uids) {
		if (StringUtils.isNotBlank(s_module) && id != null && uids != null && uids.length > 0) {
			if ("c_company".equals(s_module)) {
				Company company = companyDao.findObjectById(id);
				if (company != null) {
					/* ShareFlag为N不处理，因为不共享 */
					if ('Y' == company.getShareFlag()) {
						/* 获取数据库的值，格式为：#userid01#userid02# */
						String shareIds = company.getShareIds();
						if (StringUtils.isNotBlank(shareIds)) {
							for (int i = 0; i < uids.length; i++) {
								String uid = "#" + uids[i] + "#";
								while (true) {
									if (shareIds.contains(uid)) {
										shareIds = shareIds.replaceAll(uid, "#");
									} else {
										break;
									}
								}
							}
							//替换完后只有一个#号
							if ("#".equals(shareIds)) {
								company.setShareFlag('N');
								company.setShareIds(null);
								companyDao.update(company);
							} else {
								company.setShareIds(shareIds);
								companyDao.update(company);
							}
						}
					}
				}
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateShareCancelOne(Integer id, String s_module) {
		if (id != null && StringUtils.isNotBlank(s_module)) {
			Company company = companyDao.findObjectById(id);
			if (company != null) {
				company.setShareFlag('N');
				company.setShareIds(null);
				companyDao.update(company);
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> findSysUsersBySharedIds(Integer id) {
		if (id != null) {
			Company company = companyDao.findObjectById(id);
			if (company != null) {
				/* 格式：#2#3# */
				String shareIds = company.getShareIds();
				if (StringUtils.isNotBlank(shareIds)) {
					String[] shareId = shareIds.split("#");
					/* 存放的是用户id */
					Integer[] uid = DataType.converterStringArray2IntegerArray(shareId);
					if (uid != null && uid.length > 0) {
						/* select o from SysUser o where o.id in(?, ?, ?) */
						StringBuffer whereBuf = new StringBuffer();
						whereBuf.append(" and o.id in(");
						@SuppressWarnings("rawtypes")
						List paramList = new ArrayList();

						for (int i = 1; i < uid.length; i++) {
							whereBuf.append("?,");
							paramList.add(uid[i]);
						}
						//去掉最后一个多余的‘,’逗号
						whereBuf.deleteCharAt(whereBuf.length() - 1);
						whereBuf.append(")");

						return sysUserDao.findObjectsByConditionWithNoPage(whereBuf.toString(), paramList.toArray());
					}
				}
			}
		}
		return null;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateNextTouchTime(Integer[] ids, java.sql.Date next_touch_date) {
		if (ids != null && ids.length > 0 && next_touch_date != null) {
			for (int i = 0; i < ids.length; i++) {
				Company company = companyDao.findObjectById(ids[i]);

				if (company != null) {
					company.setNextTouchDate(next_touch_date);
					companyDao.update(company);
				}
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void changeHandler(Integer[] ids, Integer new_owner) {
		if (ids != null && ids.length > 0 && new_owner != null) {
			/* 通过用户的id查询用户的信息 */
			SysUser sysUser = sysUserDao.findObjectById(new_owner);
			for (int i = 0; i < ids.length; i++) {
				/* 通过客户的id1查询客户的信息 */
				Company company = companyDao.findObjectById(ids[i]);

				if (company != null && sysUser != null) {
					company.setSysUser(sysUser);
					company.setDispensePerson(sysUser.getCnname());
					company.setDispenseDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
					companyDao.update(company);
				}
			}
		}
	}
}
