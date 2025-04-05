package interviewready.game;

public class GameResult {
    private final boolean isCompleted;
    private final String winner;

    public GameResult(boolean isCompleted, String winner) {
        this.isCompleted = isCompleted;
        this.winner = winner;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "isCompleted=" + isCompleted +
                ", winner='" + winner + '\'' +
                '}';
    }

    public String getWinner() {
        return winner;
    }
}
