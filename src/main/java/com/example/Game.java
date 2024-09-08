package com.example;

import java.util.Scanner;

public class Game {
	private char[][] board;
	private char currentPlayer;
	private boolean gameEnded;

	public Game() {
		board = new char[3][3];
		currentPlayer = 'X';
		gameEnded = false;
		initializeBoard();
	}

	private void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		while (!gameEnded) {
			printBoard();
			playerMove(scanner);
			checkForWinner();
			switchPlayer();
		}
		scanner.close();
		promptForNewGame();
	}

	private void printBoard() {
		System.out.println("Current board state:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void playerMove(Scanner scanner) {
		int row, col;
		while (true) {
			System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
			row = scanner.nextInt();
			col = scanner.nextInt();
			if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
				board[row][col] = currentPlayer;
				break;
			} else {
				System.out.println("This move is not valid");
			}
		}
	}

	private void checkForWinner() {
		if (checkRows() || checkColumns() || checkDiagonals()) {
			gameEnded = true;
			printBoard();
			System.out.println("Player " + currentPlayer + " wins!");
		} else if (isBoardFull()) {
			gameEnded = true;
			printBoard();
			System.out.println("The game is a draw!");
		}
	}

	private boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumns() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonals() {
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}
		return false;
	}

	private boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private void switchPlayer() {
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
	}

	private void promptForNewGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to play again? (yes/no)");
		String response = scanner.nextLine();
		if (response.equalsIgnoreCase("yes")) {
			resetGame();
			play();
		} else {
			System.out.println("Thanks for playing!");
		}
		scanner.close();
	}

	private void resetGame() {
		initializeBoard();
		currentPlayer = 'X';
		gameEnded = false;
	}
}
