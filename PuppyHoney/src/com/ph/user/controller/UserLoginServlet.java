package com.ph.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    
    public UserLoginServlet() {
       
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if(request.getParameter("searchType")!=null) {
      request.setAttribute("no", request.getParameter("no"));
      request.setAttribute("cPage", request.getParameter("cPage"));
      request.setAttribute("searchType", request.getParameter("searchType"));
      request.setAttribute("inputText", request.getParameter("inputText"));
      request.setAttribute("sort", request.getParameter("sort"));
      request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
      }else {
         request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
      }
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
      doGet(request, response);
   }

}