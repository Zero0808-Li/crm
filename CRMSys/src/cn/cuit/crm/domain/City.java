package cn.cuit.crm.domain;

/**
 * 
 * <P>
 * Desc:城市信息
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 城市 */
public class City {
	private Integer id;
	/* 城市名称 */
	private String name;
	/* 城市拼音码 */
	private String pycode;
	/*
	 * 省的id， 这里没有采用表关联，而是单表直接的操作，如果是要关联的话，这应该是一个省的对象，
	 * 而这里是采用的id，所以在这里就要手动建表，并建立他们的关系
	 */
	/* 省编号 */
	private Integer pid;
	/* 邮编 */
	private String postcode;
	/* 区号 */
	private String areacode;

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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

}
