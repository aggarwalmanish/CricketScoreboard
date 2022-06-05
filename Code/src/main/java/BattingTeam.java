import Model.Over;
import Model.Player;
import utils.TeamUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattingTeam {

    private final List<Player> playersList = new ArrayList<>();
    private final List<String> battingOrder = new ArrayList<>();
    private final List<Over> overList = new ArrayList<>();
    private final int numOfPlayers;
    private final int numOfOvers;
    private int totalRuns;
    private int wicketTaken;
    private Player playerOnStrike;
    private Player playerOnNonStrike;
    private Player nextPlayer;
    private int extraRuns;

    public Player getPlayerOnStrike() {
        return playerOnStrike;
    }

    public Player getPlayerOnNonStrike() {
        return playerOnNonStrike;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getWicketTaken() {
        return wicketTaken;
    }

    public List<Over> getOverList() {
        return overList;
    }

    public BattingTeam(int numOfPlayers, int numOfOvers) {
        this.numOfPlayers = numOfPlayers;
        this.numOfOvers = numOfOvers;
        this.totalRuns = 0;
        this.wicketTaken = 0;
        this.nextPlayer = null;
        this.extraRuns = 0;
        this.playerOnStrike = null;
        this.playerOnNonStrike = null;
    }

    public void takeUserInput(Scanner sc) {

        for (int i = 0; i < numOfPlayers; i++) {
            String player = sc.nextLine();
            battingOrder.add(player);
        }

        for (int i = 0; i < numOfOvers; i++) {
            List<String> ball_List = new ArrayList<>();
            String nextBall;
            int currOvertotalBalls = 6;
            for (int ball = 1; ball <= currOvertotalBalls; ball++) {

                // TODO : put handling when all players are out and balls < 6
                if(!sc.hasNextLine())   break;
                nextBall = sc.nextLine();
                if (!nextBall.isEmpty()) {
                    ball_List.add(nextBall);
                    if(TeamUtil.isWideBall(nextBall) || TeamUtil.isNoBall(nextBall)) {
                        currOvertotalBalls++;
                    }
                }

            }
            Over over = new Over(ball_List);
            overList.add(over);
        }
    }

    public void initiatePlayerInfo() throws Exception {
        for(String playerName : battingOrder) {
            playersList.add(new Player(playerName));
        }

        if(playersList.size()>=2) {
            playerOnStrike = playersList.get(0);
            playerOnNonStrike = playersList.get(1);
            if(playersList.size()>=3)
                nextPlayer = playersList.get(2);
        }
        else {
            throw new Exception("Number of players should be minimum 2 in each team");
        }
    }

    void calcScoresforcurrOver(int overIndex, Over currentOver){

        for(int currBallIndex = 0; currBallIndex <currentOver.getBallsInfo().size();currBallIndex++) {
            String currBall = currentOver.getBallsInfo().get(currBallIndex);
            if(TeamUtil.isWicket(currBall)) {
                playerOnStrike.setOut(true);
                wicketTaken++;
                playerOnStrike.incrementNumOfBallsPlayed();
                if(nextPlayer==null)
                    break;

                playerOnStrike = nextPlayer;
                for(Player player: playersList) {
                    if(!player.isOut() && player!=playerOnStrike && player!=playerOnNonStrike) {
                        nextPlayer = player;
                        break;
                    }
                }
            }

            else {
                if (!currBall.isEmpty() && !TeamUtil.isNoBall(currBall)) {
                    int currBallRun = 0;
                    if(TeamUtil.isWideBall(currBall)) {
                        totalRuns = totalRuns + 1;
                        extraRuns = extraRuns + 1;
                    }
                    else {
                        currBallRun = Integer.parseInt(currBall);
                        if(currBallRun==4)  playerOnStrike.incrementNumOfFour();
                        if(currBallRun==6)  playerOnStrike.incrementNumOfSix();
                        playerOnStrike.incrementNumOfBallsPlayed();
                        playerOnStrike.setPlayerScore(playerOnStrike.getPlayerScore()+currBallRun);
                        totalRuns = totalRuns + currBallRun;
                    }
                    if (!TeamUtil.isWideBall(currBall) && TeamUtil.strikeChange(currBall, currBallIndex, currentOver.getBallsInfo().size())) {
                        Player tempPlayer = playerOnStrike;
                        playerOnStrike = playerOnNonStrike;
                        playerOnNonStrike = tempPlayer;
                    }
                }

            }
        }

        TeamUtil.printScoreboard(playersList, playerOnStrike, playerOnNonStrike);

        System.out.println("Total: " + getTotalRuns() + "/" + getWicketTaken());
        System.out.println("Overs: " + overIndex);
        System.out.println("Extra Runs by team till now: " + getExtraRuns());
    }

}
