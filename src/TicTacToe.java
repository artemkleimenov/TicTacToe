import java.util.stream.IntStream;

public class TicTacToe {
	private String[]  board = IntStream.rangeClosed(1, 9).mapToObj(String::valueOf).toArray(String[]::new); 
	private static final String NOUGHT = "0";
	private static final String WIN_LINE_NOUGHTS = "000";
	private static final String CROSSE = "X";
	private static final String WIN_LINE_CROSSES = "XXX";
	private int turnCounter = 0;
	private String valueXO = "";
	private boolean endGame = false;
	private int cellNumber = 0;
	private String gameResult = "";
	
	public void makeTurn() {
		whoseTurnXO();
		turn(getCellNumber(), getXO());
	}
	
	private void turn(int cellNum, String X0) {
		board[cellNum - 1] = X0;
		
		turnCounter++;
		
		checkDrawOrWin();
	}

	public String getXO() {
		return valueXO;
	}
	
	private void whoseTurnXO() {
		boolean whoseTurn = turnCounter % 2 == 0;
		valueXO = whoseTurn ? String.valueOf(CROSSE) : String.valueOf(NOUGHT);
	}
	
	public void setCellNumber(int cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	public int getCellNumber() {
		return cellNumber;
	}
	
	private void checkDrawOrWin() {
		if (turnCounter >= 5 && turnCounter <= 9) {
			win();
		}
		
		if (turnCounter == 9 && !endGame) {
			draw();
		}
	}

	private void win() {
		String line = null;

		for (int i = 1; i <= 8; i++) {

			switch (i) {
			case 1:
				line = board[0] + board[4] + board[8];
				break;
			case 2:
				line = board[2] + board[4] + board[6];
				break;
			case 3:
				line = board[0] + board[1] + board[2];
				break;
			case 4:
				line = board[3] + board[4] + board[5];
				break;
			case 5:
				line = board[6] + board[7] + board[8];
				break;
			case 6:
				line = board[0] + board[3] + board[6];
				break;
			case 7:
				line = board[1] + board[4] + board[7];
				break;
			case 8:
				line = board[2] + board[5] + board[8];
				break;
			}

			if (line.equals(WIN_LINE_CROSSES)) {
				endGame = true;
				gameResult = "FLAWLESS VICTORY! X WIN!";
			} else if (line.equals(WIN_LINE_NOUGHTS)) {
				endGame = true;
				gameResult = "FLAWLESS VICTORY! O WIN!";
			}
		}
	}

	private void draw() {
			endGame = true;
			gameResult = "DRAW!";
	}
	
	public void reset() {
		board = IntStream.rangeClosed(1, 9).mapToObj(String::valueOf).toArray(String[]::new);
		turnCounter = 0;
		endGame = false;
	}
	
	public boolean gameStatus() {
		return endGame;
	}
	
	public String getGameResult() {
		return gameResult;
	}
}