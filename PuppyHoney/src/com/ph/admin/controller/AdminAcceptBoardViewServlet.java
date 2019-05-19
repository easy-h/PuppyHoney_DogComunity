package com.ph.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.place.model.service.PlaceBoardService;
import com.ph.board.place.model.vo.PlaceBoard;

/**
 * Servlet implementation class AdminAcceptBoardViewServlet
 */
@WebServlet("/admin/acceptBoardView")
public class AdminAcceptBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAcceptBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int plBoardNum=Integer.parseInt(request.getParameter("plBoardNum"));
		
		PlaceBoard plBoard = new PlaceBoardService().selectOne(plBoardNum);
		
		String view="";
		String msg="";
		String loc="";
		if(plBoard!=null)
		{
			request.setAttribute("plBoard", plBoard);
			view="/views/admin/adminAcceptView.jsp";
		}else
		{
			request.setAttribute("msg", "조회한 게시물이 없습니다");
			request.setAttribute("loc", "/admin/AcceptBoardtList");
			view="/views/common/msg.jsp";
		}
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
