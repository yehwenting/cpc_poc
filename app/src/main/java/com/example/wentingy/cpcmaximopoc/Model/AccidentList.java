package com.example.wentingy.cpcmaximopoc.Model;

public class AccidentList {
    private String accidentId;
    private String insurance;
    private String nonempinjured;
    private String nonempdied;
    private String location;
    private String repman;
    private String createdate;
    private String type;
    private String empdied;
    private String remdy;
    private String insurance_description;
    private String rowStamp;
    private String description;
    private String financialLoss;
    private String secretDescription;
    private String secret;
    private String empinjured;
    private String dept;

    public AccidentList(String accidentId, String insurance, String nonempinjured, String nonempdied, String location, String repman, String createdate, String type, String empdied, String remdy, String insurance_description, String rowStamp, String description, String financialLoss, String secretDescription, String secret, String empinjured, String dept) {
        this.accidentId = accidentId;
        this.insurance = insurance;
        this.nonempinjured = nonempinjured;
        this.nonempdied = nonempdied;
        this.location = location;
        this.repman = repman;
        this.createdate = createdate;
        this.type = type;
        this.empdied = empdied;
        this.remdy = remdy;
        this.insurance_description = insurance_description;
        this.rowStamp = rowStamp;
        this.description = description;
        this.financialLoss = financialLoss;
        this.secretDescription = secretDescription;
        this.secret = secret;
        this.empinjured = empinjured;
        this.dept = dept;
    }

    public String getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(String accidentId) {
        this.accidentId = accidentId;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getNonempinjured() {
        return nonempinjured;
    }

    public void setNonempinjured(String nonempinjured) {
        this.nonempinjured = nonempinjured;
    }

    public String getNonempdied() {
        return nonempdied;
    }

    public void setNonempdied(String nonempdied) {
        this.nonempdied = nonempdied;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepman() {
        return repman;
    }

    public void setRepman(String repman) {
        this.repman = repman;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmpdied() {
        return empdied;
    }

    public void setEmpdied(String empdied) {
        this.empdied = empdied;
    }

    public String getRemdy() {
        return remdy;
    }

    public void setRemdy(String remdy) {
        this.remdy = remdy;
    }

    public String getInsurance_description() {
        return insurance_description;
    }

    public void setInsurance_description(String insurance_description) {
        this.insurance_description = insurance_description;
    }

    public String getRowStamp() {
        return rowStamp;
    }

    public void setRowStamp(String rowStamp) {
        this.rowStamp = rowStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinancialLoss() {
        return financialLoss;
    }

    public void setFinancialLoss(String financialLoss) {
        this.financialLoss = financialLoss;
    }

    public String getSecretDescription() {
        return secretDescription;
    }

    public void setSecretDescription(String secretDescription) {
        this.secretDescription = secretDescription;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEmpinjured() {
        return empinjured;
    }

    public void setEmpinjured(String empinjured) {
        this.empinjured = empinjured;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
