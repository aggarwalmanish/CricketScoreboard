package utils;

import Model.Over;
import Model.Player;

import java.util.Formatter;
import java.util.List;

public class TeamUtil {

    public static boolean isWideBall(String ball) {
        return !ball.isEmpty() && ball.equalsIgnoreCase("WD");
    }

    public static boolean isNoBall(String ball) {
        return !ball.isEmpty() && ball.equalsIgnoreCase("No");
    }

    public static boolean isWicket(String ball) {
        return !ball.isEmpty() && ball.equalsIgnoreCase("W");
    }

    public static boolean strikeChange(String ball, int currBallIndex, int totalBalls) {
        int currBall = Integer.parseInt(ball);
        boolean lastBall = currBallIndex==totalBalls-1;
        boolean oddRun = currBall <= 6 && currBall >= 1 && currBall % 2 != 0;
        // Strike will not change if the last ball of over is an odd Run
        return (lastBall && !oddRun) || (!lastBall && oddRun);
    }

    public static boolean isFour(String ball) {
        return !ball.isEmpty() && ball.equalsIgnoreCase("4");
    }

    public static boolean isSix(String ball) {
        return !ball.isEmpty() && ball.equalsIgnoreCase("6");
    }

    public static void printScoreboard(List<Player> playersScore, Player playerOnStrike, Player playerOnNonStrike) {
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s %15s %15s %15s\n", "Player Name", "Score", "4s", "6s", "Balls");
        for (Player player : playersScore)
        {
            String playerName = null;
            if(player==playerOnNonStrike || player == playerOnStrike)
                playerName = player.getName() + "*";
            else
                playerName = player.getName();
            String score = String.valueOf(player.getPlayerScore());
            String fours = String.valueOf(player.getNumOfFour());
            String sixes = String.valueOf(player.getNumOfSix());
            String ballsPlayed = String.valueOf(player.getNumOfBallsPlayed());

            fmt.format("%15s %15s %15s %15s %15s\n", playerName, score, fours, sixes, ballsPlayed);
        }

        System.out.println(fmt);
    }

}
