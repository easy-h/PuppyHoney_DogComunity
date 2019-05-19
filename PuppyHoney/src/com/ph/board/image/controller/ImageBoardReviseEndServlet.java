package com.ph.board.image.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;

/**
 * Servlet implementation class imageBoardReviseEndServlet
 */
@WebServlet("/imageBoard/ReviseEnd")
public class ImageBoardReviseEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageBoardReviseEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String searchType = request.getParameter("searchType");
		String inputText = request.getParameter("inputText");
		
		int result = new ImageBoardService().reviseImageBoard(boardNum,title,content);
		
		String msg="";
		String loc="/board/imageView?no="+boardNum+"&searchType="+searchType+"&inputText="+inputText;
		if(result>0) {
			msg="게시물이 수정되었습니다.";
		}else {
			msg="게시물 수정에 실패했습니다.";
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
