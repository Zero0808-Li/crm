package cn.cuit.crm.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.cuit.crm.bean.ReportBean;
import cn.cuit.crm.container.ServiceProvinder;
import cn.cuit.crm.service.IReportService;
import cn.cuit.crm.util.JFreeChartUtils;

/**
 * 
 * <P>Desc:客户分类分析报表 </P>
 * @author CUITLLB
 * @version 1.0
 */

@SuppressWarnings("serial")
/* 说明：  */
public class ReportAction extends BaseAction {

	private IReportService reportService = (IReportService) ServiceProvinder.getService(IReportService.SERVICE_NAME);
	
	/**
	 * 客户分类分析
	 * @return
	 * @throws IOException 
	 */
	public String khflfx() throws IOException {
		List<ReportBean> reportBeans = reportService.findReportBeans();
		request.setAttribute("reportBeans", reportBeans);
		/* 计算总数 */
		Long sum = 0L;
		if (reportBeans != null && reportBeans.size() > 0) {
			for (ReportBean reportBean : reportBeans) {
				sum = sum + reportBean.getCount();
			}
		}
		request.setAttribute("sum", sum);
		
		/* 获取ServletContext对象 */
		ServletContext context = ServletActionContext.getServletContext();
		/* 生成报表图片 */
		JFreeChartUtils.generalBarJpeg(reportBeans, request, context);
	
		return "khflfx";
	}
}
