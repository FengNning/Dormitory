package cn.kgc.entity;

public class Admin {

    private Integer adminId;
    private String userName;
    private String password;
    private String sex;
    private String tel;

    public Admin(Integer adminId, String userName, String password, String sex, String tel) {
        this.adminId = adminId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.tel = tel;
    }

    public Admin() {

    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
