package cn.kgc.entity;

public class Dormbuild {
    private Integer dormBuildId;
    private String dormBuildName;
    private String dormBuildDetail;
    public Dormbuild(){}
    public Dormbuild(String dormBuildName,String dormBuildDetail){

        this.dormBuildName=dormBuildName;
        this.dormBuildDetail=dormBuildDetail;
    }

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

    public String getDormBuildDetail() {
        return dormBuildDetail;
    }

    public void setDormBuildDetail(String dormBuildDetail) {
        this.dormBuildDetail = dormBuildDetail;
    }
}
