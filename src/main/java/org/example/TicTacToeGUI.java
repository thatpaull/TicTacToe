package com.example.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
	private final TicTacToe game;
	private final JButton[][] buttons;
	private final JLabel statusLabel;
	private final JLabel scoreLabel;
	private final JLabel drawLabel;
	private int player1Score;
	private int player2Score;
	private int drawCount;

	public TicTacToeGUI(TicTacToe game) {
		this.game = game;
		this.buttons = new JButton[3][3];
		this.statusLabel = new JLabel("Player " + game.getCurrentPlayer().getMarker() + "'s turn", SwingConstants.CENTER);
		this.scoreLabel = new JLabel("Player X: 0 | Player O: 0", SwingConstants.CENTER);
		this.drawLabel = new JLabel("Draws: 0", SwingConstants.CENTER);
		this.player1Score = 0;
		this.player2Score = 0;
		this.drawCount = 0;

		setTitle("Tic Tac Toe");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton("");
				buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
				buttons[i][j].setFocusPainted(false);
				buttons[i][j].addActionListener(new ButtonClickListener(i, j));
				boardPanel.add(buttons[i][j]);
			}
		}

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3, 1));
		infoPanel.add(statusLabel);
		infoPanel.add(scoreLabel);
		infoPanel.add(drawLabel);

		// Styling the labels for better visibility
		statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
		drawLabel.setFont(new Font("Arial", Font.BOLD, 18));

		add(boardPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.SOUTH);
	}

	private class ButtonClickListener implements ActionListener {
		private final int row;
		private final int col;

		public ButtonClickListener(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!game.hasWinner() && game.getBoard().isCellEmpty(row, col)) {
				game.getBoard().place(row, col, game.getCurrentPlayer().getMarker());
				buttons[row][col].setText(String.valueOf(game.getCurrentPlayer().getMarker()));

				if (game.hasWinner()) {
					statusLabel.setText("Player " + game.getCurrentPlayer().getMarker() + " wins!");
					updateScore();
					int response = JOptionPane.showConfirmDialog(null, "Player " + game.getCurrentPlayer().getMarker() + " wins! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						resetGame();
					} else {
						System.exit(0);
					}
				} else if (game.getBoard().isFull()) {
					statusLabel.setText("It's a draw!");
					drawCount++;
					drawLabel.setText("Draws: " + drawCount);
					int response = JOptionPane.showConfirmDialog(null, "It's a draw! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						resetGame();
					} else {
						System.exit(0);
					}
				} else {
					game.switchCurrentPlayer();
					statusLabel.setText("Player " + game.getCurrentPlayer().getMarker() + "'s turn");
				}
			}
		}
	}

	private void updateScore() {
		if (game.getCurrentPlayer().getMarker() == 'X') {
			player1Score++;
		} else {
			player2Score++;
		}
		scoreLabel.setText("Player X: " + player1Score + " | Player O: " + player2Score);
	}

	private void resetGame() {
		game.getBoard().clear();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j].setText("");
			}
		}
		statusLabel.setText("Player " + game.getCurrentPlayer().getMarker() + "'s turn");
	}
}
