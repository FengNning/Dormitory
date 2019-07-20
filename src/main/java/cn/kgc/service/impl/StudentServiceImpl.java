package cn.kgc.service.impl;

import cn.kgc.dao.StudentDao;
import cn.kgc.dao.impl.StudentDaoImpl;
import cn.kgc.entity.Student;
import cn.kgc.service.StudentService;
import cn.kgc.util.PageBean;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao=new StudentDaoImpl();
    @Override
    public List<Student> Liststudent(PageBean pageBean, Student student) {
        return studentDao.Liststudent(pageBean,student);
    }

    @Override
    public int countStudent(Student student) {
        return studentDao.countStudent(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public int saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public boolean deleStudent(Integer studentId) {
        return studentDao.deleStudent(studentId);
    }
}
