package cn.cuit.crm.web.form;

import java.io.Serializable;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：VO对象保存JSP页面的值（即视图表单元素，类型和表单的一样）,用于Action里面填充PO实体（实体即是PO对象，持久化对象，他的类型和数据库的类型相同） */
@SuppressWarnings("serial")
public class SysUserGroupForm implements Serializable {
	/* PO中使用的是Integer，是代理主键，而页面提交的是都是String类型数据 */
	private String id;
	/* 备注 */
	private String remark;
	/* 部门名称 */
	private String name;
	/* 部门负责人 */
	private String principal;
	/* 部门职能 */
	private String incumbent;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the incumbent
	 */
	public String getIncumbent() {
		return incumbent;
	}

	/**
	 * @param incumbent
	 *            the incumbent to set
	 */
	public void setIncumbent(String incumbent) {
		this.incumbent = incumbent;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
