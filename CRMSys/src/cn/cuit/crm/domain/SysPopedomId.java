package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>
 * Desc:
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：SysPopedom实体的联合主键 */
@SuppressWarnings("serial")
public class SysPopedomId implements Serializable {
	/* 模块名称 */
	private String popedomModule;
	/* 操作名称 */
	private String popedomPrivilege;

	public String getPopedomModule() {
		return popedomModule;
	}

	public void setPopedomModule(String popedomModule) {
		this.popedomModule = popedomModule;
	}

	public String getPopedomPrivilege() {
		return popedomPrivilege;
	}

	public void setPopedomPrivilege(String popedomPrivilege) {
		this.popedomPrivilege = popedomPrivilege;
	}

	/* 因为要比较所以重新 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((popedomModule == null) ? 0 : popedomModule.hashCode());
		result = prime * result + ((popedomPrivilege == null) ? 0 : popedomPrivilege.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysPopedomId other = (SysPopedomId) obj;
		if (popedomModule == null) {
			if (other.popedomModule != null)
				return false;
		} else if (!popedomModule.equals(other.popedomModule))
			return false;
		if (popedomPrivilege == null) {
			if (other.popedomPrivilege != null)
				return false;
		} else if (!popedomPrivilege.equals(other.popedomPrivilege))
			return false;
		return true;
	}

}
