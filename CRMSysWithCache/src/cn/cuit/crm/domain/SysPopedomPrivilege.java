package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：操作权限表  */
@SuppressWarnings("serial")
public class SysPopedomPrivilege implements Serializable {
	/* 对象作为联合主键 */
	private SysPopedomPrivilegeId id;

	public SysPopedomPrivilegeId getId() {
		return id;
	}

	public void setId(SysPopedomPrivilegeId id) {
		this.id = id;
	}
}
