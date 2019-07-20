package cn.kgc.web;

import cn.kgc.entity.Dormbuild;
import cn.kgc.service.BuildService;
import cn.kgc.service.impl.BuildServiceImpl;
import cn.kgc.util.PageBean;
import cn.kgc.util.PropertiesUtil;
import cn.kgc.util.StringUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/dormBuild")
public class BuildServlet extends HttpServlet {
    BuildService buildService=new BuildServiceImpl();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Dormbuild dormbuild=new Dormbuild();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        System.out.println("action:" + action);
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        System.out.println("page:" + page);
        String s_dormBuildName = request.getParameter("s_dormBuildName");
        if("save".equals(action)){
            buildSave(request,response);
        }else if ("preSave".equals(action)) {
            buildPreSave(request,response);
            return;
        } else if ("delete".equals(action)) {
            buildDelete(request,response);
            return;
        }else {
            if ("list".equals(action)){
                session.removeAttribute("s_dormBuildName");
            }else if ("search".equals(action)) {
                if (StringUtil.isNotEmpty(s_dormBuildName)){
                    dormbuild.setDormBuildName(s_dormBuildName);
                    session.setAttribute("s_dormBuildName",s_dormBuildName);
                }else {
                    session.removeAttribute("s_dormBuildName");
                }
            }else{
                if (StringUtil.isNotEmpty(s_dormBuildName)) {
                    dormbuild.setDormBuildName(s_dormBuildName);
                    session.setAttribute("s_dormBuildName", s_dormBuildName);
                }
                if (StringUtil.isEmpty(s_dormBuildName)) {
                    Object o1 = session.getAttribute("s_dormBuildName");
                    if (o1 != null) {
                        dormbuild.setDormBuildName((String) o1);
                    }
                }
            }

        }
        if (StringUtil.isEmpty(page)){
            page="1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
        try {
            List<Dormbuild> dormbuildList = buildService.listDormbuild(pageBean, dormbuild);
            int total = buildService.countbuild(dormbuild);
            String pageCode = this.getPageInfo(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
            System.out.println("pageCode:" + pageCode);
            request.setAttribute("pageCode", pageCode);
            request.setAttribute("dormBuildList", dormbuildList);
            request.setAttribute("mainPage", "admin/dormBuild.jsp");
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
                pageCode.append("<li ><a href='dormBuild?page=1'>首页</a></li>");
                pageCode.append("<li ><a href='dormBuild?page="+(currentPage-1)+"'>上一页</a></li>");
            }if (currentPage==totalPage){
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
                pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
            }else {
                pageCode.append("<li ><a href='dormBuild?page=" + (currentPage + 1) + "'>下一页</a></li>");
                pageCode.append("<li> <a href='dormBuild?page=" + totalPage + "'>尾页</a></li>");
            }
        }else {
            pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
        }
        return pageCode.toString();
    }
    private void buildPreSave(HttpServletRequest request,HttpServletResponse response){
        String dormBuildId = request.getParameter("dormBuildId");
        Dormbuild dormbuild=null;
        try{
            if (StringUtil.isNotEmpty(dormBuildId)){
                dormbuild=buildService.getbuildById(Integer.parseInt(dormBuildId));
                request.setAttribute("dormBuild",dormbuild);
                request.setAttribute("mainPage","admin/dormBuildSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request,response);
            }else{
                request.setAttribute("mainPage","admin/dormBuildSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request,response);
            }
        }catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void buildSave(HttpServletRequest request,HttpServletResponse response){
        String dormBuildId = request.getParameter("dormBuildId");
        String dormBuildName = request.getParameter("dormBuildName");
        String dormBuildDetail = request.getParameter("dormBuildDetail");
        Dormbuild dormbuild=new Dormbuild(dormBuildName,dormBuildDetail);
        int num=0;
        try {
            if (StringUtil.isNotEmpty(dormBuildId)) {//修改的更新
                dormbuild.setDormBuildId(Integer.parseInt(dormBuildId));
                num = buildService.updatebuild(dormbuild);
                System.out.println("num=" + num);
            } else {//增加的保存
                num = buildService.savebuild(dormbuild);
            }

            if (num > 0) {
                //修改或者增加成功后回到列表页面再次从数据库查询数据显示
                request.getRequestDispatcher("dormBuild?action=list").forward(request, response);
            } else {
                //失败后还返回原来页面，如果是修改依旧显示修改前的信息
                request.setAttribute("dormBuild", dormbuild);
                request.setAttribute("error", "保存失败");
                request.setAttribute("mainPage", "admin/dormBuildSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void buildDelete(HttpServletRequest request,HttpServletResponse response){
        String buildId=request.getParameter("dormBuildId");
        try {
            buildService.delebuild(Integer.parseInt(buildId));
            request.getRequestDispatcher("dormBuild?action=list").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
