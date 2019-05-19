package com.ph.board.image.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.ph.board.image.model.dao.ImageBoardDAO;
import com.ph.board.image.model.vo.ImageBoard;
import com.ph.board.image.model.vo.ImageReply;
import com.ph.infoBoard.model.dao.InfoBoardDAO;

public class ImageBoardService {

	public ImageBoard notLoginSelectOne(int no) {

		Connection conn = getConnection();
		ImageBoard mb = new ImageBoardDAO().notLoginSelectOne(conn, no);

		close(conn);
		return mb;
	}

	public ImageBoard selectOne(int no, boolean hasRead) {
		Connection conn = getConnection();
		ImageBoard ib = new ImageBoardDAO().selectOne(conn, no);
		int result = 0; // 조회수1증가처리
		if (ib != null) {
			if (!hasRead) {
				result = new ImageBoardDAO().insertBoardCount(conn, no);
				if (result > 0)
					commit(conn);
				else
					rollback(conn);
			}
		}

		close(conn);
		return ib;
	}

	public List<ImageReply> selectImageBoardReplyList(int no) {
		Connection conn = getConnection();
		List<ImageReply> list = new ImageBoardDAO().selectImageBoardReplyList(conn, no);
		return list;
	}

	public int insertImageBoard(ImageBoard mb) {
		Connection conn = getConnection();
		int result = new ImageBoardDAO().insertImageBoard(mb, conn);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int insertImageReply(ImageReply mr) {

		Connection conn = getConnection();
		int result = new ImageBoardDAO().insertImageReply(conn, mr);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<ImageBoard> selectImageBoardList() {

		Connection conn = getConnection();
		List<ImageBoard> list = new ImageBoardDAO().selectImageBoardList(conn);
		close(conn);
		return list;
	}

	public List<ImageBoard> searchImageBoardList(String searchType, String inputText) {
		
		Connection conn = getConnection();
		List<ImageBoard> list = new ImageBoardDAO().searchImageBoardList(conn, searchType, inputText);
		close(conn);
		return list;
	}


	public int checkRecommend(int boardNum, String userId) {
		Connection conn = getConnection();
		int result = new ImageBoardDAO().checkRecommend(conn, boardNum, userId);
		close(conn);
		return result;
	}

	public int insertImageBoardGood(int boardNum, String userId) {

		Connection conn = getConnection();
		int result2 = new ImageBoardDAO().insertImageBoardGood(conn, boardNum, userId);
		if (result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result2;
	}

	public int countImageBoardRecommend(int boardNum) {

		Connection conn = getConnection();
		int result3 = new ImageBoardDAO().countImageBoardRecommend(conn, boardNum);
		if (result3 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result3;
	}

	public int checkGonggam(int replyNum, String userId) {

		Connection conn = getConnection();
		int result = new ImageBoardDAO().checkGonggam(conn, replyNum, userId);
		close(conn);
		return result;

	}
	
	public int insertImageReplyGood(int replyNum, String userId) {
		
		Connection conn = getConnection();
		int result2=new ImageBoardDAO().insertImageReplyGood(conn, replyNum, userId);
		if(result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result2;
		
	}
	
	public int countImageReplyGonggam(int replyNum) {
		
		Connection conn = getConnection();
		int result3=new ImageBoardDAO().countImageReplyGonggam(conn, replyNum);
		if(result3>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result3;
		
		
	}
	
	public int deleteImageReply(int replyNum) {
		
		Connection conn = getConnection();
		int result=new ImageBoardDAO().deleteImageReply(conn, replyNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public int deleteImageBoard(int boardNum) {
		
		Connection conn = getConnection();
		int result=new ImageBoardDAO().deleteImageBoard(conn, boardNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public int reviseImageBoard(int boardNum,String title,String content) {
		
		Connection conn = getConnection();
		int result=new ImageBoardDAO().reviseImageBoard(conn, boardNum, title, content);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
}
