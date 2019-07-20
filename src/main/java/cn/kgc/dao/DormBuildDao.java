package cn.kgc.dao;

import cn.kgc.entity.Dormbuild;

import java.sql.SQLException;
import java.util.List;

public interface DormBuildDao {

    public List<Dormbuild> listDormList() throws SQLException;
}
