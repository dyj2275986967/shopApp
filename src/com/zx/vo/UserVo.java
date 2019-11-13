package com.zx.vo;

public class UserVo {
	private int id;
	private String loginName;
	private  String password;
	private String okpassword;
	private String name;
	private int sex;
	private String email;
    private String phone;
    private String address;
    private int role;
    private String createDate;
    private String disable;
    private String active;
    private String yzm;
	
    
    
    
    

	public UserVo() {
		super();
	}
	public UserVo(String loginName, String password, String disable, String active) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.disable = disable;
		this.active = active;
	}
	public UserVo(String loginName, String password, String active) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.active = active;
		
	}
	public UserVo(String loginName, String password, String name, int sex, String email, String phone, String address,
			String createDate, int role, String disable,String active) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createDate = createDate;
		this.disable = disable;
		this.active=active;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @return the disable
	 */
	public String getDisable() {
		return disable;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * @param emall the emall to set
	 */

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param disable the disable to set
	 */
	public void setDisable(String disable) {
		this.disable = disable;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	/**
	 * @return the yzm
	 */
	public String getYzm() {
		return yzm;
	}
	/**
	 * @param yzm the yzm to set
	 */
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", loginName=" + loginName + ", password=" + password + ", name=" + name + ", sex="
				+ sex + ", emall=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role
				+ ", createDate=" + createDate + ", disable=" + disable + ", active=" + active + ", yzm=" + yzm + "]";
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the okpassword
	 */
	public String getOkpassword() {
		return okpassword;
	}
	/**
	 * @param okpassword the okpassword to set
	 */
	public void setOkpassword(String okpassword) {
		this.okpassword = okpassword;
	}
    
    
    
    
    

    
	

}
