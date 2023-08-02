package org.edupoll.model.dto.response;

public class TestResponseData {
	int code;
	String message;
	
	String[] result;

	public TestResponseData(int code, String message, String[] result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getResult() {
		return result;
	}

	public void setResult(String[] result) {
		this.result = result;
	}
	
	
}
