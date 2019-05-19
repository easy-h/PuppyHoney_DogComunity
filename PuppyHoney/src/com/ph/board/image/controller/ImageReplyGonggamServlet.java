package com.ph.board.image.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;

/**
 * Servlet implementation class imageReplyGonggamServlet
 */
@WebServlet("/imageReply/replyGonggam")
public class ImageReplyGonggamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageReplyGonggamServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int boardNum = Integer.parseInt(request.getParameter("boardNum"));

		int replyNum = Integer.parseInt(request.getParameter("replyNum"));
		String userId = request.getParameter("userId");
		String searchType = request.getParameter("searchType");
		String inputText = request.getParameter("inputText");

		int result = new ImageBoardService().checkGonggam(replyNum, userId);

		if (result >= 1) {
			request.setAttribute("msg", "이미 공감한 게시물입니다.");
			request.setAttribute("loc", "/board/imageView?no=" + boardNum + "&searchType=" + searchType + "&inputText="
					+ inputText);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		} else {
			int result2 = new ImageBoardService().insertImageReplyGood(replyNum, userId);
			int result3 = new ImageBoardService().countImageReplyGonggam(replyNum);
			if (result3 >= 1) {
				request.setAttribute("msg", "추천이 완료되었습니다.");
				request.setAttribute("loc", "/board/imageView?no=" + boardNum + "&searchType=" + searchType
						+ "&inputText=" + inputText);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "추천에 실패했습니다..");
				request.setAttribute("loc", "/board/imageView?no=" + boardNum + "&searchType=" + searchType
						+ "&inputText=" + inputText);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
