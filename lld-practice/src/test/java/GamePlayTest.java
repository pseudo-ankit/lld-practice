import com.aks.interviewready.api.AIEngine;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GamePlayTest {

    private static GameEngine gameEngine;
    private static RuleEngine ruleEngine;
    private static AIEngine aiEngine;

    @BeforeAll
    static void setUp() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiEngine = new AIEngine();
    }


    @Test
    public void testRowWinUser() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int next = 0;
        int[][] moves = new int[][] {{1, 0}, {1, 1}, {1, 2}};
        playGame(ticTacToe, moves, next, user, computer);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testColWinUser() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int next = 0;
        int[][] moves = new int[][] {{0, 0}, {1, 0}, {2, 0}};
        playGame(ticTacToe, moves, next, user, computer);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testDaigWinUser() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int next = 0;
        int[][] moves = new int[][] {{0, 0}, {1, 1}, {2, 2}};
        playGame(ticTacToe, moves, next, user, computer);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testRevDaigWinUser() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int next = 0;
        int[][] moves = new int[][] {{2, 0}, {1, 1}, {0, 2}};
        playGame(ticTacToe, moves, next, user, computer);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testWinAI() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int next = 0;
        int[][] moves = new int[][] {{1, 0}, {1, 1}, {2, 0}};
        playGame(ticTacToe, moves, next, user, computer);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("X", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    @Test
    public void testNoWin() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int next = 0;
        int[][] moves = new int[][] {{1, 0}, {1, 1}, {0, 2}, {2, 1}, {2, 2}};
        playGame(ticTacToe, moves, next, user, computer);

        assertAll(
                () -> assertTrue(ruleEngine.isCompleted(ticTacToe).isOver()),
                () -> assertEquals("-", ruleEngine.isCompleted(ticTacToe).getWinner())
        );
    }

    private static void playGame(Board ticTacToe, int[][] moves, int next, Player user, Player computer) {
        int row;
        int col;
        while (!ruleEngine.isCompleted(ticTacToe).isOver()) {
            row = moves[next][0];
            col = moves[next][1];
            next++;
            
            gameEngine.move(ticTacToe, new Move(new Cell(row, col), user));
            if (!ruleEngine.isCompleted(ticTacToe).isOver()) {
                Move move = aiEngine.suggestMove(computer, ticTacToe);
                gameEngine.move(ticTacToe, move);
            }
        }
    }
}
