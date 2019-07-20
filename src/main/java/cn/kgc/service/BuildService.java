package cn.kgc.service;

import cn.kgc.entity.Dormbuild;
import cn.kgc.util.PageBean;

import java.util.List;

public interface BuildService {
    public List<Dormbuild> listDormbuild(PageBean pageBean, Dormbuild dormbuild);
    public int countbuild(Dormbuild dormbuild);
    public int savebuild(Dormbuild dormbuild);
    public int updatebuild(Dormbuild dormbuild);
    public Dormbuild getbuildById(int id);
    public boolean delebuild(int id);
}
