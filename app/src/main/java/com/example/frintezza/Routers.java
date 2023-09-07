package com.example.frintezza;

public class Routers {
    private long id;
    private String name;
    private String ipaddress;
    private boolean online;

    public Routers(long id, String name, String ipaddress, boolean online) {
        this.id = id;
        this.name = name;
        this.ipaddress = ipaddress;
        this.online = online;
    }
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getIpaddress() {return ipaddress;}
    public void setIpaddress(String ipaddress) {this.ipaddress = ipaddress;}
    public boolean isOnline() {return online;}

    @Override
    public String toString() {
        return "Routers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipaddress='" + ipaddress + '\'' +
                ", online=" + online +
                '}';
    }
}
