package cn.kgc.dao.impl;

import cn.kgc.dao.BaseDao;
import cn.kgc.dao.BuildDao;
import cn.kgc.entity.Dormbuild;
import cn.kgc.util.PageBean;
import cn.kgc.util.StringUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BuildDaoImpl extends BaseDao implements BuildDao {
    @Override
    public List<Dormbuild> listDormbuild(PageBean pageBean, Dormbuild dormbuild) {
        StringBuffer sql=new StringBuffer("SELECT * FROM t_dormbuild");
        List<Dormbuild> list=new ArrayList<Dormbuild>();
        if (StringUtil.isNotEmpty(dormbuild.getDormBuildName())){
            sql.append(" where dormBuildName like '%"+dormbuild.getDormBuildName()+"%'");
        }
        if (pageBean!=null){
            sql.append(" limit "+pageBean.getStart()+" ,"+pageBean.getPageSize());
        }
        ResultSet rs=this.executeQuery(sql.toString());
        try{
            while (rs.next()){
                Dormbuild db=new Dormbuild();
                db.setDormBuildId(rs.getInt("dormBuildId"));
                db.setDormBuildName(rs.getString("dormBuildName"));
                db.setDormBuildDetail(rs.getString("dormBuildDetail"));
                list.add(db);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll(conn,pstmt,rs);
        }
        return list;
    }

    @Override
    public int countbuild(Dormbuild dormbuild) {
        int total=0;
        StringBuffer sql=new StringBuffer("SELECT COUNT(1) FROM t_dormBuild ");
        if (StringUtil.isNotEmpty(dormbuild.getDormBuildName())){
            sql.append(" where dormBuildName like '%"+dormbuild.getDormBuildName()+"%'");
        }
        ResultSet rs=this.executeQuery(sql.toString());
        try{
            if (rs.next()){
                total=rs.getInt("count(1)");
            }else {
                total=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll(conn,pstmt,rs);
        }
        return total;
    }

    @Override
    public int savebuild(Dormbuild dormbuild) {
        int count;
        String sql="INSERT INTO t_dormbuild (dormBuildId,dormBuildName,dormBuildDetail) values (?,?,?)";
        count=this.exceuteUpdate(sql,dormbuild.getDormBuildId(),dormbuild.getDormBuildName(),dormbuild.getDormBuildDetail());
        closeAll(conn,pstmt,null);
        return count;
    }

    @Override
    public int updatebuild(Dormbuild dormbuild) {
        int count;
        String sql="update t_dormbuild set dormBuildName=?,dormBuildDetail=? where dormBuildId=?";
        Object[] params={dormbuild.getDormBuildName(),dormbuild.getDormBuildDetail(),dormbuild.getDormBuildId()};
        count=this.exceuteUpdate(sql,params);
        closeAll(conn,pstmt,null);
        return count;
    }

    @Override
    public Dormbuild getbuildById(int id) {
        String sql="select * from t_dormbuild where dormBuildId=?";
        Object[] params={id};
        ResultSet rs=this.executeQuery(sql,params);
        Dormbuild dormbuild=new Dormbuild();
        try {
            while (rs.next()){
                dormbuild.setDormBuildId(rs.getInt("dormBuildId"));
                dormbuild.setDormBuildName(rs.getString("dormBuildName"));
                dormbuild.setDormBuildDetail(rs.getString("dormBuildDetail"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll(conn,pstmt,rs);
        }
        return dormbuild;
    }

    @Override
    public boolean delebuild(int id) {
        boolean flag=false;
        String sql="delete from t_dormbuild where dormBuildId=?";
        Object[] params={id};
        int i=this.exceuteUpdate(sql,params);
        if (i>0){
            flag=true;
        }
        return flag;
    }

}
