package com.example.tictactoe;

public class TicTacToe {
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Board board;

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

		// Check rows
		for (int i = 0; i < 3; i++) {
			if (cells[i][0] == currentPlayer.getMarker() && cells[i][1] == currentPlayer.getMarker() && cells[i][2] == currentPlayer.getMarker()) {
				return true;
			}
		}

		// Check columns
		for (int i = 0; i < 3; i++) {
			if (cells[0][i] == currentPlayer.getMarker() && cells[1][i] == currentPlayer.getMarker() && cells[2][i] == currentPlayer.getMarker()) {
				return true;
			}
		}

		// Check diagonals
		if (cells[0][0] == currentPlayer.getMarker() && cells[1][1] == currentPlayer.getMarker() && cells[2][2] == currentPlayer.getMarker()) {
			return true;
		}

		if (cells[0][2] == currentPlayer.getMarker() && cells[1][1] == currentPlayer.getMarker() && cells[2][0] == currentPlayer.getMarker()) {
			return true;
		}

		return false;
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
