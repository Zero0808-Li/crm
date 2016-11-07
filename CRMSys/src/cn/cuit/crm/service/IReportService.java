package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.bean.ReportBean;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface IReportService {

	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.ReportServiceImpl";

	/**
	 * @return
	 */
	public abstract List<ReportBean> findReportBeans();
}
