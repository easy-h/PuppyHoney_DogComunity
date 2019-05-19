

package com.ph.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.admin.model.service.AdminService;
import com.ph.infoBoard.model.service.InfoBoardService;
import com.ph.infoBoard.model.vo.InfoBoard;
import com.ph.user.model.vo.User;

/**
 * Servlet implementation class AdminUserListServlet
 */
@WebServlet("/admin/userList")
public class AdminUserListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //페이징 처리
            int numPerPage=10;
            int cPage;
            try {
               cPage=Integer.parseInt(request.getParameter("cPage"));
            }catch(NumberFormatException e) {
               cPage=1;
            }
            
            List<User> list = new AdminService().allMemberdList(cPage,numPerPage);
            //pageBar만들기
            //전체 회원 수
            int userCount=new AdminService().allUserCount();
            userCount=userCount-1;
            //전체 페이지 수
            int totalPage=(int)Math.ceil((double)userCount/numPerPage);
            int barSize=5;
            String pageBar="";
            int pageNo=((cPage-1)/barSize)*barSize+1;
            int pageEnd=pageNo+barSize-1;
            
            //pagebar만들기!!
            if(pageNo==1) {
               pageBar+="<li class='page-item disabled'><a class='page-link'>Previous</a></li>";
            }else {
               pageBar+="<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/userList?cPage="+(pageNo-1)+"'>Previous</a></li>";
            }
            
            //페이지 번호 구성
            while(!(pageNo>pageEnd||pageNo>totalPage)) {
               if(cPage==pageNo) {
                  pageBar+="<li class='page-item disabled'><a class='page-link'>"+pageNo+"</a></li>";
               }else {
                  pageBar+="<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/userList?cPage="+pageNo+"'>"+pageNo+"</a></li>";
               }
               pageNo++;
            }
            
            //다음만들기
            if(pageNo>totalPage) {
               pageBar+="<li class='page-item disabled'><a class='page-link'>Next</a></li>";
            }else {
               pageBar+="<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/userList?cPage="+(pageNo)+"'>Next</a></li>";
            }
            //페이지바 구성 끝!
            
            request.setAttribute("list", list);
            request.setAttribute("cPage", cPage);
            request.setAttribute("pageBar", pageBar);
            request.setAttribute("allUserCount", userCount);
            request.getRequestDispatcher("/views/admin/adminMemberList.jsp").forward(request, response);
            
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}