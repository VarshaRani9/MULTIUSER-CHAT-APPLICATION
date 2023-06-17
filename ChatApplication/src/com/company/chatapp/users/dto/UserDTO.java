package com.company.chatapp.users.dto;

public class UserDTO {
private String userid;
private char[] password;
public UserDTO() {
	
}
public UserDTO(String userid, char[] password) {
	super();
	this.userid = userid;
	this.password = password;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public char[] getPassword() {
	return password;
}
public void setPassword(char[] password) {
	this.password = password;
}

}
