package cn.cuit.crm.junit;

import org.junit.Test;

import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.dao.ICompanyDao;
import cn.cuit.crm.domain.Company;
import cn.cuit.crm.domain.SysUser;

/**
 * 
 * <P>
 * Desc:
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
public class TestCompany {

	@Test
	public void testSaveCompany() {
		ICompanyDao companyDao = (ICompanyDao) ServiceProvinder.getService(ICompanyDao.SERVICE_NAME);
		Company company = new Company();

		company.setCode("测试数据：Company01");
		company.setName("测试数据：Company01");

		SysUser sysUser = new SysUser();
		sysUser.setId(4);

		company.setShareFlag('N');
		company.setSysUser(sysUser);

		companyDao.save(company);

	}
}
