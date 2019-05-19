package com.ph.board.place.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.place.model.service.PlaceBoardService;
import com.ph.board.place.model.vo.PlaceBoard;

/**
 * Servlet implementation class PlaceBoardModifyServlet
 */
@WebServlet("/board/placeBoardModify")
public class PlaceBoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int placeBoardNum=Integer.parseInt(request.getParameter("placeBoardNum"));
		String placeBoardTitle=request.getParameter("placeBoardTitle");
		String placeBoardContent=request.getParameter("placeBoardContent");
		String placeBoardArea=request.getParameter("placeBoardArea");
		String placeBoardDogSize=request.getParameter("placeBoardDogSize");
		String placeBoardBusinessType=request.getParameter("placeBoardBusinessType");
		String placeBoardStoreName=request.getParameter("placeBoardStoreName");
		String placeTime=request.getParameter("placeBoardTime");
		String placeBoardPhone = request.getParameter("placeBoardPhone");
		String placeBoardAddr=request.getParameter("placeBoardAddr");
		
		
		PlaceBoard plBoard=new PlaceBoard();
		
		
		plBoard.setPlBoardTitle(placeBoardTitle);
		plBoard.setPlBoardStoreName(placeBoardStoreName);
		plBoard.setPlBoardBusinessType(placeBoardBusinessType);
		plBoard.setPlBoardDogSize(placeBoardDogSize);
		plBoard.setPlBoardArea(placeBoardArea);
		plBoard.setPlBoardPhone(placeBoardPhone);
		plBoard.setPlBoardTime(placeTime);
		plBoard.setPlBoardAddr(placeBoardAddr);
		plBoard.setPlBoardContent(placeBoardContent);
		plBoard.setPlBoardNum(placeBoardNum);
		
//		int result=new PlaceBoardService().updatePlaceBoard(plBoard);	
		request.setAttribute("plBoard", plBoard);
		request.getRequestDispatcher("/views/board/board_place/placeBoardModify.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
