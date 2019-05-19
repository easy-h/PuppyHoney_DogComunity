package com.ph.board.news.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.news.model.service.NewsBoardService;
import com.ph.board.news.model.vo.NewsBoard;


@WebServlet("/board/newsBoardList")
public class NewsBoardListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   
    public NewsBoardListServlet() {
      
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<NewsBoard> newsList=new NewsBoardService().selectNewsBoardList();
      
      request.setAttribute("newsList", newsList);
      request.getRequestDispatcher("/views/board/board_news/newsBoardList.jsp").forward(request, response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doGet(request, response);
   }

}