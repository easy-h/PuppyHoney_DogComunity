package com.ph.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.user.model.service.UserService;


@WebServlet("/user/checkNick")
public class UserCheckNickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserCheckNickServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNick=request.getParameter("userNick");
		int result=new UserService().checkUserNick(userNick);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(result);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
