package cn.kgc.dao.impl;

import cn.kgc.dao.BaseDao;
import cn.kgc.dao.DormManagerDao;
import cn.kgc.entity.DormManager;
import cn.kgc.util.PageBean;
import cn.kgc.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormManagerDaoImpl extends BaseDao implements DormManagerDao {

    @Override
    public List<DormManager> listDormManager(PageBean pageBean, DormManager dormManager) throws SQLException {
        ResultSet rs = null;
        List<DormManager> dormManagerList = new ArrayList<DormManager>();
        try {
            StringBuffer sb = new StringBuffer("select t1.*,t2.dormBuildName from t_dormManager t1,t_dormbuild t2 " +
                    "where t1.dormBuildId = t2.dormBuildId");
            if (StringUtil.isNotEmpty(dormManager.getManagerName())) {
                sb.append(" and t1.managerName like '%" + dormManager.getManagerName() + "%'");
            } else if (StringUtil.isNotEmpty(dormManager.getSex())) {
                sb.append(" and t1.sex = '" + dormManager.getSex() + "'");
            } else if (dormManager.getDormBuildId() != null) {
                sb.append(" and t1.dormBuildId = " + dormManager.getDormBuildId());
            }
            sb.append(" ORDER BY t1.dormManId");
            if (pageBean != null) {
                sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
            }
            rs = this.executeQuery(sb.toString());
            while (rs.next()) {
                DormManager dm = new DormManager();
                dm.setDormManId(rs.getInt("dormManId"));
                dm.setManagerName(rs.getString("managerName"));
                dm.setDormBuildId(rs.getInt("dormBuildId"));
                dm.setSex(rs.getString("sex"));
                dm.setTel(rs.getString("tel"));
                dm.setDormBuildName(rs.getString("dormBuildName"));
                dormManagerList.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }

        return dormManagerList;
    }

    @Override
    public int countDormManager(DormManager dormManager) throws Exception {
        ResultSet rs = null;
        int total = 0;
        try {
            StringBuffer sb = new StringBuffer("select count(1) as total from t_dormManager t1,t_dormbuild t2 " +
                    "where t1.dormBuildId = t2.dormBuildId");
            if (StringUtil.isNotEmpty(dormManager.getManagerName())) {
                sb.append(" and t1.managerName like '%" + dormManager.getManagerName() + "%'");
            } else if (StringUtil.isNotEmpty(dormManager.getSex())) {
                sb.append(" and t1.sex = '" + dormManager.getSex() + "'");
            } else if (dormManager.getDormBuildId() != null) {
                sb.append(" and t1.dormBuildId = " + dormManager.getDormBuildId());
            }
            rs = this.executeQuery(sb.toString());
            if (rs.next()) {
                total = rs.getInt("total");
            } else {
                total = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return total;
    }

    @Override
    public DormManager getDormManagerById(Integer dormManId) {
        ResultSet rs = null;
        DormManager dormManager = new DormManager();
        String sql = "select * from t_dormmanager where dormManId = ?";
        try {
            rs = this.executeQuery(sql, dormManId);
            while (rs.next()) {
                dormManager.setDormManId(rs.getInt("dormManId"));
                dormManager.setManagerName(rs.getString("managerName"));
                dormManager.setDormBuildId(rs.getInt("dormBuildId"));
                dormManager.setSex(rs.getString("sex"));
                dormManager.setTel(rs.getString("tel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return dormManager;
    }

    @Override
    public int updateDormManager(DormManager dormManager) {
        int count = 0;
        String sql = "update t_dormManager set managerName = ?,password=?,dormBuildId=?,sex=?,tel=? where dormManId=?";
        count = this.exceuteUpdate(sql,dormManager.getManagerName(),dormManager.getPassword(),
                dormManager.getDormBuildId(),dormManager.getSex(),dormManager.getTel(),dormManager.getDormManId());
        return count;
    }

    @Override
    public int saveDormManager(DormManager dormManager) {
        int count = 0;
        String sql = "INSERT INTO t_dormmanager (managerName,password,dormBuildId,sex,tel) values(?,?,?,?,?)";
        count = this.exceuteUpdate(sql, dormManager.getManagerName(),
                dormManager.getPassword(), dormManager.getDormBuildId(), dormManager.getSex(), dormManager.getTel());
        return count;
    }

    @Override
    public int deleteManagerById(Integer dormManId) {
        int num = 0;
        String sql = "delete from t_dormManager where dormManId=?";
        num = this.exceuteUpdate(sql,dormManId);
        return num;
    }


}
