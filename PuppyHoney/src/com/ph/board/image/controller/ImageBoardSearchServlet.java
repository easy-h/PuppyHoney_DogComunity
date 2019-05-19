package com.ph.board.image.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;
import com.ph.board.image.model.vo.ImageBoard;

/**
 * Servlet implementation class imageBoardSearchServlet
 */
@WebServlet("/imageBoard/search")
public class ImageBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//보냇으면 "null"로 받음.
		//안보냈으면 null로 받음.
		String searchType = request.getParameter("searchType");
		String inputText = request.getParameter("inputText");
		System.out.println(searchType);
		System.out.println(inputText);
				
				List<ImageBoard> list = new ImageBoardService().searchImageBoardList(searchType,inputText);
				
				request.setAttribute("list", list);
				request.setAttribute("searchType", searchType);
				request.setAttribute("inputText", inputText);
				request.getRequestDispatcher("/views/board/board_image/imageBoardList.jsp").forward(request, response);
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
