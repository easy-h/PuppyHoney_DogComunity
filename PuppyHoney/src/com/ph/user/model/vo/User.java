package com.ph.user.model.vo;

import java.sql.Date;

public class User {
 
	private String userId;
	private String userPw;
	private String userNick;
	private String userEmail;
	private String userName;
	private Date userBirth;
	private String userDogName;
	private Date userDogBirth;
	private String userImageOrigin;
	private String userImageRename;
	private String userBookmark;
	private Date userJoinDate;
	private String userLeave;
	private int userEmailChecked;
	private String userEmailHash;
	
	
	public User() {}


	public User(String userId, String userPw, String userNick, String userEmail, String userName, Date userBirth,
			String userDogName, Date userDogBirth, String userImageOrigin, String userImageRename, String userBookmark,
			Date userJoinDate, String userLeave, int userEmailChecked, String userEmailHash) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNick = userNick;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userDogName = userDogName;
		this.userDogBirth = userDogBirth;
		this.userImageOrigin = userImageOrigin;
		this.userImageRename = userImageRename;
		this.userBookmark = userBookmark;
		this.userJoinDate = userJoinDate;
		this.userLeave = userLeave;
		this.userEmailChecked = userEmailChecked;
		this.userEmailHash = userEmailHash;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPw() {
		return userPw;
	}


	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}


	public String getUserNick() {
		return userNick;
	}


	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Date getUserBirth() {
		return userBirth;
	}


	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}


	public String getUserDogName() {
		return userDogName;
	}


	public void setUserDogName(String userDogName) {
		this.userDogName = userDogName;
	}


	public Date getUserDogBirth() {
		return userDogBirth;
	}


	public void setUserDogBirth(Date userDogBirth) {
		this.userDogBirth = userDogBirth;
	}


	public String getUserImageOrigin() {
		return userImageOrigin;
	}


	public void setUserImageOrigin(String userImageOrigin) {
		this.userImageOrigin = userImageOrigin;
	}


	public String getUserImageRename() {
		return userImageRename;
	}


	public void setUserImageRename(String userImageRename) {
		this.userImageRename = userImageRename;
	}


	public String getUserBookmark() {
		return userBookmark;
	}


	public void setUserBookmark(String userBookmark) {
		this.userBookmark = userBookmark;
	}


	public Date getUserJoinDate() {
		return userJoinDate;
	}


	public void setUserJoinDate(Date userJoinDate) {
		this.userJoinDate = userJoinDate;
	}


	public String getUserLeave() {
		return userLeave;
	}


	public void setUserLeave(String userLeave) {
		this.userLeave = userLeave;
	}


	public int getUserEmailChecked() {
		return userEmailChecked;
	}


	public void setUserEmailChecked(int userEmailChecked) {
		this.userEmailChecked = userEmailChecked;
	}


	public String getUserEmailHash() {
		return userEmailHash;
	}


	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + ", userNick=" + userNick + ", userEmail=" + userEmail
				+ ", userName=" + userName + ", userBirth=" + userBirth + ", userDogName=" + userDogName
				+ ", userDogBirth=" + userDogBirth + ", userImageOrigin=" + userImageOrigin + ", userImageRename="
				+ userImageRename + ", userBookmark=" + userBookmark + ", userJoinDate=" + userJoinDate + ", userLeave="
				+ userLeave + ", userEmailChecked=" + userEmailChecked + ", userEmailHash=" + userEmailHash + "]";
	}

	
	
}
