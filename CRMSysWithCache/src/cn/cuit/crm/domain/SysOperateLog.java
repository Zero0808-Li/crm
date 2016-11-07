package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>
 * Desc:日志实体(记录登陆用户的操作状态)
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 日志记录 */
@SuppressWarnings("serial")
public class SysOperateLog implements Serializable {
	private Integer id;
	/* 登陆用户的名称 */
	private String userName;
	/* 登陆用户的中文名称 */
	private String cnname;
	/* 操作类型[添加 删除 编辑 修改 共享.....] */
	private String actionType;
	/* 操作的内容 添加一个客户信息(ID:9,客户名称:上海开铺,客户编码:C-2011-0429-0013) */
	private String actionContent;
	/* 操作的日期 */
	private String actionDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionContent() {
		return actionContent;
	}

	public void setActionContent(String actionContent) {
		this.actionContent = actionContent;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

}
