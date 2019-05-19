package com.ph.board.image.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class imageBoardReviseServlet
 */
@WebServlet("/imageBoard/boardRevise")
public class ImageBoardReviseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageBoardReviseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String searchType = request.getParameter("searchType");
		String inputText = request.getParameter("inputText");
		
		
		
		
		
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("boardTitle", boardTitle);
		request.setAttribute("boardContent", boardContent);
		request.setAttribute("searchType", searchType);
		request.setAttribute("inputText", inputText);
		
		request.getRequestDispatcher("/views/board/board_image/imageBoardRevise.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
