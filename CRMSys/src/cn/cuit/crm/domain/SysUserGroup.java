package cn.cuit.crm.domain;

/**
 * 
 * <P>
 * Desc: 系统用户组（即部门）实体PO对象，这里的值是和数据库的对应
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SysUserGroup implements java.io.Serializable {
	private Integer id;
	/* 备注 */
	private String remark;
	/* 部门名称 */
	private String name;
	/* 部门负责人 */
	private String principal;
	/* 部门职能 */
	private String incumbent;

	/* 一个部门包含多个用户，这里我们不做配置，就配置一个单向操作（用的上时就陪，根据业务需求来） */
	// private Set users = new HashSet<SysUser>();

	public SysUserGroup() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getIncumbent() {
		return incumbent;
	}

	public void setIncumbent(String incumbent) {
		this.incumbent = incumbent;
	}

	@Override
	public String toString() {
		return "SysUserGroup [id=" + id + ", remark=" + remark + ", name=" + name + ", principal=" + principal
				+ ", incumbent=" + incumbent + "]";
	}

}