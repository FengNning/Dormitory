package cn.kgc.web;

import cn.kgc.entity.Dormbuild;
import cn.kgc.entity.Student;
import cn.kgc.service.DormBuildService;
import cn.kgc.service.StudentService;
import cn.kgc.service.impl.DormBuildServiceImpl;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.util.PageBean;
import cn.kgc.util.PropertiesUtil;
import cn.kgc.util.SecurityUtil;
import cn.kgc.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns="/student")
public class StudentServlet extends HttpServlet {
    StudentService studentService=new StudentServiceImpl();
    DormBuildService dormBuildService=new DormBuildServiceImpl();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("action:" + action);
        HttpSession session = request.getSession();
        Student student=new Student();
        String page = request.getParameter("page");
        System.out.println("page:" + page);
        String searchType = request.getParameter("searchType");
        System.out.println("searchType:" + searchType);
        String s_studentText = request.getParameter("s_studentText");
        String buildToSelect=request.getParameter("buildToSelect");
        System.out.println("s_studentText:" + s_studentText);
        if ("preSave".equals(action)) {
            studentPreSave(request, response);
            return;
        } else if ("save".equals(action)) {
            studentSave(request, response);
            return;
        }else if ("delete".equals(action)) {
            studentDelete(request,response);
            return;
        }else {if ("list".equals(action)){
            session.removeAttribute("s_studentText");
            session.removeAttribute("searchType");
        }else if ("search".equals(action)) {
            student.setDormBuildName(buildToSelect);
            session.setAttribute("buildToSelect",buildToSelect);
            if (StringUtil.isNotEmpty(s_studentText)) {
                if ("name".equals(searchType)) {
                    student.setStuName(s_studentText);
                } else if ("number".equals(searchType)) {
                    student.setStudentId(Integer.parseInt(s_studentText));
                } else if ("dorm".equals(searchType)) {
                    student.setDormName(s_studentText);
                }
                session.setAttribute("searchType", searchType);
                session.setAttribute("s_studentText", s_studentText);
            } else {
                session.removeAttribute("s_studentText");
                session.removeAttribute("searchType");
            }
        }else {
            if (StringUtil.isNotEmpty(s_studentText)) {
                if ("name".equals(searchType)) {
                    student.setStuName(s_studentText);
                } else if ("number".equals(searchType)) {
                    student.setStudentId(Integer.parseInt(s_studentText));
                } else if ("dorm".equals(searchType)) {
                    student.setDormName(s_studentText);
                }
                session.setAttribute("searchType", searchType);
                session.setAttribute("s_studentText", s_studentText);
            }
            if (StringUtil.isEmpty(s_studentText)) {
                Object o1 = session.getAttribute("s_studentText");
                Object o2 = session.getAttribute("searchType");
                if (o1 != null && o2 != null) {
                    if ("name".equals(searchType)) {
                        student.setStuName(s_studentText);
                    } else if ("number".equals(searchType)) {
                        student.setStudentId(Integer.parseInt(s_studentText));
                    } else if ("dorm".equals(searchType)) {
                        student.setDormName(s_studentText);
                    }
                }
            }
        }
        }
        if (StringUtil.isEmpty(page)){
            page="1";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
        try {
            System.out.println("条件1：" + student.getStuName());
            System.out.println("条件2：" + student.getStudentId());
            System.out.println("条件3：" + student.getDormName());
            List<Dormbuild> dormBuildList=dormBuildService.listDormList();
            System.out.println("dormBuildList"+dormBuildList.size());
            request.setAttribute("dormBuildList",dormBuildList);
            List<Student> studentList = studentService.Liststudent(pageBean, student);

            int total = studentService.countStudent(student);
            String pageCode = this.getPageInfo(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
            System.out.println("pageCode:" + pageCode);
            request.setAttribute("pageCode", pageCode);
            request.setAttribute("studentList", studentList);
            request.setAttribute("mainPage", "admin/student.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getPageInfo(int totalNum, int currentPage, int pageSize){
        //总页数
        int totalPage=totalNum%pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        System.out.println("totalPage:"+totalPage);
        System.out.println("totalNum:"+totalNum);
        StringBuffer pageCode = new StringBuffer();
        if (totalPage>0){
            if (currentPage==1){
                pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }else {
                pageCode.append("<li ><a href='student?page=1'>首页</a></li>");
                pageCode.append("<li ><a href='student?page="+(currentPage-1)+"'>上一页</a></li>");
            }if (currentPage==totalPage){
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
                pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
            }else {
                pageCode.append("<li ><a href='student?page=" + (currentPage + 1) + "'>下一页</a></li>");
                pageCode.append("<li> <a href='student?page=" + totalPage + "'>尾页</a></li>");
            }
        }else {
            pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
        }
        return pageCode.toString();
    }
    private void studentPreSave(HttpServletRequest request ,HttpServletResponse response){
        Student student=null;
        String studentId=request.getParameter("studentId");
        try{
            List<Dormbuild> dormBuildList=dormBuildService.listDormList();
            System.out.println("aaaaaaaaaaaaa"+dormBuildList.size());
            if (StringUtil.isNotEmpty(studentId)){
                student=studentService.getStudentById(Integer.parseInt(studentId));
                System.out.println("查询出的结果为:"+student);
                request.setAttribute("student",student);
                request.setAttribute("dormBuildList",dormBuildList);
                request.setAttribute("mainPage","admin/studentSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request,response);
            }else{
                request.setAttribute("dormBuildList",dormBuildList);
                request.setAttribute("mainPage","admin/studentSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request,response);
            }
        }catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void studentSave(HttpServletRequest request,HttpServletResponse response) {
        int num = 0;
        String studentId = request.getParameter("studentId");
        System.out.println("studentId1111111111:"+studentId);
        String pwd = request.getParameter("password");
        String password = SecurityUtil.getStrMD5(pwd);
        System.out.println(password);
        String stuName = request.getParameter("stuName");
        System.out.println(stuName);
        String sex = request.getParameter("sex");
        System.out.println(sex);
        String dormBuildId = request.getParameter("dormBuildId");
        System.out.println(dormBuildId);
        String dormName = request.getParameter("dormName");
        System.out.println(dormName);
        String dormId = request.getParameter("dormId");
        String tel = request.getParameter("tel");
        System.out.println(tel);
        Student student = new Student(stuName,pwd,Integer.parseInt(dormId),sex,tel,Integer.parseInt(dormBuildId));
        try {
            if (StringUtil.isNotEmpty(studentId)) {
                student.setStudentId(Integer.parseInt(studentId));
                num = studentService.updateStudent(student);
                System.out.println("num=" + num);
            } else {
                num = studentService.saveStudent(student);
            }

            if (num > 0) {
                request.getRequestDispatcher("student?action=list").forward(request, response);
            } else {
                List<Dormbuild> dormBuildList = dormBuildService.listDormList();
                request.setAttribute("dormBuildList", dormBuildList);
                request.setAttribute("student", student);
                request.setAttribute("error", "保存失败");
                request.setAttribute("mainPage", "admin/studentSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void studentDelete(HttpServletRequest request,HttpServletResponse response){
        String studentId=request.getParameter("studentId");
        try {
            studentService.deleStudent(Integer.parseInt(studentId));
            request.getRequestDispatcher("student?action=list").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
