package com.example.vendaa;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorCode {
    private String code;
    private String message;
    private String responseCodeGrouping;

    public ErrorCode(){

    }
    public ErrorCode(String message) {
        this.message = message;
    }

    public ErrorCode(String code, String message, String responseCodeGrouping) {
        this.code = code;
        this.message = message;
        this.responseCodeGrouping = responseCodeGrouping;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCodeGrouping() {
        return responseCodeGrouping;
    }

    public void setResponseCodeGrouping(String responseCodeGrouping) {
        this.responseCodeGrouping = responseCodeGrouping;
    }
}
