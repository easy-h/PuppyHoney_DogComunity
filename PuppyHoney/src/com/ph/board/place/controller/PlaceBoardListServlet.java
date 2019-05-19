package com.ph.board.place.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.place.model.service.PlaceBoardService;
import com.ph.board.place.model.vo.PlaceBoard;

/**
 * Servlet implementation class PlaceBoardListServlet
 */
@WebServlet("/board/placeBoardList")
public class PlaceBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			List<PlaceBoard> pList = new PlaceBoardService().selectPlaceBoardList();
		
			request.setAttribute("pList", pList);
			request.getRequestDispatcher("/views/board/board_place/placeBoardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
