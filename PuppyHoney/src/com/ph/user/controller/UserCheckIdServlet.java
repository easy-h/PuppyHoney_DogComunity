package com.ph.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.user.model.service.UserService;

/**
 * Servlet implementation class UserCheckIdServlet
 */
@WebServlet("/user/checkId")
public class UserCheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
   
    public UserCheckIdServlet() {
       
    	
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		int result=new UserService().checkUserId(userId);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(result);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
