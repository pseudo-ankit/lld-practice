import com.aks.interviewready.api.GameEngine;
import com.aks.interviewready.api.GameInfo;
import com.aks.interviewready.api.RuleEngine;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {2, 1}, {0, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);
        assertAll(
                () -> assertTrue(ruleEngine.getState(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.getState(ticTacToe).getWinner())
        );
    }

    @Test
    public void testColWinFirstPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {1, 1}, {2, 1}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.getState(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.getState(ticTacToe).getWinner())
        );
    }

    @Test
    public void testDaigWinFirstPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {2, 0}, {2, 1}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.getState(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.getState(ticTacToe).getWinner())
        );
    }

    @Test
    public void testRevDaigWinFirstPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{2, 0}, {1, 1}, {0, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {2, 1}, {2, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.getState(ticTacToe).isOver()),
                () -> assertEquals("O", ruleEngine.getState(ticTacToe).getWinner())
        );
    }

    @Test
    public void testWinSecondPlayer() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {0, 0}};
        int[][] secondPlayerMoves = new int[][]{{2, 0}, {2, 1}, {2, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.getState(ticTacToe).isOver()),
                () -> assertEquals("X", ruleEngine.getState(ticTacToe).getWinner())
        );
    }

    @Test
    public void testNoWin() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {0, 2}, {2, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {2, 0}, {1, 2}};
        playGame(ticTacToe, firstPlayerMoves, secondPlayerMoves);

        assertAll(
                () -> assertTrue(ruleEngine.getState(ticTacToe).isOver()),
                () -> assertEquals("-", ruleEngine.getState(ticTacToe).getWinner())
        );
    }

    @Test
    public void testMoveOnFilledCell() {
        Board ticTacToe = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1, 0}};
        assertThrows(IllegalArgumentException.class, () -> playGame(ticTacToe, firstPlayerMoves, firstPlayerMoves));
    }

    // TODO REFACTOR : TestCase class
    @Test
    public void testIsFork() {
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Fork Exists
        testCases.add(new TestCase(
                new String[][]{
                        {"X", " ", " "},
                        {" ", "O", " "},
                        {"X", " ", "X"}
                }, true));

        // Test Case 2: No Fork
        testCases.add(new TestCase(
                new String[][]{
                        {"X", "O", " "},
                        {" ", "X", " "},
                        {" ", " ", "O"}
                }, false));

        // Test Case 3: Fork Exists
        testCases.add(new TestCase(
                new String[][]{
                        {"X", "O", " "},
                        {" ", "X", " "},
                        {" ", "O", "X"}
                }, false));

        // Test Case 4: No Fork
        testCases.add(new TestCase(
                new String[][]{
                        {"X", "O", "X"},
                        {"O", "X", "O"},
                        {"O", "X", "O"}
                }, false));

        // Test Case 5: Fork Exists
        testCases.add(new TestCase(
                new String[][]{
                        {"X", " ", " "},
                        {" ", "X", "O"},
                        {" ", " ", "X"}
                }, false));

        // Test Case 6: No Fork
        testCases.add(new TestCase(
                new String[][]{
                        {" ", " ", " "},
                        {" ", "X", " "},
                        {" ", " ", " "}
                }, false));

        // Test Case 7: Fork Exists
        testCases.add(new TestCase(
                new String[][]{
                        {"X", " ", "O"},
                        {" ", "O", " "},
                        {"X", " ", "X"}
                }, true));

        // Test Case 8: No Fork
        testCases.add(new TestCase(
                new String[][]{
                        {"O", "X", " "},
                        {"X", "O", " "},
                        {" ", " ", "X"}
                }, false));

        // Test Case 9: Fork Exists
        testCases.add(new TestCase(
                new String[][]{
                        {"X", "O", " "},
                        {"O", "X", " "},
                        {" ", " ", "X"}
                }, false));

        // Test Case 10: No Fork
        testCases.add(new TestCase(
                new String[][]{
                        {" ", " ", " "},
                        {" ", " ", " "},
                        {" ", " ", " "}
                }, false));

        String[][] board = {
                {"X", " ", " "},
                {" ", "O", " "},
                {"X", " ", "X"}
        };
        final int[] count = {1};
        testCases.forEach(testCase -> {
            Board ticTacToe = buildBoard(testCase.matrix);
            GameInfo gameInfo = ruleEngine.getFork(ticTacToe);
            if (testCase.isFork) {
                assertTrue(gameInfo.isHasFork(), "index : {%s}".formatted(count[0]));
                assertEquals("X", gameInfo.getPlayer().getSymbol());
            } else {
                assertFalse(gameInfo.isHasFork(), "index : {%s}".formatted(count[0]));
            }
            count[0]++;
        });
    }

    private static void playGame(Board ticTacToe, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        Player firstPlayer = new Player("O");
        Player secondPlayer = new Player("X");
        int next = 0;
        while (!ruleEngine.getState(ticTacToe).isOver()) {
            int fRow = firstPlayerMoves[next][0];
            int fCol = firstPlayerMoves[next][1];

            gameEngine.move(ticTacToe, new Move(new Cell(fRow, fCol), firstPlayer));
            if (!ruleEngine.getState(ticTacToe).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                gameEngine.move(ticTacToe, new Move(new Cell(sRow, sCol), secondPlayer));
            }
            next++;
        }
    }

    private Board buildBoard(String[][] board) {
        Board ticTacToe = gameEngine.start("TicTacToe");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String symbol = board[i][j];
                if (symbol != " ") {
                    ticTacToe.move(new Move(new Cell(i, j), new Player(board[i][j])));
                }
            }
        }
        return ticTacToe;
    }

    class TestCase {
        String[][] matrix;
        boolean isFork = false;

        public TestCase(String[][] matrix, boolean isFork) {
            this.matrix = matrix;
            this.isFork = isFork;
        }
    }
}