package cn.kgc.entity;


public class Student {
    private Integer studentId;
    private String stuName;
    private String password;
    private Integer dormId;
    private String sex;
    private String tel;
    private String dormName;
    private String dormBuildName;
    private Integer dormBuildId;

    public Student( String stuName, String password, Integer dormId, String sex, String tel, Integer dormBuildId) {
        this.stuName = stuName;
        this.password = password;
        this.dormId = dormId;
        this.sex = sex;
        this.tel = tel;
        this.dormBuildId = dormBuildId;
    }
    public  Student(){}

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }


    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
