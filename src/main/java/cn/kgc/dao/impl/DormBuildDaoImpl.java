package cn.kgc.dao.impl;

import cn.kgc.dao.BaseDao;
import cn.kgc.dao.DormBuildDao;
import cn.kgc.entity.Dormbuild;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormBuildDaoImpl extends BaseDao implements DormBuildDao {
    @Override
    public List<Dormbuild> listDormList() throws SQLException {
        ResultSet rs = null;
        List<Dormbuild> dormBuildList = new ArrayList<Dormbuild>();
        String sql = "select * from t_dormbuild";
        try {
            rs = this.executeQuery(sql);
            while (rs.next()){
                Dormbuild dormBuild = new Dormbuild();
                dormBuild.setDormBuildId(rs.getInt("dormBuildId"));
                dormBuild.setDormBuildName(rs.getString("dormBuildName"));
                dormBuild.setDormBuildDetail(rs.getString("dormBuildDetail"));
                dormBuildList.add(dormBuild);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmt,rs);
        }
        return dormBuildList;
    }
}
