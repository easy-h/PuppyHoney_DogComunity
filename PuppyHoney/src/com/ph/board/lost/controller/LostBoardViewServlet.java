package com.ph.board.lost.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.lost.model.vo.LostBoard;
import com.ph.board.lost.model.vo.LostBoardComment;
import com.ph.board.lost.service.LostBoardService;
import com.ph.board.place.model.service.PlaceBoardService;

/**
 * Servlet implementation class LostBoardDetailpage
 */
@WebServlet("/board/lostBoardView")
public class LostBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		LostBoard lb=new LostBoardService().selectDetail(num);
		List<LostBoardComment> list=new LostBoardService().selectLostBoardReplyList(num);
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("lb", lb);
		request.getRequestDispatcher("/views/board/board_lost/lostBoardView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
