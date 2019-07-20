package cn.kgc.service;


import cn.kgc.entity.Dormbuild;

import java.sql.SQLException;
import java.util.List;

public interface DormBuildService {

    public List<Dormbuild> listDormList() throws SQLException;
}
