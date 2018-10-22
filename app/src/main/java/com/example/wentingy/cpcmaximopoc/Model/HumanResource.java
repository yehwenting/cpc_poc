package com.example.wentingy.cpcmaximopoc.Model;

/**
 * Created by Yehwenting on 2018/7/31.
 */

public class HumanResource {
    private String num;
    private String name;
    private String time;
    private String note;

    public HumanResource(String num, String name, String time, String note) {
        this.num = num;
        this.name = name;
        this.time = time;
        this.note = note;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
