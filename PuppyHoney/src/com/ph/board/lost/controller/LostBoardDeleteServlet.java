package com.ph.board.lost.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.lost.service.LostBoardService;

/**
 * Servlet implementation class LostBoardDeleteServlet
 */
@WebServlet("/board/lostBoardDelete")
public class LostBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int lostBoardNum=Integer.parseInt(request.getParameter("lostBoardNum"));
		
		int result=new LostBoardService().deleteLostBoardView(lostBoardNum);
		
		String msg="";
		String loc="/lostBoard";
		if(result>0)
		{
			msg="게시물이 삭제 되었습니다";
		}
		else
		{
			msg="게시물 삭제 실패";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
