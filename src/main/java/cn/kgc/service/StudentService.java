package cn.kgc.service;

import cn.kgc.entity.Student;
import cn.kgc.util.PageBean;

import java.util.List;

public interface StudentService {
    public List<Student> Liststudent(PageBean pageBean, Student student);
    public int countStudent(Student student);
    public Student getStudentById(Integer studentId);
    public int saveStudent(Student student);
    public int updateStudent(Student student);
    public boolean deleStudent(Integer studentId);
}
