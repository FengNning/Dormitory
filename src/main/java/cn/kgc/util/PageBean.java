package cn.kgc.util;

public class PageBean {

    private Integer currentPageNo;
    private Integer pageSize;
    //limit 后面的第一个参数
    private Integer start;

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        return (currentPageNo - 1) * pageSize;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public PageBean(Integer currentPageNo, Integer pageSize) {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
    }

    public PageBean() {

    }
}
