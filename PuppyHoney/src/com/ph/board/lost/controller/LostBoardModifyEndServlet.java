package com.ph.board.lost.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.lost.model.vo.LostBoard;
import com.ph.board.lost.service.LostBoardService;

/**
 * Servlet implementation class LostBoardModifyEndServlet
 */
@WebServlet("/board/lostBoardModifyEnd")
public class LostBoardModifyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostBoardModifyEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lostBoardNum=Integer.parseInt(request.getParameter("lostBoardNum"));
		String lostBoardTitle=request.getParameter("title");
		String lostBoardArea=request.getParameter("address");
		String lostBoardPhone=request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		String lostBoardType=request.getParameter("type");
		String lostBoardContent=request.getParameter("content");
		
		LostBoard lb=new LostBoard();
		lb.setLostBoardNum(lostBoardNum);
		lb.setLostBoardTitle(lostBoardTitle);
		lb.setLostBoardArea(lostBoardArea);
		lb.setLostBoardPhone(lostBoardPhone);
		lb.setLostBoardType(lostBoardType);
		lb.setLostBoardContent(lostBoardContent);
		
		int result=new LostBoardService().updateLostBoardView(lb);
		
		String msg="";
		String loc="/lostBoard";
		if(result>0)
		{
			msg="게시물이 수정 되었습니다";
		}
		else
		{
			msg="게시물 수정 실패";
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
