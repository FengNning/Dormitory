package cn.kgc.dao.impl;

import cn.kgc.dao.BaseDao;
import cn.kgc.dao.StudentDao;
import cn.kgc.entity.Student;
import cn.kgc.util.PageBean;
import cn.kgc.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {
    @Override
    public List<Student> Liststudent(PageBean pageBean, Student student) {
        ResultSet rs=null;
        List<Student> studentslist=new ArrayList<Student>();
        StringBuffer sql=new StringBuffer("SELECT a.*,b.dormName,c.dormBuildName FROM t_student a,t_dorm b,t_dormbuild c WHERE a.`dormId`=b.`dormId` AND c.`dormBuildId`=b.`dormBuildId`");
        try {
            if (StringUtil.isNotEmpty(student.getStuName())) {
                sql.append(" and stuName like '%" + student.getStuName() + "%'");
            } else if (student.getStudentId()!=null) {
                sql.append(" and studentId=" + student.getStudentId() );
            } else if (StringUtil.isNotEmpty(student.getDormName())) {
                sql.append(" and dormName='" + student.getDormName() + "'");
            }else if (StringUtil.isNotEmpty(student.getDormBuildName())){
                sql.append(" and c.dormBuildId="+student.getDormBuildName());
            }
            sql.append(" order by a.studentId");
            if (pageBean != null) {
                sql.append(" limit " + pageBean.getStart() + " ," + pageBean.getPageSize());
            }
            rs = this.executeQuery(sql.toString());
            while (rs.next()){
                Student student1=new Student();
                student1.setStudentId(rs.getInt("studentId"));
                student1.setStuName(rs.getString("stuName"));
                student1.setSex(rs.getString("sex"));
                student1.setDormBuildName(rs.getString("dormBuildName"));
                student1.setDormName(rs.getString("dormName"));
                student1.setTel(rs.getString("tel"));
                studentslist.add(student1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,pstmt,rs);
        }
        return studentslist;
    }

    @Override
    public int countStudent(Student student) {
        ResultSet rs=null;
        int total=0;
        try{
            StringBuffer sql=new StringBuffer("SELECT COUNT(1) AS total FROM t_student a,t_dorm b,t_dormbuild c WHERE a.`dormId`=b.`dormId` AND c.`dormBuildId`=b.`dormBuildId`");
            if (StringUtil.isNotEmpty(student.getStuName())) {
                sql.append(" and stuName like '%" + student.getStuName() + "%'");
            } else if (student.getStudentId()!=null) {
                sql.append(" and studentId=" + student.getStudentId());
            } else if (StringUtil.isNotEmpty(student.getDormName())) {
                sql.append(" and dormName='" + student.getDormName() + "'");
            }else if (StringUtil.isNotEmpty(student.getDormBuildName())){
                sql.append(" and c.dormBuildId="+student.getDormBuildName());
            }
            rs=this.executeQuery(sql.toString());
            if (rs.next()){
                total=rs.getInt("total");
            }else{
                total=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll(conn,pstmt,rs);
        }
        return total;
    }

    public Student getStudentById(Integer studentId){
        ResultSet rs=null;
        Student student=new Student();
        String sql="select t1.*,t2.dormBuildName from t_student t1,t_dormbuild t2  where t1.dormBuildId = t2.dormBuildId and t1.studentId=? ";
        try{
            rs=this.executeQuery(sql,studentId);
            while (rs.next()){
                student.setStudentId(rs.getInt("studentId"));
                student.setSex(rs.getString("sex"));
                student.setDormBuildId(rs.getInt("dormBuildId"));
                student.setTel(rs.getString("tel"));
                student.setStuName(rs.getString("stuName"));
                student.setPassword(rs.getString("password"));
                student.setDormId(rs.getInt("dormId"));
                student.setDormBuildName(rs.getString("dormBuildName"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    public int saveStudent(Student student){
        int count=0;
        String sql="insert into t_student(stuName,password,dormId,studentId,dormBuildId,sex,tel) values (?,?,?,?,?,?,?)";
        count=this.exceuteUpdate(sql,student.getStuName(),student.getPassword(),student.getDormId(),student.getStudentId(),student.getDormBuildId(),student.getSex(),student.getTel());
        return count;
    }

    public int updateStudent(Student student){
        int res=0;
        String sql="update t_student set stuName=?,password=?,dormId=?,studentId=?,dormBuildId=?,sex=?,tel=?  where studentId=?";
        res=this.exceuteUpdate(sql,student.getStuName(),student.getPassword(),student.getDormId(),student.getStudentId(),student.getDormBuildId(),student.getSex(),student.getTel(),student.getStudentId());
        return res;
    }

    @Override
    public boolean deleStudent(Integer studentId) {
        String sql="delete from t_student where studentId=?";
        Object[] params={studentId};
        boolean flag=false;
        int count=this.exceuteUpdate(sql,params);
        if (count>0){
            flag=true;
        }
        return flag;
    }
}
