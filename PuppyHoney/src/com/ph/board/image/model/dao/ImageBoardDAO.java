package com.ph.board.image.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ph.board.image.model.vo.ImageBoard;
import com.ph.board.image.model.vo.ImageReply;

public class ImageBoardDAO {

	private Properties prop;

	public ImageBoardDAO() {
		prop = new Properties();
		try {
			String file = ImageBoardDAO.class.getResource("/sql/imageBoard/imageBoard-sql.properties").getPath();
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageBoard notLoginSelectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		ImageBoard mb = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mb = new ImageBoard();
				mb.setBoardNum(rs.getInt("image_board_num"));
				mb.setBoardTitle(rs.getString("image_board_title"));
				mb.setBoardContent(rs.getString("image_board_content"));
				mb.setBoardId(rs.getString("image_board_id"));
				mb.setBoardDate(rs.getDate("image_board_date"));
				mb.setBoardHits(rs.getInt("image_board_hits"));
				mb.setBoardGood(rs.getInt("image_board_good"));
			

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);

		return mb;

	}

	public ImageBoard selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		ImageBoard mb = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mb = new ImageBoard();
				mb.setBoardNum(rs.getInt("image_board_num"));
				mb.setBoardTitle(rs.getString("image_board_title"));
				mb.setBoardContent(rs.getString("image_board_content"));
				mb.setBoardId(rs.getString("image_board_id"));
				mb.setBoardDate(rs.getDate("image_board_date2"));
				mb.setBoardHits(rs.getInt("image_board_hits"));
				mb.setBoardGood(rs.getInt("image_board_good"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);

		return mb;
	}

	public int insertBoardCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoardCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);

		return result;
	}

	public List<ImageReply> selectImageBoardReplyList(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectImageBoardReplyList");
		ArrayList<ImageReply> list = new ArrayList<>();
		ImageReply mr = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mr = new ImageReply();
				mr.setReplyNum(rs.getInt("IMAGE_REPLY_NUM"));
				mr.setBoardRef(rs.getInt("IMAGE_BOARD_REF"));
				mr.setReplyId(rs.getString("USER_NICK"));
				mr.setReplyDate(rs.getDate("IMAGE_REPLY_DATE2"));
				mr.setReplyContent(rs.getString("IMAGE_REPLY_CONTENT"));
				mr.setReplyGood(rs.getInt("IMAGE_REPLY_GOOD"));
				mr.setReplyLevel(rs.getInt("IMAGE_REPLY_LEVEL"));
				mr.setReplyRef(rs.getInt("IMAGE_REPLY_REF"));
				list.add(mr);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		close(rs);
		close(pstmt);

		return list;
	}

	public int insertImageBoard(ImageBoard mb, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertImageBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getBoardTitle());
			pstmt.setString(2, mb.getBoardContent());
			pstmt.setString(3, mb.getBoardId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);

		return result;
	}

	public int insertImageReply(Connection conn, ImageReply mr) {

		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertImageReply");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mr.getBoardRef());
			pstmt.setString(2, mr.getReplyId());
			pstmt.setString(3, mr.getReplyContent());
			pstmt.setInt(4, mr.getReplyLevel());
			pstmt.setString(5, mr.getReplyRef() == 0 ? null : String.valueOf(mr.getReplyRef()));
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;

	}

	public List<ImageBoard> selectImageBoardList(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectImageBoardList");
		List<ImageBoard> list = new ArrayList<ImageBoard>();
		ImageBoard mb = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mb = new ImageBoard();
				mb.setBoardNum(rs.getInt("IMAGE_BOARD_NUM"));
				mb.setBoardTitle(rs.getString("IMAGE_BOARD_TITLE"));
				mb.setBoardContent(rs.getString("IMAGE_BOARD_CONTENT"));
				mb.setBoardId(rs.getString("USER_NICK"));
				mb.setBoardDate(rs.getDate("IMAGE_BOARD_DATE2"));
				mb.setBoardHits(rs.getInt("IMAGE_BOARD_HITS"));
				mb.setBoardGood(rs.getInt("IMAGE_BOARD_GOOD"));
			
				mb.setBoardReplyNum(rs.getInt("REPLYNUM"));
				list.add(mb);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close(rs);
		close(pstmt);

		return list;

	}

	public List<ImageBoard> searchImageBoardList(Connection conn, String searchType, String inputText) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT I.*,P.USER_NICK,I.IMAGE_BOARD_DATE AS IMAGE_BOARD_DATE2,(select count(*) from image_reply b where b.IMAGE_BOARD_REF = i.image_board_num)as REPLYNUM  FROM IMAGE_BOARD I INNER JOIN PH_USER P ON(I.IMAGE_BOARD_ID = P.USER_ID) WHERE "+searchType+" LIKE ? ORDER BY IMAGE_BOARD_DATE DESC)A)";
		List<ImageBoard> list = new ArrayList<ImageBoard>();
		ImageBoard mb = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+inputText+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {

				mb = new ImageBoard();
				mb.setBoardNum(rs.getInt("IMAGE_BOARD_NUM"));
				mb.setBoardTitle(rs.getString("IMAGE_BOARD_TITLE"));
				mb.setBoardContent(rs.getString("IMAGE_BOARD_CONTENT"));
				mb.setBoardId(rs.getString("USER_NICK"));
				mb.setBoardDate(rs.getDate("IMAGE_BOARD_DATE2"));
				mb.setBoardHits(rs.getInt("IMAGE_BOARD_HITS"));
				mb.setBoardGood(rs.getInt("IMAGE_BOARD_GOOD"));
			
				mb.setBoardReplyNum(rs.getInt("REPLYNUM"));
				list.add(mb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close(rs);
		close(pstmt);
		
		return list;
	}


	public int checkRecommend(Connection conn, int boardNum, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("checkRecommend");
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, boardNum);
			;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}

	public int insertImageBoardGood(Connection conn, int boardNum, String userId) {
		PreparedStatement pstmt = null;
		int result2 = 0;
		String sql = prop.getProperty("insertImageBoardGood");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, boardNum);
			result2 = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result2;
	}

	public int countImageBoardRecommend(Connection conn, int boardNum) {

		PreparedStatement pstmt = null;
		int result3 = 0;
		String sql = prop.getProperty("countImageBoardRecommend");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			result3 = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result3;

	}

	public int checkGonggam(Connection conn, int replyNum, String userId) {

		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("checkGonggam");
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, replyNum);
			;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	public int insertImageReplyGood(Connection conn, int replyNum, String userId) {
		
		PreparedStatement pstmt = null;
		int result2 = 0;
		String sql = prop.getProperty("insertImageReplyGood");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, replyNum);
			result2 = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result2;
		
	}
	
	public int countImageReplyGonggam(Connection conn, int replyNum) {
		
		PreparedStatement pstmt = null;
		int result3 = 0;
		String sql = prop.getProperty("countImageReplyGonggam");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, replyNum);
			result3 = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result3;
		
	}
	
	public int deleteImageReply(Connection conn,int replyNum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteImageReply");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, replyNum);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	
		
	}
	
	public int deleteImageBoard(Connection conn, int boardNum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteImageBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
		
	}
	
	public int reviseImageBoard(Connection conn, int boardNum, String title, String content) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("reviseImageBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardNum);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
		
	}

}
