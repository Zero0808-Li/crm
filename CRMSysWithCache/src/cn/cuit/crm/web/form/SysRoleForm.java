package cn.cuit.crm.web.form;

import java.io.Serializable;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：角色VO对象 */
@SuppressWarnings("serial")
public class SysRoleForm implements Serializable {

	private String id;
	private String name;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
