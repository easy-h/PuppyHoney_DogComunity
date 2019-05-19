package com.ph.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;
import com.ph.board.image.model.vo.ImageBoard;
import com.ph.board.news.model.service.NewsBoardService;
import com.ph.board.news.model.vo.NewsBoard;
import com.ph.freeBoard.model.vo.FreeBoard;
import com.ph.infoBoard.model.service.InfoBoardService;
import com.ph.infoBoard.model.vo.InfoBoard;


@WebServlet("/mainPage")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MainPageServlet() {
  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sortHits="INFO_BOARD_HITS";
		String sortReply="REPLYNUM";
		int numPerPage=10;
		int cPage=1;
		
		
		List<NewsBoard> newsList=new NewsBoardService().selectNewsBoardList();
		List<InfoBoard> infoHitsList = new InfoBoardService().sortInfoBoardList(cPage,numPerPage, sortHits);
		List<InfoBoard> infoReplyList = new InfoBoardService().sortInfoBoardList(cPage,numPerPage, sortReply);
		List<InfoBoard> infoList = new InfoBoardService().selectInfoBoardList(cPage,numPerPage);
		List<ImageBoard> imageList = new ImageBoardService().selectImageBoardList();
		
		request.setAttribute("newsList", newsList);
		request.setAttribute("infoHitsList", infoHitsList);
		request.setAttribute("infoReplyList", infoReplyList);
		request.setAttribute("imageList", imageList);
		request.setAttribute("infoList", infoList);
		request.getRequestDispatcher("/views/main/mainPage.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
