package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * <P>
 * Desc:权限组（即角色）
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：角色PO对象 */
@SuppressWarnings("serial")
public class SysRole implements Serializable {

	private String id;
	/* 名称 */
	private String name;
	/* 备注 */
	private String remark;

	/** 一个角色包含多个用户，这里也不配置，使用单向关联 */
	// private Set<SysUser> roleUsers = new HashSet<>(0);

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
