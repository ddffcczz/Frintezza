package com.example.frintezza;

import java.util.List;

public class JsonProtoData {
    private String action;
    private String token;
    private String status;
    private List<Routers> data;


    public JsonProtoData(String action, String token, String status, List<Routers> data) {
        this.action = action;
        this.token = token;
        this.status = status;
        this.data = data;
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

    public List<Routers> getData() {
        return data;
    }

}
