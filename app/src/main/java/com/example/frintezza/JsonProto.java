package com.example.frintezza;

public class JsonProto {
    private String action;
    private String token;
    private String status;

    public JsonProto(String action, String token, String status) {
        this.action = action;
        this.token = token;
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
