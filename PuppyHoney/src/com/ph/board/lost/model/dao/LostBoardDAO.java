package com.ph.board.lost.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ph.board.lost.model.vo.LostBoard;
import com.ph.board.lost.model.vo.LostBoardComment;

public class LostBoardDAO {
	private Properties prop=new Properties();
	
	public LostBoardDAO()
	{
		try 
		{
			prop.load(new FileReader(LostBoardDAO.class.getResource("/sql/lostBoard/lostboard_query.properties").getPath()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public int insertBoard(Connection conn, LostBoard lb) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertLostBoard");;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lb.getLostBoardTitle());
			pstmt.setString(2, lb.getLostBoardContent());
			pstmt.setString(3, lb.getLostBoardId());
			pstmt.setString(4, lb.getLostBoardArea());
			pstmt.setString(5, lb.getLostBoardPhone());
			pstmt.setString(6, lb.getLostBoardType());
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		
		return result;
		
	}
	
	public List<LostBoard> selectAll(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LostBoard lb=null;
		String sql=prop.getProperty("selectLostBoardAll");;
		ArrayList<LostBoard> list=new ArrayList<LostBoard>();
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				lb=new LostBoard();
				lb.setLostBoardNum(rs.getInt("lost_board_num"));
				lb.setLostBoardTitle(rs.getString("lost_board_title"));
				lb.setLostBoardContent(rs.getString("lost_board_content"));
				lb.setLostBoardId(rs.getString("user_nick"));
				lb.setLostBoardDate(rs.getDate("lost_board_date"));
				lb.setLostBoardHits(rs.getInt("lost_board_hits"));
				lb.setLostBoardGood(rs.getInt("lost_board_good"));
				lb.setLostBoardArea(rs.getString("lost_board_area"));
				lb.setLostBoardPhone(rs.getString("lost_board_phone"));
				lb.setLostBoardType(rs.getString("lost_board_type"));
				
				list.add(lb);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		
		return list;
	}
	
	public LostBoard selectDetail(Connection conn, int num) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LostBoard lb=null;
		String sql=prop.getProperty("selectLostBoardDetail");
		ArrayList<LostBoard> list=new ArrayList<LostBoard>();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				lb=new LostBoard();
				lb.setLostBoardNum(rs.getInt("lost_board_num"));
				lb.setLostBoardTitle(rs.getString("lost_board_title"));
				lb.setLostBoardContent(rs.getString("lost_board_content"));
				lb.setLostBoardId(rs.getString("lost_board_Id"));
				lb.setLostBoardDate(rs.getDate("lost_board_date"));
				lb.setLostBoardHits(rs.getInt("lost_board_hits"));
				lb.setLostBoardGood(rs.getInt("lost_board_good"));
				lb.setLostBoardArea(rs.getString("lost_board_area"));
				lb.setLostBoardPhone(rs.getString("lost_board_phone"));
				lb.setLostBoardType(rs.getString("lost_board_type"));
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		System.out.println(lb);
		
		return lb;
	}
	
	public List<LostBoard> filterLostBoardList(Connection conn, String[] pAddr) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<LostBoard> pFilterList=null;
		LostBoard lb=null;
		
		if(pAddr!=null) {
			
			String sql="select * from lost_board where lost_board_area in (";
			for(int i=0; i<pAddr.length;i++)
			{
				if(i==pAddr.length-1)
				{
					sql+="'"+pAddr[i]+"') ";
					break;
				}
				sql+="'"+pAddr[i]+"',";
			}
			System.out.println(sql);
			
			pFilterList=new ArrayList();
			try
			{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				{
					while(rs.next())
					{
						lb=new LostBoard();
						lb.setLostBoardNum(rs.getInt("lost_board_num"));
						lb.setLostBoardTitle(rs.getString("lost_board_title"));
						lb.setLostBoardContent(rs.getString("lost_board_content"));
						lb.setLostBoardId(rs.getString("lost_board_Id"));
						lb.setLostBoardDate(rs.getDate("lost_board_date"));
						lb.setLostBoardHits(rs.getInt("lost_board_hits"));
						lb.setLostBoardGood(rs.getInt("lost_board_good"));
						lb.setLostBoardArea(rs.getString("lost_board_area"));
						lb.setLostBoardPhone(rs.getString("lost_board_phone"));
						lb.setLostBoardType(rs.getString("lost_board_type"));
						
						pFilterList.add(lb);
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			close(rs);
			close(pstmt);
		}
		
		return pFilterList;
	}
	
	public int updateLostBoardView(Connection conn, LostBoard lb) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateLostBoardView");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lb.getLostBoardTitle());
			pstmt.setString(2, lb.getLostBoardArea());
			pstmt.setString(3, lb.getLostBoardPhone());
			pstmt.setString(4, lb.getLostBoardType());
			pstmt.setString(5, lb.getLostBoardContent());
			pstmt.setInt(6, lb.getLostBoardNum());
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		
		return result;
	}
	
	public int deleteLostBoardView(Connection conn, int num) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteLostBoardView");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		close(pstmt);
		
		return result;
	}
	
	public int insertLostBoardComment(Connection conn, LostBoardComment lbc) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertLostBoardComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, lbc.getLostBoardRef());
			pstmt.setString(2, lbc.getLostReplyId());
			pstmt.setString(3, lbc.getLostReplyContent());
			pstmt.setInt(4, lbc.getLostReplyLevel());
			pstmt.setString(5, lbc.getLostReplyRef()==0?null:String.valueOf(lbc.getLostReplyRef()));
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		
		return result;
	}
	
	public List<LostBoardComment> selectLostBoardReplyList(Connection conn, int num) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectLostBoardReplyList");
		LostBoardComment lbc=null;
		ArrayList<LostBoardComment> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				lbc=new LostBoardComment();
				lbc.setLostReplyNum(rs.getInt("lost_reply_num"));
				lbc.setLostBoardRef(rs.getInt("lost_board_ref"));
				lbc.setLostReplyId(rs.getString("lost_reply_id"));
				lbc.setLostReplyDate(rs.getDate("lost_reply_date"));
				lbc.setLostReplyContent(rs.getString("lost_reply_content"));
				lbc.setLostReplyGood(rs.getInt("lost_reply_good"));
				lbc.setLostReplyLevel(rs.getInt("lost_reply_level"));
				lbc.setLostReplyRef(rs.getInt("lost_reply_ref"));
				list.add(lbc);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		close(rs);
		close(pstmt);
		
		return list;
	}
	public int lostReplyDelete(Connection conn, int replyNum) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("lostReplyDelete");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, replyNum);
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		close(pstmt);
		
		return result;
	}

}
