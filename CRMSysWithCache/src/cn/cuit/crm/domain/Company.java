package cn.cuit.crm.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * <P>
 * Desc: 客户信息
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：客户信息（客户是其他公司）PO */
@SuppressWarnings("serial")
public class Company implements Serializable {
	private Integer id;
	/* 客户编码 */
	private String code;
	/* 客户名称 */
	private String name;
	/* 拼音码 */
	private String pycode;
	/* 客户级别 */
	private String grade;
	/* 区域名称 */
	private String regionName;
	/* 客户来源 */
	private String source;
	/* 所属行业 */
	private String trade;
	/* 公司规模 */
	private String scale;
	/* 省份 */
	private String province;
	/* 城市 */
	private String city;
	/* 邮编 */
	private String postcode;
	/* 联系地址 */
	private String address;
	/* 电子邮件 */
	private String email;
	/* 公司网址 */
	private String web;
	/* 电话1 */
	private String tel1;
	/* 传真 */
	private String fax;
	/* 手机号码 */
	private String mobile;
	/* 电话2 */
	private String tel2;
	/* 下次联系时间 */
	private Date nextTouchDate;
	/* 客户性质 */
	private String quality;
	/* 备注 */
	private String remark;
	/* 经营范围 */
	private String dealin;
	/* 企业性质 */
	private String kind;
	/* 法人代表 */
	private String artificialPerson;
	/* 注册资金 */
	private String registeMoney;
	/* 开户银行 */
	private String bank;
	/* 银行账户 */
	private String account;
	/* 公司税号 */
	private String taxCode;
	/* 创建人 */
	private String creater;
	/* 创建日期 */
	private String createTime;
	/* 修改人 */
	private String updater;
	/* 修改日期 */
	private String updateTime;

	/* 多个客户对应一个用户 */
	private SysUser sysUser;
	
	/* 所属人的分配日期 */
	private String dispensePerson;
	/* #分配日期(经手人变更的日期) */
	private String dispenseDate;
	/* #共享标志 Y(共享)和N(不共享) */
	private Character shareFlag;
	/* #共享ID客户资料共享给业务人员 格式 #人事编号#人事编号...例如(#12#11#) */
	private String shareIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public Date getNextTouchDate() {
		return nextTouchDate;
	}

	public void setNextTouchDate(Date nextTouchDate) {
		this.nextTouchDate = nextTouchDate;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDealin() {
		return dealin;
	}

	public void setDealin(String dealin) {
		this.dealin = dealin;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getArtificialPerson() {
		return artificialPerson;
	}

	public void setArtificialPerson(String artificialPerson) {
		this.artificialPerson = artificialPerson;
	}

	public String getRegisteMoney() {
		return registeMoney;
	}

	public void setRegisteMoney(String registeMoney) {
		this.registeMoney = registeMoney;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getDispensePerson() {
		return dispensePerson;
	}

	public void setDispensePerson(String dispensePerson) {
		this.dispensePerson = dispensePerson;
	}

	public String getDispenseDate() {
		return dispenseDate;
	}

	public void setDispenseDate(String dispenseDate) {
		this.dispenseDate = dispenseDate;
	}

	public Character getShareFlag() {
		return shareFlag;
	}

	public void setShareFlag(Character shareFlag) {
		this.shareFlag = shareFlag;
	}

	public String getShareIds() {
		return shareIds;
	}

	public void setShareIds(String shareIds) {
		this.shareIds = shareIds;
	}

}
