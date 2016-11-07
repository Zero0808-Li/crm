package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：操作表 */
@SuppressWarnings("serial")
public class SysPopedom implements Serializable {

	/* 联合主键用一个类来表示 ，在HIbernate中称为OID */
	private SysPopedomId id;
	/* 排序 */
	private Integer sort;
	/* 提示 */
	private String title;
	/* 标题 */
	private String popedomName;
	/* 说明 */
	private String remark;

	public SysPopedomId getId() {
		return id;
	}

	public void setId(SysPopedomId id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPopedomName() {
		return popedomName;
	}

	public void setPopedomName(String popedomName) {
		this.popedomName = popedomName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
