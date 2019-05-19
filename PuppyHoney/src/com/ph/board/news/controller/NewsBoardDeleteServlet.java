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
 * Servlet implementation class NewsBoardDeleteServlet
 */
@WebServlet("/newsBoard/boardDelete")
public class NewsBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		int result = new NewsBoardService().deleteNewsBoard(boardNum);
		
		String msg="";
		String loc="/board/newsBoardList";
		if(result>0) {
			msg="게시물이 삭제 되었습니다.";
		}else {
			msg="게시물 삭제 실패.";
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
