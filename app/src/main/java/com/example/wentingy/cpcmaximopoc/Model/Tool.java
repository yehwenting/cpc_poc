package com.example.wentingy.cpcmaximopoc.Model;

/**
 * Created by Yehwenting on 2018/7/31.
 */

public class Tool {
    private String id;
    private String type;
    private String info;
    private String name;

    public Tool(String id, String type, String info, String name) {
        this.id = id;
        this.type = type;
        this.info = info;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
