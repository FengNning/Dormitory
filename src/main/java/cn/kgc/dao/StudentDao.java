package cn.kgc.dao;

import cn.kgc.entity.Student;
import cn.kgc.util.PageBean;

import java.util.List;

public interface StudentDao {
    public List<Student> Liststudent(PageBean pageBean, Student student);  //分页查询
    public int countStudent(Student student);  // 总页
    public Student getStudentById(Integer studentId); //指定ids
    public int saveStudent(Student student); //添加
    public int updateStudent(Student student);  //修改
    public boolean deleStudent(Integer studentId);  //删除
}
