package org.edupoll.model.dto.request;

public class AddReplyRequestData {
	String text;
	String password;
	String moimId;
	

	public String getMoimId() {
		return moimId;
	}

	public void setMoimId(String moimId) {
		this.moimId = moimId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
