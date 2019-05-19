package com.ph.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ph.user.model.service.UserService;

/**
 * Servlet implementation class DeleteMemberEndServlet
 */
@WebServlet("/mypage/deleteMemberEnd")
public class DeleteMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("memberId");
		
		
		int result=new UserService().deleteMember(userId);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(result>0) {
			msg="회원탈퇴가 완료되었습니다.";
			loc="/";
			HttpSession session=request.getSession();
			if(session!=null)
			{
				session.invalidate();
			}
		}
		else {
			msg="회원탈퇴 실패";
			loc="/mypage/deleteMemberPage?userId="+userId;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
