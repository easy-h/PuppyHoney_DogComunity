package com.ph.infoBoard.model.service;

import java.sql.Connection;
import java.util.List;

import com.ph.infoBoard.model.dao.InfoBoardDAO;
import com.ph.infoBoard.model.vo.InfoBoard;
import com.ph.infoBoard.model.vo.InfoReply;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

public class InfoBoardService {

	public int insertInfoBoard(InfoBoard ib) {
		Connection conn = getConnection();
		int result = new InfoBoardDAO().insertInfoBoard(ib,conn);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public List<InfoBoard> selectInfoBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<InfoBoard> list = new InfoBoardDAO().selectInfoBoardList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectInfoBoardCount() {
		Connection conn = getConnection();
		int result = new InfoBoardDAO().selectInfoBoardCount(conn);
		close(conn);
		return result;
	}

	public InfoBoard selectOne(int no, boolean hasRead) {
		Connection conn = getConnection();
		InfoBoard ib = new InfoBoardDAO().selectOne(conn,no);
		int result=0; //조회수1증가처리
		if(ib!=null) {
			if(!hasRead) {
				result = new InfoBoardDAO().insertBoardCount(conn, no);
				if(result>0) commit(conn);
				else rollback(conn);
			}
		}
		
		close(conn);
		return ib;
	}

	public List<InfoReply> selectInfoBoardReplyList(int no) {
		Connection conn = getConnection();
		List<InfoReply> list = new InfoBoardDAO().selectInfoBoardReplyList(conn,no);
		return list;
	}

	public int insertInfoReply(InfoReply ir) {
		Connection conn = getConnection();
		int result=new InfoBoardDAO().insertInfoReply(conn, ir);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public InfoBoard notLoginSelectOne(int no) {
		Connection conn = getConnection();
		InfoBoard ib = new InfoBoardDAO().notLoginSelectOne(conn,no);
		
		close(conn);
		return ib;
	}

	public int deleteInfoBoard(int boardNum) {
		Connection conn = getConnection();
		int result=new InfoBoardDAO().deleteInfoBoard(conn, boardNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteInfoReply(int replyNum) {
		
		Connection conn = getConnection();
		int result=new InfoBoardDAO().deleteInfoReply(conn, replyNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int reviseInfoBoard(int boardNum, String title, String content) {
		Connection conn = getConnection();
		int result=new InfoBoardDAO().reviseInfoBoard(conn, boardNum, title, content);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int checkRecommend(int boardNum, String userId) {
		Connection conn = getConnection();
		int result=new InfoBoardDAO().checkRecommend(conn, boardNum, userId);
		close(conn);
		return result;
	}

	public int insertInfoBoardGood(int boardNum, String userId) {
		
		Connection conn = getConnection();
		int result2=new InfoBoardDAO().insertInfoBoardGood(conn, boardNum, userId);
		if(result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result2;
	}

	public int countInfoBoardRecommend(int boardNum) {
		Connection conn = getConnection();
		int result3=new InfoBoardDAO().countInfoBoardRecommend(conn, boardNum);
		if(result3>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result3;
	}

	public int checkGonggam(int replyNum, String userId) {
		Connection conn = getConnection();
		int result=new InfoBoardDAO().checkGonggam(conn, replyNum, userId);
		close(conn);
		return result;
	}

	public int insertInfoReplyGood(int replyNum, String userId) {
		Connection conn = getConnection();
		int result2=new InfoBoardDAO().insertInfoReplyGood(conn, replyNum, userId);
		if(result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result2;
	}

	public int countInfoReplyGonggam(int replyNum) {
		Connection conn = getConnection();
		int result3=new InfoBoardDAO().countInfoReplyGonggam(conn, replyNum);
		if(result3>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result3;
	}

	public List<InfoBoard> searchInfoBoardList(int cPage, int numPerPage, String searchType, String inputText) {
		Connection conn = getConnection();
		List<InfoBoard> list = new InfoBoardDAO().searchInfoBoardList(conn, cPage, numPerPage,searchType,inputText);
		close(conn);
		return list;
	}

	public int searchInfoBoardCount(String searchType, String inputText) {
		Connection conn = getConnection();
		int result = new InfoBoardDAO().searchInfoBoardCount(conn,searchType,inputText);
		close(conn);
		return result;
	}

	public List<InfoBoard> sortInfoBoardList(int cPage, int numPerPage, String sort) {
		Connection conn = getConnection();
		List<InfoBoard> list = new InfoBoardDAO().sortInfoBoardList(conn, cPage, numPerPage, sort);
		close(conn);
		return list;
	}

	public List<InfoBoard> searchSortInfoBoardList(int cPage, int numPerPage, String searchType, String inputText,
			String sort) {
		Connection conn = getConnection();
		List<InfoBoard> list = new InfoBoardDAO().searchSortInfoBoardList(conn, cPage, numPerPage,searchType,inputText, sort);
		close(conn);
		return list;
	}

	
	

}
