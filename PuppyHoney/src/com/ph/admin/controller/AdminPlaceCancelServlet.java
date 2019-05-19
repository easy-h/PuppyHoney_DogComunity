package com.ph.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminPlaceCancelServlet
 */
@WebServlet("/admin/placeCancel")
public class AdminPlaceCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPlaceCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int placeBoardNum = Integer.parseInt(request.getParameter("placeBoardNum"));
		String placeBoardId = request.getParameter("placeBoardId");
		String getPlBoardTitle = request.getParameter("getPlBoardTitle");
		
		int result = new AdminService().boardCancel(placeBoardNum);
		if(result>0) {
			String userId = new AdminService().sameUserId(placeBoardId);
			int result2 = new AdminService().messageSendCancel(userId,getPlBoardTitle);
		}
		
		String msg="";
		String loc="/admin/acceptBoardView?plBoardNum="+placeBoardNum;
		String view="/views/common/msg.jsp";
		if(result>0) {
			msg="게시물승인이 취소되었습니다.";
		}else {
			msg="게시물승인취소에 실패했습니다.";
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
