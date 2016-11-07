package cn.cuit.crm.dao;

import java.util.List;

import cn.cuit.crm.bean.ReportBean;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface IReportDao extends ICommonDao<ReportBean> {

	public final static String SERVICE_NAME = "cn.cuit.crm.dao.impl.ReportDaoImpl";

	/**
	 * 查询报表的数据（因为没有实体映射）
	 * @return
	 */
	public abstract List<ReportBean> findReportBeans();

}
