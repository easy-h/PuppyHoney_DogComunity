
package com.ph.board.news.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.*;
import com.ph.board.news.model.vo.NewsBoard;
import com.ph.board.news.model.vo.NewsReply;
import com.ph.infoBoard.model.vo.InfoBoard;
import com.ph.infoBoard.model.vo.InfoReply;

public class NewsBoardDAO {
   
   private Properties prop;
   
   public NewsBoardDAO() 
   {
      prop=new Properties();
      String file=NewsBoardDAO.class.getResource("/sql/newsboard/newsboard-sql.properties").getPath();
      try {
         prop.load(new FileReader(file));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public List<NewsBoard> selectNewsBoardList(Connection conn)
   {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectNewsBoardList");
      List<NewsBoard> newsList=new ArrayList<NewsBoard>();
      NewsBoard nb=null;
      try
      {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            nb=new NewsBoard();
            nb.setNewsBoardNum(rs.getInt("news_board_num"));
            nb.setNewsBoardTitle(rs.getString("news_board_title"));
            nb.setNewsBoardContent(rs.getString("news_board_content"));
            nb.setNewsBoardDate(rs.getDate("news_board_date"));
            nb.setNewsBoardGood(rs.getInt("news_board_good"));
            nb.setNewsBoardHits(rs.getInt("news_board_hits"));
            nb.setNewsBoardReplynum(rs.getInt("news_board_replynum"));
            newsList.add(nb);
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      
      return newsList;
   }
   
   public int insertNews(Connection conn, NewsBoard nb)
   {
      PreparedStatement pstmt=null;
      int result=0;
      String sql=prop.getProperty("insertNews");
      try
      {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, nb.getNewsBoardTitle());
         pstmt.setString(2,nb.getNewsBoardContent());
         result=pstmt.executeUpdate();
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      close(pstmt);
      return result;
   }
   
   public NewsBoard selectNewsBoard(Connection conn, int num)
   {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectNewsBoard");
      NewsBoard nb=null;
      try
      {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, num);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            nb=new NewsBoard();
            nb.setNewsBoardNum(rs.getInt("news_board_num"));
            nb.setNewsBoardTitle(rs.getString("news_board_title"));
            nb.setNewsBoardContent(rs.getString("news_board_content"));
            nb.setNewsBoardDate(rs.getDate("news_board_date"));
            nb.setNewsBoardGood(rs.getInt("news_board_good"));
            nb.setNewsBoardHits(rs.getInt("news_board_hits"));
            nb.setNewsBoardReplynum(rs.getInt("news_board_replynum"));
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      
      return nb;
   }

public List<NewsReply> selectNewsBoardReplyList(Connection conn, int num) {
   PreparedStatement pstmt=null;
   ResultSet rs=null;
   String sql=prop.getProperty("selectNewsBoardReplyList");
   ArrayList<NewsReply> list=new ArrayList<>();
   NewsReply ir=null;
   try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, num);
      rs=pstmt.executeQuery();
      while(rs.next())
      {
         ir=new NewsReply();
         ir.setReplyNum(rs.getInt("NEWS_REPLY_NUM"));
         ir.setBoardRef(rs.getInt("NEWS_BOARD_REF"));
         ir.setReplyId(rs.getString("USER_NICK"));
         ir.setReplyDate(rs.getString("NEWS_REPLY_DATE2"));
         ir.setReplyContent(rs.getString("NEWS_REPLY_CONTENT"));
         ir.setReplyGood(rs.getInt("NEWS_REPLY_GOOD"));
         ir.setReplyLevel(rs.getInt("NEWS_REPLY_LEVEL"));
         ir.setReplyRef(rs.getInt("NEWS_REPLY_REF"));
         list.add(ir);
      }
   }
   catch (Exception e) {
      e.printStackTrace();
   }
   
   close(rs);
   close(pstmt);
   
   return list;
}

public NewsBoard selectOne(Connection conn, int num) {
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   String sql=prop.getProperty("selectOne");
   NewsBoard nb =null;
   try {
      pstmt= conn.prepareStatement(sql);
      pstmt.setInt(1, num);
      rs=pstmt.executeQuery();
      if(rs.next()) {
         nb= new NewsBoard();
         nb.setNewsBoardNum(rs.getInt("news_board_num"));
            nb.setNewsBoardTitle(rs.getString("news_board_title"));
            nb.setNewsBoardContent(rs.getString("news_board_content"));
            nb.setNewsBoardDate(rs.getDate("news_board_date"));
            nb.setNewsBoardGood(rs.getInt("news_board_good"));
            nb.setNewsBoardHits(rs.getInt("news_board_hits"));
            nb.setNewsBoardReplynum(rs.getInt("news_board_replynum"));
      }
   }catch(SQLException e) {
      e.printStackTrace();
   }
   close(rs);
   close(pstmt);
   
   return nb;
}

public int insertBoardCount(Connection conn, int num) {
   PreparedStatement pstmt = null;
   int result = 0;
   String sql=prop.getProperty("insertBoardCount");
   try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, num);
      result= pstmt.executeUpdate();
   }catch(SQLException e) {
      e.printStackTrace();
   }
   close(pstmt);
   
   return result;
}

public int insertNewsReply(Connection conn, NewsReply ir) {
   PreparedStatement pstmt = null;
   int result = 0;
   String sql = prop.getProperty("insertNewsReply");
   
   try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, ir.getBoardRef());
      pstmt.setString(2, ir.getReplyId());
      pstmt.setString(3, ir.getReplyContent());
      pstmt.setInt(4, ir.getReplyLevel());
      pstmt.setString(5, ir.getReplyRef()==0?null:String.valueOf(ir.getReplyRef()));
      result = pstmt.executeUpdate();
      
   }catch(SQLException e) {
      e.printStackTrace();
   }
   close(pstmt);
   return result;
}

public int deleteNewsBoard(Connection conn, int boardNum) {
   PreparedStatement pstmt = null;
   int result = 0;
   String sql = prop.getProperty("deleteNewsBoard");
   
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

public int checkRecommend(Connection conn, int boardNum, String userId) {
   PreparedStatement pstmt = null;
   int result = 0;
   String sql = prop.getProperty("checkRecommend");
   ResultSet rs = null;
   try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, userId);
      pstmt.setInt(2, boardNum);;
      rs = pstmt.executeQuery();
      if(rs.next()) {
         result = rs.getInt("CNT");
      }
      
   }catch(SQLException e) {
      e.printStackTrace();
   }
   close(pstmt);
   return result;
}

public int insertNewsBoardGood(Connection conn, int boardNum, String userId) {
   PreparedStatement pstmt = null;
   int result2 = 0;
   String sql = prop.getProperty("insertNewsBoardGood");
   
   try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, userId);
      pstmt.setInt(2, boardNum);
      result2 = pstmt.executeUpdate();
      
   }catch(SQLException e) {
      e.printStackTrace();
   }
   close(pstmt);
   return result2;
}

public int countNewsBoardRecommend(Connection conn, int boardNum) {
   PreparedStatement pstmt = null;
   int result3 = 0;
   String sql = prop.getProperty("countNewsBoardRecommend");
   
   try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, boardNum);
      result3 = pstmt.executeUpdate();
      
   }catch(SQLException e) {
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
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, userId);
      pstmt.setInt(2, replyNum);;
      rs = pstmt.executeQuery();
      if(rs.next()) {
         result = rs.getInt("CNT");
      }
      
   }catch(SQLException e) {
      e.printStackTrace();
   }
   close(pstmt);
   return result;
}

public int insertNewsReplyGood(Connection conn, int replyNum, String userId) {
   PreparedStatement pstmt = null;
   int result2 = 0;
   String sql = prop.getProperty("insertNewsReplyGood");
   
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

public int countNewsReplyGonggam(Connection conn, int replyNum) {
   PreparedStatement pstmt = null;
   int result3 = 0;
   String sql = prop.getProperty("countNewsReplyGonggam");
   
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

public int deleteNewsReply(Connection conn, int replyNum) {
   PreparedStatement pstmt = null;
   int result = 0;
   String sql = prop.getProperty("deleteNewsReply");
   
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


   
   
}