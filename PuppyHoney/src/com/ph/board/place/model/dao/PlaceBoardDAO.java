package com.ph.board.place.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ph.board.place.model.vo.PlaceBoard;
import com.ph.board.place.model.vo.PlaceBoardComment;

public class PlaceBoardDAO {

	private Properties prop;
	public PlaceBoardDAO() 
	{
		prop=new Properties();
		String file=PlaceBoardDAO.class.getResource("/sql/placeboard/placeboard-sql.properties").getPath();
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int insertPlaceBoard(Connection conn, PlaceBoard plBoard) 	//濡쒓렇�씤�떆 �꽭�뀡媛� �븯�굹 諛쏄린
	{
		PreparedStatement pstmt=null;
		int result=0;
		System.out.println("dao: "+plBoard);
		String sql=prop.getProperty("insertPlaceBoard");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,plBoard.getPlBoardTitle());
			pstmt.setString(2,plBoard.getPlBoardContent());
			pstmt.setString(3,plBoard.getPlBoardId());		//�꽭�뀡媛믪쑝濡� �븘�씠�뵒 �븯�굹 諛쏄린
			pstmt.setString(4,plBoard.getPlBoardArea());
			pstmt.setString(5,plBoard.getPlBoardDogSize());
			pstmt.setString(6,plBoard.getPlBoardBusinessType());
			pstmt.setString(7,plBoard.getPlBoardStoreName());
			pstmt.setString(8,plBoard.getPlBoardTime());
			pstmt.setString(9,plBoard.getPlBoardPhone());
			pstmt.setString(10,plBoard.getPlBoardAddr());
			
			result=pstmt.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		close(pstmt);
		return result;
	}
	public List<PlaceBoard> selectPlaceBoard(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectPlaceBoard");
		ArrayList<PlaceBoard> pList=new ArrayList();
		PlaceBoard plBoard=null;
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				plBoard=new PlaceBoard();
				plBoard.setPlBoardNum(rs.getInt("place_board_num"));
				plBoard.setPlBoardTitle(rs.getString("place_board_title"));
				plBoard.setPlBoardId(rs.getString("place_board_id"));
				plBoard.setPlBoardContent(rs.getString("place_board_content"));
				plBoard.setPlBoardDate(rs.getDate("place_board_date"));
				plBoard.setPlBoardHits(rs.getInt("place_board_hits"));
				plBoard.setPlBoardGood(rs.getInt("place_board_good"));
				plBoard.setPlBoardArea(rs.getString("place_board_area"));
				plBoard.setPlBoardDogSize(rs.getString("place_board_dogsize"));
				plBoard.setPlBoardBusinessType(rs.getString("place_board_business_type"));
				plBoard.setPlBoardStoreName(rs.getString("place_board_name"));
				plBoard.setPlBoardTime(rs.getString("place_board_time"));
				plBoard.setPlBoardPhone(rs.getString("place_board_phone"));
				plBoard.setPlBoardAddr(rs.getString("listaddr"));
				plBoard.setPlBoardAcept_yn(rs.getString("place_board_accept_yn"));
				pList.add(plBoard);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		for(PlaceBoard pb:pList)
		{
			System.out.println("dao: "+pb);
		}
		close(rs);
		close(pstmt);
		return pList;
	}
	public PlaceBoard selectOne(Connection conn, int plBoardNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		PlaceBoard plBoard=null;
		String sql=prop.getProperty("plBoardSelectOne");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, plBoardNum);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				plBoard=new PlaceBoard();
				plBoard.setPlBoardNum(rs.getInt("place_board_num"));
				plBoard.setPlBoardTitle(rs.getString("place_board_title"));
				plBoard.setPlBoardContent(rs.getString("place_board_content"));
				plBoard.setPlBoardId(rs.getString("user_nick"));
				plBoard.setPlBoardDate(rs.getDate("place_board_date"));
				plBoard.setPlBoardHits(rs.getInt("place_board_hits"));
				plBoard.setPlBoardGood(rs.getInt("place_board_good"));
				plBoard.setPlBoardArea(rs.getString("place_board_area"));
				plBoard.setPlBoardDogSize(rs.getString("place_board_dogsize"));
				plBoard.setPlBoardBusinessType(rs.getString("place_board_business_type"));
				plBoard.setPlBoardStoreName(rs.getString("place_board_name"));
				plBoard.setPlBoardTime(rs.getString("place_board_time"));
				plBoard.setPlBoardPhone(rs.getString("place_board_phone"));
				plBoard.setPlBoardAddr(rs.getString("place_board_address"));
				plBoard.setPlBoardAcept_yn(rs.getString("place_board_accept_yn"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return plBoard;
	}
	public int updatePlaceBoard(Connection conn, PlaceBoard plBoard) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updatePlaceBoard");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,plBoard.getPlBoardTitle());
			pstmt.setString(2,plBoard.getPlBoardContent());
			pstmt.setString(3,plBoard.getPlBoardArea());
			pstmt.setString(4,plBoard.getPlBoardDogSize());
			pstmt.setString(5,plBoard.getPlBoardBusinessType());
			pstmt.setString(6,plBoard.getPlBoardStoreName());
			pstmt.setString(7,plBoard.getPlBoardTime());
			pstmt.setString(8,plBoard.getPlBoardPhone());
			pstmt.setString(9,plBoard.getPlBoardAddr());
			pstmt.setInt(10,plBoard.getPlBoardNum());
			
			result=pstmt.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	public int insertPlaceReply(Connection conn, PlaceBoardComment placeReply) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertPlaceReply");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, placeReply.getPlBoardRef());
			pstmt.setString(2, placeReply.getPlBoardReplyId());
			pstmt.setString(3, placeReply.getPlBoardReplyContent());
			pstmt.setInt(4, placeReply.getPlBoardReplyLevel());
			pstmt.setString(5, placeReply.getPlBoardReplyRef()==0?null:String.valueOf(placeReply.getPlBoardReplyRef()));
			result=pstmt.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		close(pstmt);
		return result;
	}
	
	
	public List<PlaceBoardComment> selectPlaceBoardReplyList(Connection conn, int plBoardNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		PlaceBoardComment pr=null;
		String sql=prop.getProperty("selectPlaceBoardReplyList");
		ArrayList<PlaceBoardComment> pReplyList=new ArrayList();
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, plBoardNum);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				pr=new PlaceBoardComment();
				pr.setPlBoardReplyNo(rs.getInt("place_reply_num"));
				pr.setPlBoardReplyContent(rs.getString("place_reply_content"));
				pr.setPlBoardReplyId(rs.getString("user_nick"));
				pr.setPlBoardDate(rs.getString("place_REPLY_DATE2"));
				pr.setPlBoardReplyGood(rs.getInt("place_reply_good"));
				pr.setPlBoardReplyLevel(rs.getInt("place_reply_level"));
				pr.setPlBoardRef(rs.getInt("place_reply_num"));
				pr.setPlBoardReplyRef(rs.getInt("place_reply_ref"));
				pReplyList.add(pr);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return pReplyList;
	}
	public int deletePlaceBoard(Connection conn, int pboardNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deletePlaceBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pboardNum);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	public List<PlaceBoard> FilterPlaceBoardList(Connection conn, String[] pAddr, String[] pType) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<PlaceBoard> pFilterList=null;
		PlaceBoard plBoard=null;
		
		if(pAddr!=null&&pType!=null) {
			
		String sql="select a.*,substr(place_board_address,7)as listAddr from place_board a where place_board_area in (";
		for(int i=0; i<pAddr.length;i++)
		{
			if(i==pAddr.length-1)
			{
				sql+="'"+pAddr[i]+"'"+") ";
				break;
			}
			sql+="'"+pAddr[i]+"'"+",";
		}
		sql+="and place_board_business_type in (";
		for(int i=0; i<pType.length; i++)
		{
			if(i==pType.length-1)
			{
				sql+="'"+pType[i]+"'"+")";
				break;
			}
			sql+="'"+pType[i]+"'"+",";
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
					plBoard=new PlaceBoard();
					plBoard.setPlBoardNum(rs.getInt("place_board_num"));
					plBoard.setPlBoardTitle(rs.getString("place_board_title"));
					plBoard.setPlBoardId(rs.getString("place_board_id"));
					plBoard.setPlBoardContent(rs.getString("place_board_content"));
					plBoard.setPlBoardDate(rs.getDate("place_board_date"));
					plBoard.setPlBoardHits(rs.getInt("place_board_hits"));
					plBoard.setPlBoardGood(rs.getInt("place_board_good"));
					plBoard.setPlBoardArea(rs.getString("place_board_area"));
					plBoard.setPlBoardDogSize(rs.getString("place_board_dogsize"));
					plBoard.setPlBoardBusinessType(rs.getString("place_board_business_type"));
					plBoard.setPlBoardStoreName(rs.getString("place_board_name"));
					plBoard.setPlBoardTime(rs.getString("place_board_time"));
					plBoard.setPlBoardPhone(rs.getString("place_board_phone"));
					plBoard.setPlBoardAddr(rs.getString("listaddr"));
					plBoard.setPlBoardAcept_yn(rs.getString("place_board_accept_yn"));
					pFilterList.add(plBoard);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		close(rs);
		close(pstmt);
		}else if(pAddr!=null&&pType==null)
		{
			String sql="select a.*,substr(place_board_address,7)as listAddr from place_board a where place_board_area in (";
			for(int i=0; i<pAddr.length;i++)
			{
				if(i==pAddr.length-1)
				{
					sql+="'"+pAddr[i]+"'"+") ";
					break;
				}
				sql+="'"+pAddr[i]+"'"+",";
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
						plBoard=new PlaceBoard();
						plBoard.setPlBoardNum(rs.getInt("place_board_num"));
						plBoard.setPlBoardTitle(rs.getString("place_board_title"));
						plBoard.setPlBoardId(rs.getString("place_board_id"));
						plBoard.setPlBoardContent(rs.getString("place_board_content"));
						plBoard.setPlBoardDate(rs.getDate("place_board_date"));
						plBoard.setPlBoardHits(rs.getInt("place_board_hits"));
						plBoard.setPlBoardGood(rs.getInt("place_board_good"));
						plBoard.setPlBoardArea(rs.getString("place_board_area"));
						plBoard.setPlBoardDogSize(rs.getString("place_board_dogsize"));
						plBoard.setPlBoardBusinessType(rs.getString("place_board_business_type"));
						plBoard.setPlBoardStoreName(rs.getString("place_board_name"));
						plBoard.setPlBoardTime(rs.getString("place_board_time"));
						plBoard.setPlBoardPhone(rs.getString("place_board_phone"));
						plBoard.setPlBoardAddr(rs.getString("listaddr"));
						plBoard.setPlBoardAcept_yn(rs.getString("place_board_accept_yn"));
						pFilterList.add(plBoard);
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			close(rs);
			close(pstmt);
		}else
		{
			String sql="select a.*,substr(place_board_address,7)as listAddr from place_board a where place_board_business_type in (";
			for(int i=0; i<pType.length; i++)
			{
				if(i==pType.length-1)
				{
					sql+="'"+pType[i]+"'"+")";
					break;
				}
				sql+="'"+pType[i]+"'"+",";
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
						plBoard=new PlaceBoard();
						plBoard.setPlBoardNum(rs.getInt("place_board_num"));
						plBoard.setPlBoardTitle(rs.getString("place_board_title"));
						plBoard.setPlBoardId(rs.getString("place_board_id"));
						plBoard.setPlBoardContent(rs.getString("place_board_content"));
						plBoard.setPlBoardDate(rs.getDate("place_board_date"));
						plBoard.setPlBoardHits(rs.getInt("place_board_hits"));
						plBoard.setPlBoardGood(rs.getInt("place_board_good"));
						plBoard.setPlBoardArea(rs.getString("place_board_area"));
						plBoard.setPlBoardDogSize(rs.getString("place_board_dogsize"));
						plBoard.setPlBoardBusinessType(rs.getString("place_board_business_type"));
						plBoard.setPlBoardStoreName(rs.getString("place_board_name"));
						plBoard.setPlBoardTime(rs.getString("place_board_time"));
						plBoard.setPlBoardPhone(rs.getString("place_board_phone"));
						plBoard.setPlBoardAddr(rs.getString("listaddr"));
						plBoard.setPlBoardAcept_yn(rs.getString("place_board_accept_yn"));
						pFilterList.add(plBoard);
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
	public int deletePlaceReply(Connection conn, int pReplyNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deletePlaceBoardReplyList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pReplyNum);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}

}
