package cn.kgc.service.impl;

import cn.kgc.dao.DormBuildDao;
import cn.kgc.dao.impl.DormBuildDaoImpl;
import cn.kgc.entity.Dormbuild;
import cn.kgc.service.DormBuildService;

import java.sql.SQLException;
import java.util.List;

public class DormBuildServiceImpl implements DormBuildService {

    DormBuildDao dormBuildDao = new DormBuildDaoImpl();

    @Override
    public List<Dormbuild> listDormList() throws SQLException {
        return dormBuildDao.listDormList();
    }

    public static void main(String[] args) {

        DormBuildServiceImpl service = new DormBuildServiceImpl();
        List<Dormbuild> dormBuildList = null;
        try {
            dormBuildList = service.listDormList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Dormbuild dormBuild : dormBuildList) {
            System.out.println(dormBuild.getDormBuildName());

        }

    }
}
