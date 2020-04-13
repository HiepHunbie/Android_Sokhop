package com.example.ev.SoKhop.Base;

public class BaseData {
	int error_code = 0;
	
	String message = "";

	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "BaseData [error_code=" + error_code + ", message=" + message
				+ "]";
	}
	
	
}
