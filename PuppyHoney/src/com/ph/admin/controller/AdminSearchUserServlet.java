
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
import com.ph.user.model.vo.User;

/**
 * Servlet implementation class AdminSearchUserServlet
 */
@WebServlet("/admin/searchUser")
public class AdminSearchUserServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String searchType = request.getParameter("searchType");
      String inputText = request.getParameter("inputText");
      
      int numPerPage=10;
      int cPage;
      try {
         cPage=Integer.parseInt(request.getParameter("cPage"));
      }catch(NumberFormatException e) {
         cPage=1;
      }
      
      List<User> list = new AdminService().searchMemberList(cPage,numPerPage,searchType,inputText);
      //pageBar만들기
      //전체 자료 수
      int totalCount=new AdminService().countMemberList(searchType,inputText);
      if(searchType.equals("USER_ID")) {
         if(inputText.contains("a")||inputText.contains("d")||inputText.contains("m")||inputText.contains("i")||inputText.contains("n")) {
            totalCount=totalCount-1;   
         }
      }
      if(searchType.equals("USER_NICK")) {
         if(inputText.contains("관")||inputText.contains("리")||inputText.contains("자")) {
            totalCount=totalCount-1;
         }
      }
      
      if(searchType.equals("USER_EMAIL")) {
         if(inputText.contains("s")||inputText.contains("e")||inputText.contains("m")||inputText.contains("i")||inputText.contains("p")||inputText.contains("u")||inputText.contains("y")||inputText.contains("h")||inputText.contains("o")||inputText.contains("n")||inputText.contains("e")||inputText.contains("y")||inputText.contains("@")||inputText.contains("g")||inputText.contains("m")||inputText.contains("a")||inputText.contains("i")||inputText.contains("l")||inputText.contains(".")||inputText.contains("c")) {
            totalCount=totalCount-1;
         }
      }
      
      
      
      //전체 페이지 수
      int totalPage=(int)Math.ceil((double)totalCount/numPerPage);
      int barSize=5;
      String pageBar="";
      int pageNo=((cPage-1)/barSize)*barSize+1;
      int pageEnd=pageNo+barSize-1;
      
      //pagebar만들기!!
      if(pageNo==1) {
         pageBar+="<li class='page-item disabled'><a class='page-link'>Previous</a></li>";
      }else {
         pageBar+="<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/searchUser?cPage="+(pageNo-1)+"&searchType="+searchType+"&inputText="+inputText+"'>Previous</a></li>";
      }
      
      //페이지 번호 구성
      while(!(pageNo>pageEnd||pageNo>totalPage)) {
         if(cPage==pageNo) {
            pageBar+="<li class='page-item disabled'><a class='page-link'>"+pageNo+"</a></li>";
         }else {
            pageBar+="<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/searchUser?cPage="+pageNo+"&searchType="+searchType+"&inputText="+inputText+"'>"+pageNo+"</a></li>";
         }
         pageNo++;
      }
      
      //다음만들기
      if(pageNo>totalPage) {
         pageBar+="<li class='page-item disabled'><a class='page-link'>Next</a></li>";
      }else {
         pageBar+="<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/searchUser?cPage="+(pageNo)+"&searchType="+searchType+"&inputText="+inputText+"'>Next</a></li>";
      }
      //페이지바 구성 끝!
      
      request.setAttribute("list", list);
      request.setAttribute("cPage", cPage);
      request.setAttribute("pageBar", pageBar);
      request.setAttribute("searchType", searchType);
      request.setAttribute("inputText", inputText);
      request.setAttribute("searchCount", totalCount);
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