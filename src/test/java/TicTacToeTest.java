import com.example.TicTacToe;
import com.example.Player;
import com.example.Board;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {
	private TicTacToe game;

	@BeforeEach
	public void setUp() {
		game = new TicTacToe();
	}

	@Test
	public void testInitialCurrentPlayer() {
		Player currentPlayer = game.getCurrentPlayer();
		assertEquals('X', currentPlayer.getMarker(), "Player 1 should start with marker 'X'");
	}

	@Test
	public void testSwitchCurrentPlayer() {
		game.switchCurrentPlayer();
		Player currentPlayer = game.getCurrentPlayer();
		assertEquals('O', currentPlayer.getMarker(), "After first switch, player 2 should have marker 'O'");

		game.switchCurrentPlayer();
		currentPlayer = game.getCurrentPlayer();
		assertEquals('X', currentPlayer.getMarker(), "After second switch, player 1 should have marker 'X'");
	}

	@Test
	public void testHasWinner_Row() {
		Board board = game.getBoard();
		char[][] cells = board.getCells();

		// Simulate Player 1 (X) completing a row
		cells[0][0] = 'X';
		cells[0][1] = 'X';
		cells[0][2] = 'X';

		assertTrue(game.hasWinner(), "Player 1 should win with a complete row");
	}

	@Test
	public void testHasWinner_Column() {
		Board board = game.getBoard();
		char[][] cells = board.getCells();

		// Simulate Player 2 (O) completing a column
		game.switchCurrentPlayer(); // Switch to Player 2
		cells[0][0] = 'O';
		cells[1][0] = 'O';
		cells[2][0] = 'O';

		assertTrue(game.hasWinner(), "Player 2 should win with a complete column");
	}

	@Test
	public void testHasWinner_Diagonal() {
		Board board = game.getBoard();
		char[][] cells = board.getCells();

		// Simulate Player 1 (X) completing a diagonal
		cells[0][0] = 'X';
		cells[1][1] = 'X';
		cells[2][2] = 'X';

		assertTrue(game.hasWinner(), "Player 1 should win with a diagonal");
	}

	@Test
	public void testHasWinner_ReverseDiagonal() {
		Board board = game.getBoard();
		char[][] cells = board.getCells();

		// Simulate Player 2 (O) completing the reverse diagonal
		game.switchCurrentPlayer(); // Switch to Player 2
		cells[0][2] = 'O';
		cells[1][1] = 'O';
		cells[2][0] = 'O';

		assertTrue(game.hasWinner(), "Player 2 should win with the reverse diagonal");
	}

	@Test
	public void testNoWinner() {
		Board board = game.getBoard();
		char[][] cells = board.getCells();

		// Simulate an incomplete board with no winner
		cells[0][0] = 'X';
		cells[0][1] = 'O';
		cells[0][2] = 'X';
		cells[1][0] = 'O';
		cells[1][1] = 'X';
		cells[1][2] = 'O';
		cells[2][0] = 'O';
		cells[2][1] = 'X';
		cells[2][2] = 'O';

		assertFalse(game.hasWinner(), "There should be no winner on an incomplete board");
	}
}
