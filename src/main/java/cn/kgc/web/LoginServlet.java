package cn.kgc.web;

import cn.kgc.dao.LoginDao;
import cn.kgc.dao.impl.LoginDaoImpl;
import cn.kgc.entity.Admin;
import cn.kgc.entity.DormManager;
import cn.kgc.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    LoginDao loginDao = new LoginDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userName");
        String pwd = request.getParameter("password");
        String type = request.getParameter("userType");
        String action = request.getParameter("action");
        Admin currentAdmin = null;
        DormManager currentDormManager = null;
        Student currentStudent = null;
        HttpSession session = request.getSession();
        if ("login".equals(action)) {
            if ("admin".equals(type)) {
                Admin admin = new Admin();
                admin.setUserName(name);
                admin.setPassword(pwd);
                try {
                    currentAdmin = (Admin) loginDao.login(admin);
                    if (currentAdmin == null) {
                        request.setAttribute("error", "用户名或密码错误！");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        return;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("currentUserType", "admin");
                session.setAttribute("currentUser", currentAdmin);
                request.setAttribute("mainPage", "admin/blank.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);

            } else if ("dormManager".equals(type)) {

                DormManager dormManager = new DormManager();
                dormManager.setManagerName(name);
                dormManager.setPassword(pwd);
                try {
                    currentDormManager = (DormManager) loginDao.login(dormManager);
                    if (currentDormManager == null) {
                        request.setAttribute("error", "用户名或密码错误！");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        return;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("currentUserType", "dormManager");
                session.setAttribute("currentUser", currentDormManager);
                request.setAttribute("mainPage", "dormManager/blank.jsp");
                request.getRequestDispatcher("mainManager.jsp").forward(request, response);

            } else {
                Student student = new Student();
                student.setStuName(name);
                student.setPassword(pwd);
                try {
                    currentStudent = (Student) loginDao.login(student);
                    if (currentStudent == null) {
                        request.setAttribute("error", "用户名或密码错误！");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        return;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("currentUserType", "student");
                session.setAttribute("currentUser", currentStudent);
                request.setAttribute("mainPage", "student/blank.jsp");
                request.getRequestDispatcher("mainStudent.jsp").forward(request, response);
            }
        } else {
            logOut(session, response);
        }
    }

    private void logOut(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("currentUser") != null) {
            session.removeAttribute("currentUser");
        }
        try {
            response.sendRedirect("/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
