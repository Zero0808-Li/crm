package cn.cuit.crm.domain;

import java.sql.Date;

/**
 * <P>
 * Desc:（人事）即系统用户
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 人事管理PO对象User多的一端 */
@SuppressWarnings("serial")
public class SysUser implements java.io.Serializable {
	private Integer id;
	/* 创建人 */
	private String creator;
	/* 创建时间 yyyy-mm-dd HH24:mm:ss */
	private String createTime;
	/* 修改人 */
	private String updater; //
	/* 修建时间 yyyy-mm-dd HH24:mm:ss */
	private String updateTime;
	/* 备注 */
	private String remark;
	/* 用户名 */
	private String name;
	/* 中文名 */
	private String cnname;
	/* 密码 */
	private String password;
	/* 家庭地址 */
	private String address;
	/* 家庭电话 */
	private String telephone;
	/* 电子邮件 */
	private String email;
	/* (合同生效)起始有效期 yyyy-mm-dd，注意这里的Date类型是使用的sql包中的 */
	private Date beginDate;
	/* (合同失效)终止有效期 yyyy-mm-dd */
	private Date endDate;

	/* Hibernate关联，在SysRole、SysUserGroup类中使用集合在关联多的一端SysUser,这里都是配置的单向的 */
	private SysRole sysRole; // 多个用户对应一个角色(权限组)（外键）
	private SysUserGroup sysUserGroup; // 多个用户属于一个部门（外键）

	/* 未命名 */
	private String accessFileLevel;
	/* 状态(Y N) */
	private String status;
	/* 推荐人 */
	private String commendMan;
	/* 移动电话 */
	private String movetelePhone;
	/* 现住宅地址 */
	private String nowAddress;
	/* 现住宅电话 */
	private String nowtelePhone;
	/* 身份证号码 */
	private String identityCode;
	/* 社会保险号 */
	private String insuranceCode;
	/* 紧急联系人 */
	private String instancyLinkman;
	/* 紧急联系人电话 */
	private String instancytelePhone;
	/* 性别 */
	private String sex;
	/* 出生日期 */
	private Date birthday;
	/* 职员类别 */
	private String personnelType;
	/* 职务 */
	private String duty;
	/* 入职日期 */
	private Date workDate;
	/* 最高学历 */
	private String highSchool;
	/* 毕业学校 */
	private String finishSchool;
	/* 毕业日期 */
	private Date finishSchoolDate;
	/* 配偶姓名 */
	private String consortName;
	/* 子女姓名 */
	private String youngoneName;
	/* 办公电话 */
	private String officetelePhone;
	/* 配偶电话 */
	private String consorttelePhone;
	/* 业余爱好 */
	private String avocation;
	/* 配偶工作单位 */
	private String consortCompany;
	/* 偏好特长 */
	private String strongSuit;
	/* 信息沟通 */
	private String commUniCate;
	/* 培训情况 */
	private String bringup;
	/* 组织能力 */
	private String organise;
	/* 分析能力 */
	private String analyse;
	/* 计划能力 */
	private String planing;
	/* 人员开发 */
	private String empolder;
	/* 人际关系 */
	private String relation;

	public SysUser() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysUserGroup getSysUserGroup() {
		return sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
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

	public Date getFinishSchoolDate() {
		return finishSchoolDate;
	}

	public void setFinishSchoolDate(Date finishSchoolDate) {
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
