package com.ph.board.lost.service;


import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.ph.board.lost.model.dao.LostBoardDAO;
import com.ph.board.lost.model.vo.LostBoard;
import com.ph.board.lost.model.vo.LostBoardComment;

public class LostBoardService {
	
	public int insertBoard(LostBoard lb) {
		Connection conn=getConnection();
		int result=new LostBoardDAO().insertBoard(conn, lb);
		if(result>0) close(conn);
		else rollback(conn);
		return result;
	}
	
	public List<LostBoard> selectAll() {
		Connection conn=getConnection();
		List<LostBoard> list=new LostBoardDAO().selectAll(conn);
		close(conn);
		return list;
	} 
	
	public LostBoard selectDetail(int num) {
		Connection conn=getConnection();
		LostBoard lb=new LostBoardDAO().selectDetail(conn, num);
		close(conn);
		return lb;
	}
	
	public List<LostBoard> filterLostBoardList(String[] pAddr) {
		Connection conn=getConnection();
		List<LostBoard> list=new LostBoardDAO().filterLostBoardList(conn, pAddr);
		close(conn);
		return list;
	}
	
	public int updateLostBoardView(LostBoard lb) {
		Connection conn=getConnection();
		int result=new LostBoardDAO().updateLostBoardView(conn, lb);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public int deleteLostBoardView(int num) {
		Connection conn=getConnection();
		int result=new LostBoardDAO().deleteLostBoardView(conn, num);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public int insertLostBoardComment(LostBoardComment lbc) {
		Connection conn=getConnection();
		int result=new LostBoardDAO().insertLostBoardComment(conn, lbc);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public List<LostBoardComment> selectLostBoardReplyList(int num) {
		Connection conn=getConnection();
		List<LostBoardComment> list=new LostBoardDAO().selectLostBoardReplyList(conn, num);
		close(conn);
		return list;
	}

	public int lostReplyDelete(int replyNum) {
		Connection conn=getConnection();
		int result=new LostBoardDAO().lostReplyDelete(conn, replyNum);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	

}
