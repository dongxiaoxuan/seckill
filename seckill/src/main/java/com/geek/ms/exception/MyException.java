package com.geek.ms.exception;
//自定义异常
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 2824336297555981047L;
	private int code;

    public MyException() {
        super();
    }

    public MyException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
