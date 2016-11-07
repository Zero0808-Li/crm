package cn.cuit.crm.bean;

import java.io.Serializable;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 报表 */
public class ReportBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private Long count;

	/**
	 * @param type
	 * @param count
	 */
	public ReportBean(String type, Long count) {
		super();
		this.type = type;
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
