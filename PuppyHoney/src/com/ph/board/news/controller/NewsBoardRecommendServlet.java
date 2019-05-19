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
 * Servlet implementation class NewsBoardRecommendServlet
 */
@WebServlet("/newsBoard/boardRecommend")
public class NewsBoardRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsBoardRecommendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String userId = request.getParameter("userId");
		
		int result = new NewsBoardService().checkRecommend(boardNum,userId);
		
		if(result>=1) {
			request.setAttribute("msg", "이미 추천한 게시물입니다.");
			request.setAttribute("loc","/board/newsBoardView?num="+boardNum);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}else {
			int result2 = new NewsBoardService().insertNewsBoardGood(boardNum,userId);
			int result3 = new NewsBoardService().countNewsBoardRecommend(boardNum);
			if(result3>=1) {
				request.setAttribute("msg", "추천이 완료 되었습니다.");
				request.setAttribute("loc","/board/newsBoardView?num="+boardNum);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "추천실패.");
				request.setAttribute("loc","/board/newsBoardView?num="+boardNum);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
