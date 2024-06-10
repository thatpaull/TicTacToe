package com.example.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
	private TicTacToe game;
	private JButton[][] buttons;
	private JLabel statusLabel;

	public TicTacToeGUI(TicTacToe game) {
		this.game = game;
		buttons = new JButton[3][3];
		statusLabel = new JLabel("Player " + game.getCurrentPlayer().getMarker() + "'s turn");

		setTitle("Tic Tac Toe");
		setSize(400, 400);
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

		add(boardPanel, BorderLayout.CENTER);
		add(statusLabel, BorderLayout.SOUTH);
	}

	private class ButtonClickListener implements ActionListener {
		private int row, col;

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
				} else if (game.getBoard().isFull()) {
					statusLabel.setText("It's a draw!");
				} else {
					game.switchCurrentPlayer();
					statusLabel.setText("Player " + game.getCurrentPlayer().getMarker() + "'s turn");
				}
			}
		}
	}
}
