package cn.cuit.crm.bean;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */
/* 说明： 该包中的JavaBean是保存的搜索的条件， 独立的，条件可以在这里动态的增减 */
public class SysUserGroupSearch {

	/* 部门名称作为条件 */
	private String name;

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

}
