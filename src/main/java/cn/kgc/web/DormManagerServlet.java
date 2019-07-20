package cn.kgc.web;

import cn.kgc.entity.DormManager;
import cn.kgc.entity.Dormbuild;
import cn.kgc.service.DormBuildService;
import cn.kgc.service.DormManagerService;
import cn.kgc.service.impl.DormBuildServiceImpl;
import cn.kgc.service.impl.DormManagerServiceImpl;
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

@WebServlet(urlPatterns = "/dormManager")
public class DormManagerServlet extends HttpServlet {
    DormManagerService dormManagerService = new DormManagerServiceImpl();
    DormBuildService dormBuildService = new DormBuildServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println("action:" + action);
        HttpSession session = request.getSession();
        DormManager dormManager = new DormManager();
        String page = request.getParameter("page");
        System.out.println("page:" + page);
        String searchType = request.getParameter("searchType");
        System.out.println("searchType:" + searchType);
        String s_dormManagerText = request.getParameter("s_dormManagerText");
        System.out.println("s_dormManagerText:" + s_dormManagerText);
        if ("preSave".equals(action)) {
            dormManagerPreSave(request, response);
            return;
        } else if ("save".equals(action)) {
            dormManagerSave(request, response);
            return;
        } else if ("delete".equals(action)) {
            dormManagerDelete(request, response);
            return;
        } else {
            if ("list".equals(action)) {
                session.removeAttribute("s_dormManagerText");
                session.removeAttribute("searchType");
            } else if ("search".equals(action)) {
                if (StringUtil.isNotEmpty(s_dormManagerText)) {
                    if ("managerName".equals(searchType)) {
                        dormManager.setManagerName(s_dormManagerText);
                    } else if ("sex".equals(searchType)) {
                        dormManager.setSex(s_dormManagerText);
                    } else if ("dormBuildId".equals(searchType)) {
                        dormManager.setDormBuildId(Integer.parseInt(s_dormManagerText));
                    }
                    session.setAttribute("searchType", searchType);
                    session.setAttribute("s_dormManagerText", s_dormManagerText);
                } else {
                    session.removeAttribute("s_dormManagerText");
                    session.removeAttribute("searchType");
                }
            } else {
                if (StringUtil.isNotEmpty(s_dormManagerText)) {
                    if ("managerName".equals(searchType)) {
                        dormManager.setManagerName(s_dormManagerText);
                    } else if ("sex".equals(searchType)) {
                        dormManager.setSex(s_dormManagerText);
                    } else if ("dormBuildId".equals(searchType)) {
                        dormManager.setDormBuildId(Integer.parseInt(s_dormManagerText));
                    }
                    session.setAttribute("searchType", searchType);
                    session.setAttribute("s_dormManagerText", s_dormManagerText);
                }
                if (StringUtil.isEmpty(s_dormManagerText)) {
                    Object o1 = session.getAttribute("s_dormManagerText");
                    Object o2 = session.getAttribute("searchType");
                    if (o1 != null && o2 != null) {
                        if ("managerName".equals((String) o2)) {
                            dormManager.setManagerName((String) o1);
                        } else if ("sex".equals((String) o2)) {
                            dormManager.setSex((String) o1);
                        } else if ("dormBuildId".equals((String) o2)) {
                            dormManager.setDormBuildId(Integer.parseInt((String) o1));
                        }
                    }
                }
            }

            if (StringUtil.isEmpty(page)) {
                page = "1";
            }
            PageBean pageBean = new PageBean(Integer.parseInt(page),
                    Integer.parseInt(PropertiesUtil.getValue("pageSize")));
            try {
                System.out.println("条件1:" + dormManager.getDormBuildId());
                System.out.println("条件2:" + dormManager.getManagerName());
                System.out.println("条件3:" + dormManager.getSex());
                List<DormManager> dormManagerList = dormManagerService.listDormManager(pageBean, dormManager);
                int total = dormManagerService.countDormManager(dormManager);
                String pageCode = this.getPageInfo(total, Integer.parseInt(page),
                        Integer.parseInt(PropertiesUtil.getValue("pageSize")));
                System.out.println("pageCode:" + pageCode);

                request.setAttribute("pageCode", pageCode);
                request.setAttribute("dormManagerList", dormManagerList);
                request.setAttribute("mainPage", "admin/dormManager.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String getPageInfo(int totalNum, int currentPage, int pageSize) {
        //总页数
        int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        if (totalPage > 0) {
            if (currentPage == 1) {
                pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            } else {
                pageCode.append("<li><a href='dormManager?page=1'>首页</a></li>");
                pageCode.append("<li><a href='dormManager?page=" + (currentPage - 1) + "'>上一页</a></li>");
            }
            if (currentPage == totalPage) {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
                pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
            } else {
                pageCode.append("<li><a href='dormManager?page=" + (currentPage + 1) + "'>下一页</a></li>");
                pageCode.append("<li><a href='dormManager?page=" + totalPage + "'>尾页</a></li>");
            }
        } else {
            pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
        }

        return pageCode.toString();
    }

    private void dormManagerPreSave(HttpServletRequest request, HttpServletResponse response) {
        DormManager dormManager = null;
        String dormManId = request.getParameter("dormManId");
        try {
            List<Dormbuild> dormBuildList = dormBuildService.listDormList();
            if (StringUtil.isNotEmpty(dormManId)) {
                dormManager = dormManagerService.getDormManagerById(Integer.parseInt(dormManId));
                System.out.println("查询出的结果为:" + dormManager.getDormBuildId());
                request.setAttribute("dormManager", dormManager);
                request.setAttribute("dormBuildList", dormBuildList);
                request.setAttribute("mainPage", "admin/dormManagerSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            } else {
                request.setAttribute("dormBuildList", dormBuildList);
                request.setAttribute("mainPage", "admin/dormManagerSave.jsp");
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

    private void dormManagerSave(HttpServletRequest request, HttpServletResponse response) {
        int num = 0;
        String managerName = request.getParameter("managerName");
        System.out.println(managerName);
        String pwd = request.getParameter("password");
        String password = SecurityUtil.getStrMD5(pwd);
        System.out.println(password);
        String sex = request.getParameter("sex");
        System.out.println(sex);
        String tel = request.getParameter("tel");
        System.out.println(tel);
        String dormBuildId = request.getParameter("dormBuildId");
        System.out.println(dormBuildId);
        String dormManId = request.getParameter("dormManId");
        System.out.println(dormManId);
        DormManager dormManager = new DormManager(managerName, password, Integer.parseInt(dormBuildId), sex, tel);
        try {
            if (StringUtil.isNotEmpty(dormManId)) {
                dormManager.setDormManId(Integer.parseInt(dormManId));
                num = dormManagerService.updateDormManager(dormManager);
            } else {
                num = dormManagerService.saveDormManager(dormManager);
            }
            if (num > 0) {
                request.getRequestDispatcher("dormManager?action=list").forward(request, response);
            } else {
                request.setAttribute("dormManager", dormManager);
                request.setAttribute("error", "保存失败");
                request.setAttribute("mainPage", "dormManager/dormManagerSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dormManagerDelete(HttpServletRequest request, HttpServletResponse response) {
        String dormManId = request.getParameter("dormManId");
        try {
            dormManagerService.deleteManagerById(Integer.parseInt(dormManId));
            request.getRequestDispatcher("dormManager?action=list").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
