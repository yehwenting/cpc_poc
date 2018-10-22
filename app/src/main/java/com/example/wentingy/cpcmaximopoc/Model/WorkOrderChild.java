package com.example.wentingy.cpcmaximopoc.Model;

/**
 * Created by Yehwenting on 2018/7/30.
 */

public class WorkOrderChild {
    private String num;
    private String name;
    private String status;
    private String detail;

    public WorkOrderChild(String num, String name, String status, String detail) {
        this.num = num;
        this.name = name;
        this.status = status;
        this.detail = detail;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
