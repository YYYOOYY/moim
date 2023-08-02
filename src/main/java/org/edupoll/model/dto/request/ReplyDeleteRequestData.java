package org.edupoll.model.dto.request;

public class ReplyDeleteRequestData {
	String moimId;
	String pass;
	Integer replyId;
	String replyPass;
	
	public String getMoimId() {
		return moimId;
	}
	public void setMoimId(String moimId) {
		this.moimId = moimId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public String getReplyPass() {
		return replyPass;
	}
	public void setReplyPass(String replyPass) {
		this.replyPass = replyPass;
	}
}
