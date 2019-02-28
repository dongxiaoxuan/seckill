package com.geek.ms.model;
/**
 * 异常信息模板
 */
public class ErrorResponseEntity {
	
	private int code;
    private String message;
    
    public ErrorResponseEntity() {
		super();
	}
	public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
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
	@Override
	public String toString() {
		return "ResponseEntity [code=" + code + ", message=" + message + "]";
	}

}
