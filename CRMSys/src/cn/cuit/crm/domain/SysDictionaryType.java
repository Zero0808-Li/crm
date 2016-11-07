package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>
 * Desc: 类型信息（客户下拉选相关的实体）
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：客户信息的下拉列表 */
@SuppressWarnings("serial")
public class SysDictionaryType implements Serializable {
	private Integer id;
	/* 排序序号 */
	private Integer sort;
	/* 备注 */
	private String remark;
	/* 区分的唯一标识 */
	private String code;
	/* 细节名称例如(东北,华北) */
	private String value;
	/* 是否有效 'N' 表示删除掉 'Y' 可用 ，该字段在该项目中没有使用*/
	private Character sysFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Character getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(Character sysFlag) {
		this.sysFlag = sysFlag;
	}

}
