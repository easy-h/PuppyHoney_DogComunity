package com.ph.board.place.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.place.model.service.PlaceBoardService;

/**
 * Servlet implementation class PlaceBoardReplyDeleteServlet
 */
@WebServlet("/Reply/placeReplyDelete")
public class PlaceBoardReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBoardReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pReplyNum = Integer.parseInt(request.getParameter("replyNum"));
		int pBoardNum = Integer.parseInt(request.getParameter("boardNum"));
		int result = new PlaceBoardService().deletePlaceReply(pReplyNum);
		
		String msg="";
		String loc="/board/placeBoardView?plBoardNum="+pBoardNum;
		if(result>0)
		{
			msg="댓글 삭제 완료";
		}
		else 
		{
			msg="댓글 삭제 실패";
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
