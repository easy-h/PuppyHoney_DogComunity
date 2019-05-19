package com.ph.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.user.model.service.UserService;
import com.ph.user.model.vo.User;


@WebServlet("/user/emailValidate")
public class UserEmailValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
   
    public UserEmailValidateServlet() {
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String code=request.getParameter("code");
		User user=new UserService().selectUser(userId);
		
		
		String msg="";
		String loc="/";
		String view="/views/common/msg.jsp";
		if(user.getUserEmailHash().equals(code))
		{
			int result=new UserService().updateEmailChecked(userId);
			if(result>0)
			{
				msg="E-Mail 인증이 완료되었습니다.";
			}
			else
			{
				msg="E-Mail 인증실패";
				return;
			}
			
		}
		else
		{
			msg="E-Mail 인증실패";
			return;
		}
		
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
