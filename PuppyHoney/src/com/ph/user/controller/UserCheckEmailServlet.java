package com.ph.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.user.model.service.UserService;


@WebServlet("/user/checkEmail")
public class UserCheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserCheckEmailServlet() {
        super();
  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail=request.getParameter("userEmail");
		int result=new UserService().checkUserEmail(userEmail);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(result);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
