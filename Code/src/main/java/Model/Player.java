package Model;

public class Player {

    private String name;
    private boolean isOut;
    private int playerScore;
    private int numOfFour;
    private int numOfSix;
    private int numOfBallsPlayed;

    public Player(String name) {
        this.name = name;
        this.isOut = false;
        this.playerScore = 0;
        this.numOfBallsPlayed = 0;
        this.numOfFour = 0;
        this.numOfSix = 0;
    }

    public String getName() {
        return name;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public int getNumOfFour() {
        return numOfFour;
    }

    public void incrementNumOfFour() {
        this.numOfFour++;
    }

    public int getNumOfSix() {
        return numOfSix;
    }

    public void incrementNumOfSix() {
        this.numOfSix++;
    }

    public int getNumOfBallsPlayed() {
        return numOfBallsPlayed;
    }

    public void incrementNumOfBallsPlayed() {
        this.numOfBallsPlayed++;
    }
}
