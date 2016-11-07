package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * <P>
 * Desc:
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 菜单实体 */
@SuppressWarnings("serial")
public class SysMenu implements Serializable {
	/* 对象作为联合主键 */
	private SysMenuId id;
	/* 排序字段 */
	private Integer sort;
	/* 中文名称 */
	private String menuName;
	/* 标题 */
	private String title;
	/* 链接路径 */
	private String url;
	/* 目标框架 */
	private String target;
	/* 使用的图标 */
	private String icon;
	/* 说明 */
	private String remark;

	public SysMenuId getId() {
		return id;
	}

	public void setId(SysMenuId id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
