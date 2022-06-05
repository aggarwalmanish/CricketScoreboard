package Model;

import java.util.ArrayList;
import java.util.List;

public class Over {

    private final int numOfBalls;
    List<String> ballsInfo = new ArrayList<>();

    public Over(List<String> ballsInfo) {
        this.ballsInfo = ballsInfo;
        numOfBalls = ballsInfo.size();
    }

    public int getNumOfBalls() {
        return numOfBalls;
    }

    public List<String> getBallsInfo() {
        return ballsInfo;
    }
}
