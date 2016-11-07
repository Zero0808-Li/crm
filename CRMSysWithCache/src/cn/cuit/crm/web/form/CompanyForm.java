package cn.cuit.crm.web.form;

import java.io.Serializable;


/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@SuppressWarnings("serial")
public class CompanyForm implements Serializable {
	private String id;
	private String code;
	private String name;
	private String pycode;
	private String grade;
	private String regionName;
	private String source;
	private String trade;
	private String scale;
	private String province;
	private String city;
	private String postcode;
	private String address;
	private String email;
	private String web;
	private String tel1;
	private String fax;
	private String mobile;
	private String tel2;
	private String nextTouchDate;
	private String quality;
	private String remark;
	private String dealin;
	private String kind;
	private String artificialPerson;
	private String registeMoney;
	private String bank;
	private String account;
	private String taxCode;
	private String creater;
	private String createTime;
	private String updater;
	private String updateTime;

	/* 多个客户对应一个用户，模型驱动使用id，而不使用对象 */
	private String ownerUser; // * 保存所属人的id
	/* 所属人的分配日期 */
	private String dispensePerson;
	/* #分配日期(经手人变更的日期) */
	private String dispenseDate;
	/* #共享标志 Y(共享)和N(不共享)， 默认值“N” */
	private Character shareFlag;
	/* #共享ID客户资料共享给业务人员 格式 #人事编号#人事编号...例如(#12#11#) */
	private String shareIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getNextTouchDate() {
		return nextTouchDate;
	}

	public void setNextTouchDate(String nextTouchDate) {
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

	public String getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(String ownerUser) {
		this.ownerUser = ownerUser;
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
