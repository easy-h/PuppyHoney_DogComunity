package com.ph.infoBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.infoBoard.model.service.InfoBoardService;
import com.ph.user.model.vo.User;

/**
 * Servlet implementation class InfoReplyGonggamServlet
 */
@WebServlet("/infoReply/replyGonggam")
public class InfoReplyGonggamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoReplyGonggamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
			int replyNum = Integer.parseInt(request.getParameter("replyNum"));
			String userId = request.getParameter("userId");
			int cPage=Integer.parseInt(request.getParameter("cPage"));
			String searchType=request.getParameter("searchType");
			String inputText=request.getParameter("inputText");
			String sort=request.getParameter("sort");
			
			int result = new InfoBoardService().checkGonggam(replyNum,userId);
			
			if(result>=1) {
				request.setAttribute("no", boardNum);
				request.setAttribute("cPage", cPage);
				request.setAttribute("searchType", searchType);
				request.setAttribute("inputText", inputText);
				request.setAttribute("sort", sort);
				request.setAttribute("msg", "이미 공감한 게시물입니다.");
				request.setAttribute("loc","/freeBoard/boardView?no="+boardNum+"&cPage="+cPage+"&searchType="+searchType+"&inputText="+inputText+"&sort="+sort);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}else {
				int result2 = new InfoBoardService().insertInfoReplyGood(replyNum,userId);
				int result3 = new InfoBoardService().countInfoReplyGonggam(replyNum);
				if(result3>=1) {
					request.setAttribute("no", boardNum);
					request.setAttribute("cPage", cPage);
					request.setAttribute("searchType", searchType);
					request.setAttribute("inputText", inputText);
					request.setAttribute("sort", sort);
					request.setAttribute("msg", "공감이 완료되었습니다.");
					request.setAttribute("loc","/freeBoard/boardView?no="+boardNum+"&cPage="+cPage+"&searchType="+searchType+"&inputText="+inputText+"&sort="+sort);
					request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				}else {
					request.setAttribute("no", boardNum);
					request.setAttribute("cPage", cPage);
					request.setAttribute("searchType", searchType);
					request.setAttribute("inputText", inputText);
					request.setAttribute("sort", sort);
					request.setAttribute("msg", "공감에 실패했습니다..");
					request.setAttribute("loc","/freeBoard/boardView?no="+boardNum+"&cPage="+cPage+"&searchType="+searchType+"&inputText="+inputText+"&sort="+sort);
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
