import utils.TeamUtil;

import java.io.File;
import java.util.Scanner;

public class showCricketScoreboard {

    public static void main(String[] args) {

        System.out.println("WELCOME TO CRICKET SCOREBOARD");
        System.out.println("---------------------------------------------------");

        try {
            System.out.println("Take input from user (type '1') or from file (type '2'): ");
            Scanner sc = new Scanner(System.in);
            int option = Integer.parseInt(sc.nextLine());

            if(option==2) {
                File file = new File("../Cricket_Input.txt");
                sc = new Scanner(file);
            }

            System.out.println("Enter number of players in each team: ");
            int numOfPlayers = Integer.parseInt(sc.nextLine());

            System.out.println("Enter number of overs: ");
            int numOfOvers = Integer.parseInt(sc.nextLine());

            BattingTeam firstBattingTeam = new BattingTeam(numOfPlayers, numOfOvers);
            BattingTeam secondBattingTeam = new BattingTeam(numOfPlayers, numOfOvers);

            for (int index = 1; index <= 2; index++) {
                BattingTeam team;
                team = (index==1) ? firstBattingTeam : secondBattingTeam;
                team.takeUserInput(sc);
                System.out.println("---------------------------------------------------");
                System.out.println("Scorecard for Team: " + index);
                System.out.println("---------------------------------------------------");
                team.initiatePlayerInfo();
                for (int currOver = 1; currOver <= numOfOvers; currOver++) {
                    System.out.println("Over: " + currOver);
                    team.calcScoresforcurrOver(currOver, team.getOverList().get(currOver-1));
                    System.out.println("---------------------------------------------------\n");
                }
            }

            if(secondBattingTeam.getTotalRuns() > firstBattingTeam.getTotalRuns()) {
                System.out.println("Team 2 won the match by " + String.valueOf(secondBattingTeam.getTotalRuns()-firstBattingTeam.getTotalRuns()) + " runs");
            }
            else {
                System.out.println("Team 1 won the match by " + String.valueOf(firstBattingTeam.getTotalRuns()-secondBattingTeam.getTotalRuns()) + " runs");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
