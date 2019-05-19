package com.ph.infoBoard.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ph.infoBoard.model.vo.InfoBoard;
import com.ph.infoBoard.model.vo.InfoReply;

import static common.JDBCTemplate.close;

public class InfoBoardDAO {
   private Properties prop;
   
   public InfoBoardDAO() {
      prop=new Properties();
      try {
          String file=InfoBoardDAO.class.getResource("/sql/infoBoard/infoBoard-sql.properties").getPath();
          prop.load(new FileReader(file));
       }
       catch(IOException e)
       {
          e.printStackTrace();
       }
   }
   public int insertInfoBoard(InfoBoard ib, Connection conn) {
      PreparedStatement pstmt=null;
      int result=0;
      String sql=prop.getProperty("insertInfoBoard");
      System.out.println(ib.getBoardTitle());
      System.out.println(ib.getBoardContent());
      System.out.println(ib.getBoardId());
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, ib.getBoardTitle());
         pstmt.setString(2, ib.getBoardContent());
         pstmt.setString(3, ib.getBoardId());
         result = pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      
      return result;
   }
   
   public List<InfoBoard> selectInfoBoardList(Connection conn, int cPage, int numPerPage) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectInfoBoardList");
      ArrayList<InfoBoard> list=new ArrayList();
      InfoBoard ib=null;
      
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, ((cPage-1)*numPerPage+1));
         pstmt.setInt(2, cPage*numPerPage);         
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            ib=new InfoBoard();
            ib.setBoardNumber(rs.getInt("INFO_BOARD_NUM"));
            ib.setBoardTitle(rs.getString("INFO_BOARD_TITLE"));
            ib.setBoardContent(rs.getString("INFO_BOARD_CONTENT"));
            ib.setBoardId(rs.getString("USER_NICK"));
            ib.setBoardDate(rs.getString("INFO_BOARD_DATE2"));
            ib.setBoardHit(rs.getInt("INFO_BOARD_HITS"));
            ib.setBoardgood(rs.getInt("INFO_BOARD_GOOD"));
            ib.setBoardReplyNum(rs.getInt("REPLYNUM"));
            list.add(ib);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return list;
      
   }
   public int selectInfoBoardCount(Connection conn) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectInfoBoardCount");
      int result=0;
      try
      {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         if(rs.next())
         {
            result=rs.getInt("cnt");
         }
         
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return result;
   }
   public InfoBoard selectOne(Connection conn, int no) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql=prop.getProperty("selectOne");
      InfoBoard ib =null;
      try {
         pstmt= conn.prepareStatement(sql);
         pstmt.setInt(1, no);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            ib= new InfoBoard();
            ib.setBoardNumber(rs.getInt("INFO_BOARD_NUM"));
            ib.setBoardTitle(rs.getString("INFO_BOARD_TITLE"));
            ib.setBoardContent(rs.getString("INFO_BOARD_CONTENT"));
            ib.setBoardId(rs.getString("USER_NICK"));
            ib.setBoardDate(rs.getString("INFO_BOARD_DATE2"));
            ib.setBoardHit(rs.getInt("INFO_BOARD_HITS"));
            ib.setBoardgood(rs.getInt("INFO_BOARD_GOOD"));
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(rs);
      close(pstmt);
      
      return ib;
   }
   public int insertBoardCount(Connection conn, int no) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql=prop.getProperty("insertBoardCount");
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, no);
         result= pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      
      return result;
   }
   public List<InfoReply> selectInfoBoardReplyList(Connection conn, int no) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectInfoBoardReplyList");
      ArrayList<InfoReply> list=new ArrayList<>();
      InfoReply ir=null;
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, no);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            ir=new InfoReply();
            ir.setReplyNum(rs.getInt("INFO_REPLY_NUM"));
            ir.setBoardRef(rs.getInt("INFO_BOARD_REF"));
            ir.setReplyId(rs.getString("USER_NICK"));
            ir.setReplyDate(rs.getString("INFO_REPLY_DATE2"));
            ir.setReplyContent(rs.getString("INFO_REPLY_CONTENT"));
            ir.setReplyGood(rs.getInt("INFO_REPLY_GOOD"));
            ir.setReplyLevel(rs.getInt("INFO_REPLY_LEVEL"));
            ir.setReplyRef(rs.getInt("INFO_REPLY_REF"));
            ir.setReName(rs.getString("USER_IMAGE_RENAME"));
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
   
   public int insertInfoReply(Connection conn, InfoReply ir) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = prop.getProperty("insertInfoReply");
      
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, ir.getBoardRef());
         pstmt.setString(2, ir.getReplyId());
         pstmt.setString(3, ir.getReplyContent());
         pstmt.setInt(4, ir.getReplyLevel());
         pstmt.setString(5, ir.getReplyRef()==0?null:String.valueOf(ir.getReplyRef()));
         pstmt.setString(6, ir.getReName());
         result = pstmt.executeUpdate();
         
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return result;
   }
   
   public InfoBoard notLoginSelectOne(Connection conn, int no) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql=prop.getProperty("selectOne");
      InfoBoard ib =null;
      try {
         pstmt= conn.prepareStatement(sql);
         pstmt.setInt(1, no);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            ib= new InfoBoard();
            ib.setBoardNumber(rs.getInt("INFO_BOARD_NUM"));
            ib.setBoardTitle(rs.getString("INFO_BOARD_TITLE"));
            ib.setBoardContent(rs.getString("INFO_BOARD_CONTENT"));
            ib.setBoardId(rs.getString("USER_NICK"));
            ib.setBoardDate(rs.getString("INFO_BOARD_DATE2"));
            ib.setBoardHit(rs.getInt("INFO_BOARD_HITS"));
            ib.setBoardgood(rs.getInt("INFO_BOARD_GOOD"));
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(rs);
      close(pstmt);
      
      return ib;
   }
   public int deleteInfoBoard(Connection conn, int boardNum) {
      
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = prop.getProperty("deleteInfoBoard");
      
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
   
   public int deleteInfoReply(Connection conn, int replyNum) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = prop.getProperty("deleteInfoReply");
      
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
   
   public int reviseInfoBoard(Connection conn, int boardNum, String title, String content) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = prop.getProperty("reviseInfoBoard");
      
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
   
   public int insertInfoBoardGood(Connection conn, int boardNum, String userId) {
      PreparedStatement pstmt = null;
      int result2 = 0;
      String sql = prop.getProperty("insertInfoBoardGood");
      
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
   
   public int countInfoBoardRecommend(Connection conn, int boardNum) {
      PreparedStatement pstmt = null;
      int result3 = 0;
      String sql = prop.getProperty("countInfoBoardRecommend");
      
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
   
   public int insertInfoReplyGood(Connection conn, int replyNum, String userId) {
      PreparedStatement pstmt = null;
      int result2 = 0;
      String sql = prop.getProperty("insertInfoReplyGood");
      
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
   
   public int countInfoReplyGonggam(Connection conn, int replyNum) {
      PreparedStatement pstmt = null;
      int result3 = 0;
      String sql = prop.getProperty("countInfoReplyGonggam");
      
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
   
   public List<InfoBoard> searchInfoBoardList(Connection conn, int cPage, int numPerPage, String searchType,
         String inputText) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      System.out.println("첫번쨰 : "+((cPage-1)*numPerPage+1));
      System.out.println("두번째 : "+cPage*numPerPage);
      String sql="SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT I.*,P.USER_NICK,TO_CHAR(I.INFO_BOARD_DATE,'yyyy-mm-dd hh24:mi:ss')AS INFO_BOARD_DATE2,(select count(*) from info_reply b where b.INFO_BOARD_REF = i.info_board_num)as REPLYNUM  FROM INFO_BOARD I INNER JOIN PH_USER P ON(I.INFO_BOARD_ID = P.USER_ID) WHERE "+searchType+" LIKE "+"'%"+inputText+"%' ORDER BY INFO_BOARD_DATE DESC)A)WHERE RNUM BETWEEN "+((cPage-1)*numPerPage+1)+" AND "+cPage*numPerPage;
      ArrayList<InfoBoard> list=new ArrayList();
      InfoBoard ib=null;
      
      try {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            
            ib=new InfoBoard();
            ib.setBoardNumber(rs.getInt("INFO_BOARD_NUM"));
            ib.setBoardTitle(rs.getString("INFO_BOARD_TITLE"));
            ib.setBoardContent(rs.getString("INFO_BOARD_CONTENT"));
            ib.setBoardId(rs.getString("USER_NICK"));
            ib.setBoardDate(rs.getString("INFO_BOARD_DATE2"));
            ib.setBoardHit(rs.getInt("INFO_BOARD_HITS"));
            ib.setBoardgood(rs.getInt("INFO_BOARD_GOOD"));
            ib.setBoardReplyNum(rs.getInt("REPLYNUM"));
            list.add(ib);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return list;
   }
   
   public int searchInfoBoardCount(Connection conn, String searchType, String inputText) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql="SELECT COUNT(*) AS CNT FROM INFO_BOARD I INNER JOIN PH_USER P ON(I.INFO_BOARD_ID = P.USER_ID) WHERE "+searchType+" LIKE "+"'%"+inputText+"%'";
      int result=0;
      try
      {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         if(rs.next())
         {
            result=rs.getInt("cnt");
         }
         
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return result;
   }
   public List<InfoBoard> sortInfoBoardList(Connection conn, int cPage, int numPerPage, String sort) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql="SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT I.*,P.USER_NICK,TO_CHAR(I.INFO_BOARD_DATE,'yyyy-mm-dd hh24:mi:ss')AS INFO_BOARD_DATE2,(select count(*) from info_reply b where b.INFO_BOARD_REF = i.info_board_num)as REPLYNUM  FROM INFO_BOARD I INNER JOIN PH_USER P ON(I.INFO_BOARD_ID = P.USER_ID)ORDER BY "+sort+" DESC)A)WHERE RNUM BETWEEN "+((cPage-1)*numPerPage+1)+" AND "+cPage*numPerPage;
      ArrayList<InfoBoard> list=new ArrayList();
      InfoBoard ib=null;
      
      try {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            ib=new InfoBoard();
            ib.setBoardNumber(rs.getInt("INFO_BOARD_NUM"));
            ib.setBoardTitle(rs.getString("INFO_BOARD_TITLE"));
            ib.setBoardContent(rs.getString("INFO_BOARD_CONTENT"));
            ib.setBoardId(rs.getString("USER_NICK"));
            ib.setBoardDate(rs.getString("INFO_BOARD_DATE2"));
            ib.setBoardHit(rs.getInt("INFO_BOARD_HITS"));
            ib.setBoardgood(rs.getInt("INFO_BOARD_GOOD"));
            ib.setBoardReplyNum(rs.getInt("REPLYNUM"));
            list.add(ib);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return list;
   }
   public List<InfoBoard> searchSortInfoBoardList(Connection conn, int cPage, int numPerPage, String searchType,
         String inputText, String sort) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      System.out.println("첫번쨰 : "+((cPage-1)*numPerPage+1));
      System.out.println("두번째 : "+cPage*numPerPage);
      String sql="SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT I.*,P.USER_NICK,TO_CHAR(I.INFO_BOARD_DATE,'yyyy-mm-dd hh24:mi:ss')AS INFO_BOARD_DATE2,(select count(*) from info_reply b where b.INFO_BOARD_REF = i.info_board_num)as REPLYNUM  FROM INFO_BOARD I INNER JOIN PH_USER P ON(I.INFO_BOARD_ID = P.USER_ID) WHERE "+searchType+" LIKE "+"'%"+inputText+"%' ORDER BY "+sort+" DESC)A)WHERE RNUM BETWEEN "+((cPage-1)*numPerPage+1)+" AND "+cPage*numPerPage;
      ArrayList<InfoBoard> list=new ArrayList();
      InfoBoard ib=null;
      
      try {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            
            ib=new InfoBoard();
            ib.setBoardNumber(rs.getInt("INFO_BOARD_NUM"));
            ib.setBoardTitle(rs.getString("INFO_BOARD_TITLE"));
            ib.setBoardContent(rs.getString("INFO_BOARD_CONTENT"));
            ib.setBoardId(rs.getString("USER_NICK"));
            ib.setBoardDate(rs.getString("INFO_BOARD_DATE2"));
            ib.setBoardHit(rs.getInt("INFO_BOARD_HITS"));
            ib.setBoardgood(rs.getInt("INFO_BOARD_GOOD"));
            ib.setBoardReplyNum(rs.getInt("REPLYNUM"));
            list.add(ib);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return list;
   }
   
   
   

}