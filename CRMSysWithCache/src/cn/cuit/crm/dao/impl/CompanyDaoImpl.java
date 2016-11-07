package cn.cuit.crm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.cuit.crm.dao.ICompanyDao;
import cn.cuit.crm.domain.Company;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Repository(ICompanyDao.SERVICE_NAME)
public class CompanyDaoImpl extends CommonDaoImpl<Company>implements ICompanyDao {

	
}
