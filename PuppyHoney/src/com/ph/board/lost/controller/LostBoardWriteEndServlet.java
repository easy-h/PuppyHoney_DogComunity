package com.ph.board.lost.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.lost.model.vo.LostBoard;
import com.ph.board.lost.service.LostBoardService;

import common.ImgExtract;
/**
 * Servlet implementation class LostBoardWriteEndServlet
 */
@WebServlet("/board/lostBoardWriteEnd")
public class LostBoardWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostBoardWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		String type=request.getParameter("type");
		String content=request.getParameter("content");
		String userId=request.getParameter("userId");
		
		
		LostBoard lb=new LostBoard();
		lb.setLostBoardTitle(title);
		lb.setLostBoardArea(address);
		lb.setLostBoardPhone(phone);
		lb.setLostBoardType(type);
		lb.setLostBoardContent(content);
		lb.setLostBoardId(userId);
		
		int result=new LostBoardService().insertBoard(lb);
		
		String msg="";
		String loc="/lostBoard";
		String view="/views/common/msg.jsp";
		if(result>0) {
			msg="게시물이 등록 되었습니다";
			loc="/lostBoard";
		}
		else {
			msg="게시물 등록 실패";
			loc="/lostBoard";
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
