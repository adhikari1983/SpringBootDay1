/**
 * ApplicationMessage$.java
 * <p>
 * Description: TODO - Describe this class
 * <p>
 * Created by Kiran Adhikari on 7/4/2025
 */
package com.utility;

public class ApplicationMessage {

    private int code;
    private String message;

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
        return "ApplicationMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
