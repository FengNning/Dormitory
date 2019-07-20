package cn.kgc.dao.impl;

import cn.kgc.dao.BaseDao;
import cn.kgc.dao.LoginDao;
import cn.kgc.entity.Admin;
import cn.kgc.entity.DormManager;
import cn.kgc.entity.Student;
import cn.kgc.util.SecurityUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl extends BaseDao implements LoginDao {

    @Override
    public Object login(Object o) throws SQLException {
        Admin resultAdmin = null;
        DormManager resultDormManager = null;
        Student resultStudent = null;
        ResultSet rs = null;
        if(o instanceof Admin){
            Admin admin = (Admin) o;
            String sql = "select * from t_admin where userName = ? and password = ?";
            String pwd = SecurityUtil.getStrMD5(admin.getPassword());
            rs = executeQuery(sql,admin.getUserName(),pwd);
            while (rs.next()){
                resultAdmin = new Admin();
                resultAdmin.setAdminId(rs.getInt("adminId"));
                resultAdmin.setUserName(rs.getString("userName"));
                resultAdmin.setSex(rs.getString("sex"));
                resultAdmin.setTel(rs.getString("tel"));
            }
            return resultAdmin;
        }else if(o instanceof DormManager){
            DormManager dormManager = (DormManager) o;
            String sql = "select * from t_dormmanager where managerName = ? and password = ?";
            String pwd = SecurityUtil.getStrMD5(dormManager.getPassword());
            rs = executeQuery(sql,dormManager.getManagerName(),pwd);
            while (rs.next()){
                resultDormManager = new DormManager();
                resultDormManager.setDormBuildId(rs.getInt("dormManId"));
                resultDormManager.setManagerName(rs.getString("managerName"));
                resultDormManager.setDormBuildId(rs.getInt("dormBuildId"));
                resultDormManager.setSex(rs.getString("sex"));
                resultDormManager.setTel(rs.getString("tel"));
            }
            return resultDormManager;

        }else{

            Student student = (Student) o;
            String sql = "select * from t_student where stuName = ? and password = ?";
            String pwd = SecurityUtil.getStrMD5(student.getPassword());
            rs = executeQuery(sql,student.getStuName(),pwd);
            while (rs.next()){
                resultStudent = new Student();
                resultStudent.setStudentId(rs.getInt("studentId"));
                resultStudent.setStuName(rs.getString("stuName"));
                resultStudent.setDormId(rs.getInt("dormId"));
                resultStudent.setSex(rs.getString("sex"));
                resultStudent.setTel(rs.getString("tel"));
            }
            return resultStudent;
        }

    }

}
