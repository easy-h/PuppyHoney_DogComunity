package com.ph.board.news.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.news.model.service.NewsBoardService;
import com.ph.board.news.model.vo.NewsBoard;


@WebServlet("/board/newsBoardWriteEnd")
public class NewsBoardWriteEndServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

    public NewsBoardWriteEndServlet() {
    
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      System.out.println(content);
      NewsBoard nb=new NewsBoard();
      nb.setNewsBoardTitle(title);
      nb.setNewsBoardContent(content);
      
      int result=new NewsBoardService().insertNews(nb);
      
      String msg="";
      String loc="/board/newsBoardList";
      String view="/views/common/msg.jsp";
      if(result>0)
      {
         msg="게시물이 등록 되었습니다.";
         
      }
      else
      {
         msg="게시물 등록실패";
         
      }
      
      
      
      request.setAttribute("msg", msg);
      request.setAttribute("loc", loc);
      request.getRequestDispatcher(view).forward(request, response);
      
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doGet(request, response);
   }

}