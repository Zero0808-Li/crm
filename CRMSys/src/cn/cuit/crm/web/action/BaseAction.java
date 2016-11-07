package cn.cuit.crm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：公共的Action,进一步的封装，避免每一个要使用request的Action都去实现ServletRequestAware接口，妙啊 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
