package com.ph.board.image.model.vo;

import java.sql.Date;

public class ImageReply {
	
	private int replyNum;
	private int boardRef;
	private String replyId;
	private Date replyDate;
	private	String replyContent;
	private int replyGood;
	private int replyLevel;
	private int replyRef;
	
	public ImageReply() {}

	public ImageReply(int replyNum, int boardRef, String replyId, Date replyDate, String replyContent, int replyGood,
			int replyLevel, int replyRef) {
		super();
		this.replyNum = replyNum;
		this.boardRef = boardRef;
		this.replyId = replyId;
		this.replyDate = replyDate;
		this.replyContent = replyContent;
		this.replyGood = replyGood;
		this.replyLevel = replyLevel;
		this.replyRef = replyRef;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getBoardRef() {
		return boardRef;
	}

	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getReplyGood() {
		return replyGood;
	}

	public void setReplyGood(int replyGood) {
		this.replyGood = replyGood;
	}

	public int getReplyLevel() {
		return replyLevel;
	}

	public void setReplyLevel(int replyLevel) {
		this.replyLevel = replyLevel;
	}

	public int getReplyRef() {
		return replyRef;
	}

	public void setReplyRef(int replyRef) {
		this.replyRef = replyRef;
	}

	@Override
	public String toString() {
		return "ImageReply [replyNum=" + replyNum + ", boardRef=" + boardRef + ", replyId=" + replyId + ", replyDate="
				+ replyDate + ", replyContent=" + replyContent + ", replyGood=" + replyGood + ", replyLevel="
				+ replyLevel + ", replyRef=" + replyRef + "]";
	}
	
	

}
