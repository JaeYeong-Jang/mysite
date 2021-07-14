package com.javaex.vo;

public class UserVo {
	
	//fields
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String chk_agree;
	
	//constructors
	public UserVo() {
		
	}
		
	public UserVo(String id, String pw, String name, String gender) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
	}
	
	public UserVo(String id, String pw, String name, String gender, String chk_agree) {
		this();
		this.chk_agree = chk_agree;
		
	}
	
	//g/s
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getChk_agree() {
		return chk_agree;
	}
	public void setChk_agree(String chk_agree) {
		this.chk_agree = chk_agree;
	}
	
	//method
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", chk_agree=" + chk_agree
				+ "]";
	}
	
}
