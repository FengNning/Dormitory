package cn.kgc.entity;

public class DormManager {

    private Integer dormManId;
    private String managerName;
    private String password;
    private Integer dormBuildId;
    private String sex;
    private String tel;
    private String dormBuildName;



    public Integer getDormManId() {
        return dormManId;
    }

    public void setDormManId(Integer dormManId) {
        this.dormManId = dormManId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
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

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public DormManager(String managerName, String password, Integer dormBuildId, String sex, String tel) {

        this.managerName = managerName;
        this.password = password;
        this.dormBuildId = dormBuildId;
        this.sex = sex;
        this.tel = tel;
    }

    public DormManager() {

    }
}
