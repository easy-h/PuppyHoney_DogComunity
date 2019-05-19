package com.ph.board.image.model.vo;

import java.sql.Date;

public class ImageBoard {
	
	private int boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardId;
	private Date boardDate;
	private int boardHits;
	private int boardGood;

	private int boardReplyNum;
	
	public ImageBoard() {}

	public ImageBoard(int boardNum, String boardTitle, String boardContent, String boardId, Date boardDate,
			int boardHits, int boardGood, int boardReplyNum) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardId = boardId;
		this.boardDate = boardDate;
		this.boardHits = boardHits;
		this.boardGood = boardGood;
		this.boardReplyNum = boardReplyNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardHits() {
		return boardHits;
	}

	public void setBoardHits(int boardHits) {
		this.boardHits = boardHits;
	}

	public int getBoardGood() {
		return boardGood;
	}

	public void setBoardGood(int boardGood) {
		this.boardGood = boardGood;
	}

	public int getBoardReplyNum() {
		return boardReplyNum;
	}

	public void setBoardReplyNum(int boardReplyNum) {
		this.boardReplyNum = boardReplyNum;
	}

	@Override
	public String toString() {
		return "ImageBoard [boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardId=" + boardId + ", boardDate=" + boardDate + ", boardHits=" + boardHits + ", boardGood="
				+ boardGood + ", boardReplyNum=" + boardReplyNum + "]";
	}

	
}