package com.example.wentingy.cpcmaximopoc.Model;

/**
 * Created by Yehwenting on 2018/8/1.
 */

public class Record {
    private String unit;
    private String d_name;
    private String name;
    private String startTime;
    private String checkTime;
    private String comment;

    public Record(String unit, String d_name, String name, String startTime, String checkTime, String comment) {
        this.unit = unit;
        this.d_name = d_name;
        this.name = name;
        this.startTime = startTime;
        this.checkTime = checkTime;
        this.comment = comment;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
