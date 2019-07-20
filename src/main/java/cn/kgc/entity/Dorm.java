package cn.kgc.entity;

public class Dorm {

    private Integer dormId;
    private Integer dormBuildId;
    private String dormName;
    private String dormType;
    private String dormTel;

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getDormType() {
        return dormType;
    }

    public void setDormType(String dormType) {
        this.dormType = dormType;
    }

    public String getDormTel() {
        return dormTel;
    }

    public void setDormTel(String dormTel) {
        this.dormTel = dormTel;
    }

    public Dorm(Integer dormId, Integer dormBuildId, String dormName, String dormType, String dormTel) {

        this.dormId = dormId;
        this.dormBuildId = dormBuildId;
        this.dormName = dormName;
        this.dormType = dormType;
        this.dormTel = dormTel;
    }

    public Dorm() {

    }
}
