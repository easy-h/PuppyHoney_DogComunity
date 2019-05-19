package com.ph.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminAllMessageSendServlet
 */
@WebServlet("/admin/AllMessageSend")
public class AdminAllMessageSendServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAllMessageSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      String admin = request.getParameter("admin");
      
      
      int result = new AdminService().allUserCount();
      int sendCount = 0;
      int allCount = 0;
      
      for(int i=1; i<=result; i++) {
         String userId = new AdminService().countUserId(i);
         if(userId.equals("admin")) {
            continue;
         }
         sendCount = new AdminService().allMessageSend(userId,title,content,admin);
         allCount+=sendCount;
      }
      
      String msg="";
      String loc="/views/admin/AllMessage.jsp";
      String script="self.close()";
      if(allCount>0) {
         msg="총 "+allCount+"명에게 메세지를 보냈습니다.";
         request.setAttribute("script", script);
      }else {
         msg="회원이 없거나 메세지 발송에 실패했습니다.";
      }
      request.setAttribute("msg", msg);
      request.setAttribute("loc", loc);
      request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
      
      
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}