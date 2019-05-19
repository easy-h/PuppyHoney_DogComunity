package com.ph.board.place.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.ph.board.place.model.dao.PlaceBoardDAO;
import com.ph.board.place.model.vo.PlaceBoard;
import com.ph.board.place.model.vo.PlaceBoardComment;
import com.ph.infoBoard.model.dao.InfoBoardDAO;
public class PlaceBoardService {
	
	public int insertPlaceBoard(PlaceBoard plBoard)
	{
		Connection conn=getConnection();
		int result= new PlaceBoardDAO().insertPlaceBoard(conn,plBoard);
		System.out.println("service: "+plBoard);
		if(result>0)
		{
			commit(conn);
		}else
		{
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public List<PlaceBoard> selectPlaceBoardList() 
	{
		Connection conn=getConnection();
		List<PlaceBoard> pList=new PlaceBoardDAO().selectPlaceBoard(conn);
		close(conn);
		
		return pList;
	}

	public PlaceBoard selectOne(int plBoardNum) {
		Connection conn=getConnection();
		PlaceBoard plBoard=new PlaceBoardDAO().selectOne(conn,plBoardNum);
		close(conn);
		return plBoard;
	}

	public int updatePlaceBoard(PlaceBoard plBoard) {
		Connection conn=getConnection();
		int result=new PlaceBoardDAO().updatePlaceBoard(conn,plBoard);
		if(result>0)
		{
			commit(conn);
		}else
		{
			rollback(conn);
		}
		close(conn);
		return result;
		
	}

	public int insertPlaceReply(PlaceBoardComment placeReply) {
		Connection conn = getConnection();
		int result=new PlaceBoardDAO().insertPlaceReply(conn, placeReply);
		if(result>0)
		{
			commit(conn);
		}else
		{
			rollback(conn);
		}
		
		close(conn);
		return result;
		
	}

	public List<PlaceBoardComment> selectPlaceBoardReplyList(int plBoardNum) {
		Connection conn = getConnection();
		List<PlaceBoardComment> pReplyList=new PlaceBoardDAO().selectPlaceBoardReplyList(conn,plBoardNum);
		
		return pReplyList;
	}

	public int deletePlaceBoard(int pboardNum) {
		Connection conn = getConnection();
		int result=new PlaceBoardDAO().deletePlaceBoard(conn, pboardNum);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public List<PlaceBoard> FilterPlaceBoardList(String[] pAddr, String[] pType) {
		Connection conn=getConnection();
		List<PlaceBoard> pList=new PlaceBoardDAO().FilterPlaceBoardList(conn,pAddr,pType);
		close(conn);
		
		return pList;
	}

	public int deletePlaceReply(int pReplyNum) {
		Connection conn = getConnection();
		int result=new PlaceBoardDAO().deletePlaceReply(conn, pReplyNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
