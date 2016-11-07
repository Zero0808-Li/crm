package cn.cuit.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.bean.ReportBean;
import cn.cuit.crm.dao.IReportDao;
import cn.cuit.crm.service.IReportService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@Transactional(readOnly = true)
@Service(value = IReportService.SERVICE_NAME)
public class ReportServiceImpl implements IReportService {

	@Resource(name=IReportDao.SERVICE_NAME)
	private IReportDao reportDao;
	
	@Override
	public List<ReportBean> findReportBeans() {
		List<ReportBean> list = reportDao.findReportBeans();
		
		return list;
	}

}
