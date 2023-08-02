package org.edupoll.model.dto.response;

import java.util.List;

public class AttendanceJoinResponseData {
	
	boolean result;
	String errorMessage;
	
	int currentPerson;
	
	List<String> attendUserIds;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String error) {
		this.errorMessage = error;
	}
	public int getCurrentPerson() {
		return currentPerson;
	}
	public void setCurrentPerson(int currentPerson) {
		this.currentPerson = currentPerson;
	}
	public List<String> getAttendUserIds() {
		return attendUserIds;
	}
	public void setAttendUserids(List<String> attendUserIds) {
		this.attendUserIds = attendUserIds;
	}
}
