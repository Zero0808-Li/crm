package cn.cuit.crm.domain;

import java.io.Serializable;

/**
 * 
 * <P>
 * Desc:编码规则
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 客户代码（编码）生成PO */
@SuppressWarnings("serial")
public class SysCodeRule implements Serializable {
	private Integer id;
	/* 模块名称 */
	private String module;
	/* 编码前缀 */
	private String areaPrefix;
	/* 日期位 */
	private String areaTime;
	/* 流水位 */
	private Integer glideBit;
	/* 预览 */
	private String currentCode;
	/* 表名 */
	private String tabName;
	/* 是否固定 */
	private String available;
	/* 下次产生的序号 */
	private String nextseq;
	/* 序号对应的日期(yyyyMMdd) */
	private String curDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAreaPrefix() {
		return areaPrefix;
	}

	public void setAreaPrefix(String areaPrefix) {
		this.areaPrefix = areaPrefix;
	}

	public String getAreaTime() {
		return areaTime;
	}

	public void setAreaTime(String areaTime) {
		this.areaTime = areaTime;
	}

	public Integer getGlideBit() {
		return glideBit;
	}

	public void setGlideBit(Integer glideBit) {
		this.glideBit = glideBit;
	}

	public String getCurrentCode() {
		return currentCode;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getNextseq() {
		return nextseq;
	}

	public void setNextseq(String nextseq) {
		this.nextseq = nextseq;
	}

	public String getCurDate() {
		return curDate;
	}

	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}

}
