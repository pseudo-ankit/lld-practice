import com.aks.interviewready.api.GameEngine;
import com.aks.interviewready.api.RuleEngine;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GamePlayTest {

    private static GameEngine gameEngine;
    private static RuleEngine ruleEngine;

    @BeforeAll
    static void setUp() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }


    @Test
    public void testRowWinFirstPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][] {{0, 0}, {2, 1}, {0, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);
        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testColWinFirstPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{0, 0}, {1, 0}, {2, 0}};
        int[][] secondPlayerMoves = new int[][] {{0, 1}, {1, 1}, {2, 1}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testDaigWinFirstPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][] {{0, 1}, {2, 0}, {2, 1}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testRevDaigWinFirstPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{2, 0}, {1, 1}, {0, 2}};
        int[][] secondPlayerMoves = new int[][] {{0, 0}, {2, 1}, {2, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testWinSecondPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{1, 0}, {1, 1}, {0, 0}};
        int[][] secondPlayerMoves = new int[][] {{2, 0}, {2, 1}, {2, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("X", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testNoWin() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{1, 0}, {1, 1}, {0, 2}, {2, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][] {{0, 0}, {0, 1}, {2, 0}, {1, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("-", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testMoveOnFilledCell() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{1, 0}};
        assertThrows(IllegalArgumentException.class, () -> playGame(ticTacToe, firstPlayerMoves, firstPlayerMoves));
    }

    private static void playGame(Board ticTacToe, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        Player firstPlayer = new Player("O");
        Player secondPlayer = new Player("X");
        int next = 0;
        while (!ruleEngine.isCompleted(ticTacToe).isOver()) {
            int fRow = firstPlayerMoves[next][0];
            int fCol = firstPlayerMoves[next][1];

            gameEngine.move(ticTacToe, new Move(new Cell(fRow, fCol), firstPlayer));
            if (!ruleEngine.isCompleted(ticTacToe).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                gameEngine.move(ticTacToe, new Move(new Cell(sRow, sCol), secondPlayer));
            }
            next++;
        }
    }
}
