package com.ph.board.news.model.vo;

import java.sql.Date;

public class NewsBoard {
   private int newsBoardNum;
   private String newsBoardTitle;
   private String newsBoardContent;
   private Date newsBoardDate;
   private int newsBoardHits;
   private int newsBoardGood;
   private int newsBoardReplynum;
   
   public NewsBoard() {}

   public NewsBoard(int newsBoardNum, String newsBoardTitle, String newsBoardContent, Date newsBoardDate,
         int newsBoardHits, int newsBoardGood, int newsBoardReplynum) {
      super();
      this.newsBoardNum = newsBoardNum;
      this.newsBoardTitle = newsBoardTitle;
      this.newsBoardContent = newsBoardContent;
      this.newsBoardDate = newsBoardDate;
      this.newsBoardHits = newsBoardHits;
      this.newsBoardGood = newsBoardGood;
      this.newsBoardReplynum = newsBoardReplynum;
   }

   public int getNewsBoardNum() {
      return newsBoardNum;
   }

   public void setNewsBoardNum(int newsBoardNum) {
      this.newsBoardNum = newsBoardNum;
   }

   public String getNewsBoardTitle() {
      return newsBoardTitle;
   }

   public void setNewsBoardTitle(String newsBoardTitle) {
      this.newsBoardTitle = newsBoardTitle;
   }

   public String getNewsBoardContent() {
      return newsBoardContent;
   }

   public void setNewsBoardContent(String newsBoardContent) {
      this.newsBoardContent = newsBoardContent;
   }

   public Date getNewsBoardDate() {
      return newsBoardDate;
   }

   public void setNewsBoardDate(Date newsBoardDate) {
      this.newsBoardDate = newsBoardDate;
   }

   public int getNewsBoardHits() {
      return newsBoardHits;
   }

   public void setNewsBoardHits(int newsBoardHits) {
      this.newsBoardHits = newsBoardHits;
   }

   public int getNewsBoardGood() {
      return newsBoardGood;
   }

   public void setNewsBoardGood(int newsBoardGood) {
      this.newsBoardGood = newsBoardGood;
   }

   public int getNewsBoardReplynum() {
      return newsBoardReplynum;
   }

   public void setNewsBoardReplynum(int newsBoardReplynum) {
      this.newsBoardReplynum = newsBoardReplynum;
   }

   @Override
   public String toString() {
      return "NewsBoard [newsBoardNum=" + newsBoardNum + ", newsBoardTitle=" + newsBoardTitle + ", newsBoardContent="
            + newsBoardContent + ", newsBoardDate=" + newsBoardDate + ", newsBoardHits=" + newsBoardHits
            + ", newsBoardGood=" + newsBoardGood + ", newsBoardReplynum=" + newsBoardReplynum + "]";
   }


   
}