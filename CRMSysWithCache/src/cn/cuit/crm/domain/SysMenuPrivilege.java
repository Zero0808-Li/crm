package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 菜单权限实体 */
@SuppressWarnings("serial")
public class SysMenuPrivilege implements Serializable {
	/* 对象作为联合主键 */
	private SysMenuPrivilegeId id;

	public SysMenuPrivilegeId getId() {
		return id;
	}

	public void setId(SysMenuPrivilegeId id) {
		this.id = id;
	}

}
