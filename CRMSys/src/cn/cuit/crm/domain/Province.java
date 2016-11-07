package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>
 * Desc:省份信息
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：省份信息 */
@SuppressWarnings("serial")
public class Province implements Serializable {
	private Integer id;
	/* 省名称 */
	private String name;
	/* 拼音码 */
	private String pycode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
}
