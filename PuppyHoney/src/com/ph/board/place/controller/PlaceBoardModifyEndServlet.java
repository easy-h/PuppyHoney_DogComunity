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
 * Servlet implementation class PlaceBoardModifyEndServlet
 */
@WebServlet("/board/placeBoardModifyEnd")
public class PlaceBoardModifyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBoardModifyEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pBoardNum=Integer.parseInt(request.getParameter("pBoardNum"));
		String pTitle=request.getParameter("title");
		String pStoreName=request.getParameter("storeName");
		String pBusinessType=request.getParameter("businessType");
		String pArea=request.getParameter("location");
		String pDogSize=request.getParameter("size");
		String pPhone=request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		String pTime=request.getParameter("open")+" - "+request.getParameter("close");
		String pAddr=request.getParameter("address1")+","+request.getParameter("address2")+","+request.getParameter("address3");
		String pContent=request.getParameter("content");
		
		PlaceBoard plBoard=new PlaceBoard();
		plBoard.setPlBoardNum(pBoardNum);
		plBoard.setPlBoardTitle(pTitle);
		plBoard.setPlBoardStoreName(pStoreName);
		plBoard.setPlBoardBusinessType(pBusinessType);
		plBoard.setPlBoardArea(pArea);
		plBoard.setPlBoardDogSize(pDogSize);
		plBoard.setPlBoardPhone(pPhone);
		plBoard.setPlBoardTime(pTime);
		plBoard.setPlBoardAddr(pAddr);
		plBoard.setPlBoardContent(pContent);
		
		int result=new PlaceBoardService().updatePlaceBoard(plBoard);
		
		String msg="";
		String loc="/board/placeBoardList";
		if(result>0)
		{
			msg="게시물 수정 성공";
		}else
		{
			msg="게시물 수정 실패";
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
