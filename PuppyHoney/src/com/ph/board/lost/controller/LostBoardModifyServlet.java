package com.ph.board.lost.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.lost.model.vo.LostBoard;

/**
 * Servlet implementation class LostBoardModifyServlet
 */
@WebServlet("/board/lostBoardModify")
public class LostBoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostBoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lostBoardNum=Integer.parseInt(request.getParameter("lostBoardNum"));
		String lostBoardTitle=request.getParameter("lostBoardTitle");
		String lostBoardContent=request.getParameter("lostBoardContent");
		String lostBoardArea=request.getParameter("lostBoardArea");
		String lostBoardPhone=request.getParameter("lostBoardPhone");
		String lostBoardType=request.getParameter("lostBoardType");
		
		LostBoard lb=new LostBoard();
		
		lb.setLostBoardNum(lostBoardNum);
		lb.setLostBoardTitle(lostBoardTitle);
		lb.setLostBoardContent(lostBoardContent);
		lb.setLostBoardArea(lostBoardArea);
		lb.setLostBoardPhone(lostBoardPhone);
		lb.setLostBoardType(lostBoardType);
		
		request.setAttribute("lb", lb);
		request.getRequestDispatcher("/views/board/board_lost/lostBoardModify.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
