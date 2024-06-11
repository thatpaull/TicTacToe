package com.example.tictactoe;

public class TicTacToe  {
	private final Player player1;
	private final Player player2;
	private Player currentPlayer;
	private final Board board;

	public TicTacToe() {
		player1 = new Player('X');
		player2 = new Player('O');
		currentPlayer = player1;
		board = new Board();
	}

	public void start() {
		TicTacToeGUI gui = new TicTacToeGUI(this);
		gui.setVisible(true);
	}

	public void switchCurrentPlayer() {
		currentPlayer = (currentPlayer == player1) ? player2 : player1;
	}

	public boolean hasWinner() {
		char[][] cells = board.getCells();
		char marker = currentPlayer.getMarker();

		// Check rows and columns
		for (int i = 0; i < 3; i++) {
			if ((cells[i][0] == marker && cells[i][1] == marker && cells[i][2] == marker) ||
				(cells[0][i] == marker && cells[1][i] == marker && cells[2][i] == marker)) {
				return true;
			}
		}

		// Check diagonals
		return (cells[0][0] == marker && cells[1][1] == marker && cells[2][2] == marker) ||
			(cells[0][2] == marker && cells[1][1] == marker && cells[2][0] == marker);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.start();
	}
}
