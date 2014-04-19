package com.social.models;

import java.sql.Timestamp;
import java.util.Calendar;

import com.social.common.Messages;

public class User {

	private String userPrimaryKey;
	private String id;
	private String password;
	private String firstName;
	private String lastName;
	private String position;
	private String gender;
	private String token;
	private Timestamp creationTimestamp;

	public User() {
		Calendar calendar = Calendar.getInstance();
		setId(Messages.UNKNOWN);
		setPassword(Messages.UNKNOWN);
		setFirstName(Messages.UNKNOWN);
		setLastName(Messages.UNKNOWN);
		setPosition(Messages.UNKNOWN);
		setGender(Messages.UNKNOWN);
		setToken(Messages.UNKNOWN);
		setCreationTimestamp(new Timestamp(calendar.getTimeInMillis()));

	}
	
	public User(String id, String pw, String first, String last, String pos, String gen) {
	
		setId(id);
		setPassword(pw);
		setFirstName(first);
		setLastName(last);
		setPosition(pos);
		setGender(gen);
	}
	
	public String getUserPrimaryKey() {
		return userPrimaryKey;
	}
	public void setUserPrimaryKey(String userPrimaryKey) {
		this.userPrimaryKey = userPrimaryKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Timestamp creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
