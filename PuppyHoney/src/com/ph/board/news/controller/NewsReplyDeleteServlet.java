package com.ph.board.news.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.news.model.service.NewsBoardService;
import com.ph.infoBoard.model.service.InfoBoardService;

/**
 * Servlet implementation class NewsReplyDeleteServlet
 */
@WebServlet("/newsReply/newsReplyDelete")
public class NewsReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int replyNum = Integer.parseInt(request.getParameter("replyNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		int result = new NewsBoardService().deleteNewsReply(replyNum);
		
		String msg="";
		String loc="/board/newsBoardView?num="+boardNum;
		if(result>0) {
			msg="댓글이 삭제되었습니다.";
		}else {
			msg="댓글 삭제 실패.";
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
