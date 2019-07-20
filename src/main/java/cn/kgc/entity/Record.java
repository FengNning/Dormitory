package cn.kgc.entity;

import java.util.Date;

public class Record {

    private Integer recordId;
    private Integer studentId;
    private Integer dormId;
    private Date recordDate;
    private String detail;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Record(Integer recordId, Integer studentId, Integer dormId, Date recordDate, String detail) {

        this.recordId = recordId;
        this.studentId = studentId;
        this.dormId = dormId;
        this.recordDate = recordDate;
        this.detail = detail;
    }

    public Record() {

    }
}
