package com.ph.freeBoard.model.vo;

	public class FreeBoard {
		private int boardNumber;
		private String boardTitle;
		private String boardContent;
		private String boardId;
		private String boardDate;
		private int boardHit; 
		private int boardgood;
		private int boardReplyNum;
		
		public FreeBoard(){}

		public FreeBoard(int boardNumber, String boardTitle, String boardContent, String boardId, String boardDate,
				int boardHit, int boardgood, int boardReplyNum) {
			super();
			this.boardNumber = boardNumber;
			this.boardTitle = boardTitle;
			this.boardContent = boardContent;
			this.boardId = boardId;
			this.boardDate = boardDate;
			this.boardHit = boardHit;
			this.boardgood = boardgood;
			this.boardReplyNum = boardReplyNum;
		}

		public int getBoardNumber() {
			return boardNumber;
		}

		public void setBoardNumber(int boardNumber) {
			this.boardNumber = boardNumber;
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

		public String getBoardDate() {
			return boardDate;
		}

		public void setBoardDate(String boardDate) {
			this.boardDate = boardDate;
		}

		public int getBoardHit() {
			return boardHit;
		}

		public void setBoardHit(int boardHit) {
			this.boardHit = boardHit;
		}

		public int getBoardgood() {
			return boardgood;
		}

		public void setBoardgood(int boardgood) {
			this.boardgood = boardgood;
		}

		public int getBoardReplyNum() {
			return boardReplyNum;
		}

		public void setBoardReplyNum(int boardReplyNum) {
			this.boardReplyNum = boardReplyNum;
		}

		@Override
		public String toString() {
			return "FreeBoard [boardNumber=" + boardNumber + ", boardTitle=" + boardTitle + ", boardContent="
					+ boardContent + ", boardId=" + boardId + ", boardDate=" + boardDate + ", boardHit=" + boardHit
					+ ", boardgood=" + boardgood + ", boardReplyNum=" + boardReplyNum + "]";
		}

		
		
		
	

}
