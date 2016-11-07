package cn.cuit.crm.web.form;

import java.io.Serializable;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/*
 * 说明： VO对象(用户输入的全是字符串),特别要注意，时间类型，和表关联的外键字段的类型都是String，注意和对应的PO对象里面的区别：自己看就明白了
 */
@SuppressWarnings("serial")
public class SysUserForm implements Serializable {

	private String id;
	private String creator; // 创建人
	private String createTime; // 创建时间 yyyy-mm-dd HH24:mm:ss
	private String updater; // 修改人
	private String updateTime; // 修建时间 yyyy-mm-dd HH24:mm:ss
	private String remark;
	private String name;
	private String cnname;
	private String password;
	private String address;
	private String telephone;
	private String email;
	/* 时间类型在VO对象中，全部使用String类型 */
	private String beginDate; // 起始有效期 yyyy-mm-dd，注意这里的Date类型是使用的sql包中的
	private String endDate; // 终止有效期 yyyy-mm-dd

	/* Hibernate关联，在SysRole、SysUserGroup类中使用集合在关联多的一端SysUser */
	private String roleId; // 多个用户对应一个角色(权限组)
	private String groupId; // 多个用户属于一个部门

	private String accessFileLevel;
	private String status; // 状态(Y N)
	private String commendMan;
	private String movetelePhone;
	private String nowAddress;
	private String nowtelePhone;
	private String identityCode;
	private String insuranceCode;
	private String instancyLinkman;
	private String instancytelePhone;
	private String sex;
	private String birthday; // 出生日期
	private String personnelType;
	private String duty;
	private String workDate; // 入职日期
	private String highSchool;
	private String finishSchool;
	private String finishSchoolDate; // 毕业日期

	private String consortName;
	private String youngoneName;
	private String officetelePhone;
	private String consorttelePhone;
	private String avocation;
	private String consortCompany;
	private String strongSuit;
	private String commUniCate;
	private String bringup;
	private String organise;
	private String analyse;
	private String planing;
	private String empolder;
	private String relation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAccessFileLevel() {
		return accessFileLevel;
	}

	public void setAccessFileLevel(String accessFileLevel) {
		this.accessFileLevel = accessFileLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommendMan() {
		return commendMan;
	}

	public void setCommendMan(String commendMan) {
		this.commendMan = commendMan;
	}

	public String getMovetelePhone() {
		return movetelePhone;
	}

	public void setMovetelePhone(String movetelePhone) {
		this.movetelePhone = movetelePhone;
	}

	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}

	public String getNowtelePhone() {
		return nowtelePhone;
	}

	public void setNowtelePhone(String nowtelePhone) {
		this.nowtelePhone = nowtelePhone;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getInstancyLinkman() {
		return instancyLinkman;
	}

	public void setInstancyLinkman(String instancyLinkman) {
		this.instancyLinkman = instancyLinkman;
	}

	public String getInstancytelePhone() {
		return instancytelePhone;
	}

	public void setInstancytelePhone(String instancytelePhone) {
		this.instancytelePhone = instancytelePhone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	public String getFinishSchool() {
		return finishSchool;
	}

	public void setFinishSchool(String finishSchool) {
		this.finishSchool = finishSchool;
	}

	public String getFinishSchoolDate() {
		return finishSchoolDate;
	}

	public void setFinishSchoolDate(String finishSchoolDate) {
		this.finishSchoolDate = finishSchoolDate;
	}

	public String getConsortName() {
		return consortName;
	}

	public void setConsortName(String consortName) {
		this.consortName = consortName;
	}

	public String getYoungoneName() {
		return youngoneName;
	}

	public void setYoungoneName(String youngoneName) {
		this.youngoneName = youngoneName;
	}

	public String getOfficetelePhone() {
		return officetelePhone;
	}

	public void setOfficetelePhone(String officetelePhone) {
		this.officetelePhone = officetelePhone;
	}

	public String getConsorttelePhone() {
		return consorttelePhone;
	}

	public void setConsorttelePhone(String consorttelePhone) {
		this.consorttelePhone = consorttelePhone;
	}

	public String getAvocation() {
		return avocation;
	}

	public void setAvocation(String avocation) {
		this.avocation = avocation;
	}

	public String getConsortCompany() {
		return consortCompany;
	}

	public void setConsortCompany(String consortCompany) {
		this.consortCompany = consortCompany;
	}

	public String getStrongSuit() {
		return strongSuit;
	}

	public void setStrongSuit(String strongSuit) {
		this.strongSuit = strongSuit;
	}

	public String getCommUniCate() {
		return commUniCate;
	}

	public void setCommUniCate(String commUniCate) {
		this.commUniCate = commUniCate;
	}

	public String getBringup() {
		return bringup;
	}

	public void setBringup(String bringup) {
		this.bringup = bringup;
	}

	public String getOrganise() {
		return organise;
	}

	public void setOrganise(String organise) {
		this.organise = organise;
	}

	public String getAnalyse() {
		return analyse;
	}

	public void setAnalyse(String analyse) {
		this.analyse = analyse;
	}

	public String getPlaning() {
		return planing;
	}

	public void setPlaning(String planing) {
		this.planing = planing;
	}

	public String getEmpolder() {
		return empolder;
	}

	public void setEmpolder(String empolder) {
		this.empolder = empolder;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
