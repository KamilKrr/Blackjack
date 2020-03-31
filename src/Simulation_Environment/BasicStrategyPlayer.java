package Simulation_Environment;

import java.util.HashMap;

public class BasicStrategyPlayer {
    public static HashMap<String, HashMap<String, String>> basicStrategy = new HashMap<>();

    public BasicStrategyPlayer() {

        //dealer Upcard 2
        basicStrategy.put("2", new HashMap<>());
        basicStrategy.get("2").put("4", "H");
        basicStrategy.get("2").put("5", "H");
        basicStrategy.get("2").put("6", "H");
        basicStrategy.get("2").put("7", "H");
        basicStrategy.get("2").put("8", "H");
        basicStrategy.get("2").put("9", "H");
        basicStrategy.get("2").put("10", "DH");
        basicStrategy.get("2").put("11", "DH");
        basicStrategy.get("2").put("12", "H");
        basicStrategy.get("2").put("13", "S");
        basicStrategy.get("2").put("14", "PS");
        basicStrategy.get("2").put("15", "S");
        basicStrategy.get("2").put("16", "PS");
        basicStrategy.get("2").put("17", "S");
        basicStrategy.get("2").put("18", "PS");
        basicStrategy.get("2").put("19", "S");
        basicStrategy.get("2").put("20", "S");
        basicStrategy.get("2").put("s12", "PH");
        basicStrategy.get("2").put("s13", "H");
        basicStrategy.get("2").put("s14", "H");
        basicStrategy.get("2").put("s15", "H");
        basicStrategy.get("2").put("s16", "H");
        basicStrategy.get("2").put("s17", "H");
        basicStrategy.get("2").put("s18", "S");
        basicStrategy.get("2").put("s19", "S");
        basicStrategy.get("2").put("s20", "S");

        //dealer Upcard 3
        basicStrategy.put("3", new HashMap<>());
        basicStrategy.get("3").put("4", "H");
        basicStrategy.get("3").put("5", "H");
        basicStrategy.get("3").put("6", "H");
        basicStrategy.get("3").put("7", "H");
        basicStrategy.get("3").put("8", "H");
        basicStrategy.get("3").put("9", "DH");
        basicStrategy.get("3").put("10", "DH");
        basicStrategy.get("3").put("11", "DH");
        basicStrategy.get("3").put("12", "PH");
        basicStrategy.get("3").put("13", "S");
        basicStrategy.get("3").put("14", "PS");
        basicStrategy.get("3").put("15", "S");
        basicStrategy.get("3").put("16", "PS");
        basicStrategy.get("3").put("17", "S");
        basicStrategy.get("3").put("18", "PS");
        basicStrategy.get("3").put("19", "S");
        basicStrategy.get("3").put("20", "S");
        basicStrategy.get("3").put("s12", "PH");
        basicStrategy.get("3").put("s13", "H");
        basicStrategy.get("3").put("s14", "H");
        basicStrategy.get("3").put("s15", "H");
        basicStrategy.get("3").put("s16", "H");
        basicStrategy.get("3").put("s17", "DH");
        basicStrategy.get("3").put("s18", "DS");
        basicStrategy.get("3").put("s19", "S");
        basicStrategy.get("3").put("s20", "S");

        //dealer Upcard 4
        basicStrategy.put("4", new HashMap<>());
        basicStrategy.get("4").put("4", "PH");
        basicStrategy.get("4").put("5", "H");
        basicStrategy.get("4").put("6", "PH");
        basicStrategy.get("4").put("7", "H");
        basicStrategy.get("4").put("8", "H");
        basicStrategy.get("4").put("9", "DH");
        basicStrategy.get("4").put("10", "DH");
        basicStrategy.get("4").put("11", "DH");
        basicStrategy.get("4").put("12", "PS");
        basicStrategy.get("4").put("13", "S");
        basicStrategy.get("4").put("14", "PS");
        basicStrategy.get("4").put("15", "S");
        basicStrategy.get("4").put("16", "PS");
        basicStrategy.get("4").put("17", "S");
        basicStrategy.get("4").put("18", "PS");
        basicStrategy.get("4").put("19", "S");
        basicStrategy.get("4").put("20", "S");
        basicStrategy.get("4").put("s12", "PH");
        basicStrategy.get("4").put("s13", "H");
        basicStrategy.get("4").put("s14", "H");
        basicStrategy.get("4").put("s15", "DH");
        basicStrategy.get("4").put("s16", "DH");
        basicStrategy.get("4").put("s17", "DH");
        basicStrategy.get("4").put("s18", "DS");
        basicStrategy.get("4").put("s19", "S");
        basicStrategy.get("4").put("s20", "S");

        //dealer Upcard 5
        basicStrategy.put("5", new HashMap<>());
        basicStrategy.get("5").put("4", "PH");
        basicStrategy.get("5").put("5", "H");
        basicStrategy.get("5").put("6", "PH");
        basicStrategy.get("5").put("7", "H");
        basicStrategy.get("5").put("8", "H");
        basicStrategy.get("5").put("9", "DH");
        basicStrategy.get("5").put("10", "DH");
        basicStrategy.get("5").put("11", "DH");
        basicStrategy.get("5").put("12", "PS");
        basicStrategy.get("5").put("13", "S");
        basicStrategy.get("5").put("14", "PS");
        basicStrategy.get("5").put("15", "S");
        basicStrategy.get("5").put("16", "PS");
        basicStrategy.get("5").put("17", "S");
        basicStrategy.get("5").put("18", "PS");
        basicStrategy.get("5").put("19", "S");
        basicStrategy.get("5").put("20", "S");
        basicStrategy.get("5").put("s12", "PH");
        basicStrategy.get("5").put("s13", "DH");
        basicStrategy.get("5").put("s14", "DH");
        basicStrategy.get("5").put("s15", "DH");
        basicStrategy.get("5").put("s16", "DH");
        basicStrategy.get("5").put("s17", "DH");
        basicStrategy.get("5").put("s18", "DS");
        basicStrategy.get("5").put("s19", "S");
        basicStrategy.get("5").put("s20", "S");

        //dealer Upcard 6
        basicStrategy.put("6", new HashMap<>());
        basicStrategy.get("6").put("4", "PH");
        basicStrategy.get("6").put("5", "H");
        basicStrategy.get("6").put("6", "PH");
        basicStrategy.get("6").put("7", "H");
        basicStrategy.get("6").put("8", "H");
        basicStrategy.get("6").put("9", "DH");
        basicStrategy.get("6").put("10", "DH");
        basicStrategy.get("6").put("11", "DH");
        basicStrategy.get("6").put("12", "PS");
        basicStrategy.get("6").put("13", "S");
        basicStrategy.get("6").put("14", "PS");
        basicStrategy.get("6").put("15", "S");
        basicStrategy.get("6").put("16", "PS");
        basicStrategy.get("6").put("17", "S");
        basicStrategy.get("6").put("18", "PS");
        basicStrategy.get("6").put("19", "S");
        basicStrategy.get("6").put("20", "S");
        basicStrategy.get("6").put("s12", "PH");
        basicStrategy.get("6").put("s13", "DH");
        basicStrategy.get("6").put("s14", "DH");
        basicStrategy.get("6").put("s15", "DH");
        basicStrategy.get("6").put("s16", "DH");
        basicStrategy.get("6").put("s17", "DH");
        basicStrategy.get("6").put("s18", "DS");
        basicStrategy.get("6").put("s19", "S");
        basicStrategy.get("6").put("s20", "S");

        //dealer Upcard 7
        basicStrategy.put("7", new HashMap<>());
        basicStrategy.get("7").put("4", "PH");
        basicStrategy.get("7").put("5", "H");
        basicStrategy.get("7").put("6", "PH");
        basicStrategy.get("7").put("7", "H");
        basicStrategy.get("7").put("8", "H");
        basicStrategy.get("7").put("9", "H");
        basicStrategy.get("7").put("10", "DH");
        basicStrategy.get("7").put("11", "DH");
        basicStrategy.get("7").put("12", "H");
        basicStrategy.get("7").put("13", "H");
        basicStrategy.get("7").put("14", "PH");
        basicStrategy.get("7").put("15", "H");
        basicStrategy.get("7").put("16", "PH");
        basicStrategy.get("7").put("17", "S");
        basicStrategy.get("7").put("18", "S");
        basicStrategy.get("7").put("19", "S");
        basicStrategy.get("7").put("20", "S");
        basicStrategy.get("7").put("s12", "PH");
        basicStrategy.get("7").put("s13", "H");
        basicStrategy.get("7").put("s14", "H");
        basicStrategy.get("7").put("s15", "H");
        basicStrategy.get("7").put("s16", "H");
        basicStrategy.get("7").put("s17", "H");
        basicStrategy.get("7").put("s18", "S");
        basicStrategy.get("7").put("s19", "S");
        basicStrategy.get("7").put("s20", "S");

        //dealer Upcard 8
        basicStrategy.put("8", new HashMap<>());
        basicStrategy.get("8").put("4", "H");
        basicStrategy.get("8").put("5", "H");
        basicStrategy.get("8").put("6", "H");
        basicStrategy.get("8").put("7", "H");
        basicStrategy.get("8").put("8", "H");
        basicStrategy.get("8").put("9", "H");
        basicStrategy.get("8").put("10", "DH");
        basicStrategy.get("8").put("11", "DH");
        basicStrategy.get("8").put("12", "H");
        basicStrategy.get("8").put("13", "H");
        basicStrategy.get("8").put("14", "H");
        basicStrategy.get("8").put("15", "H");
        basicStrategy.get("8").put("16", "PH");
        basicStrategy.get("8").put("17", "S");
        basicStrategy.get("8").put("18", "PS");
        basicStrategy.get("8").put("19", "S");
        basicStrategy.get("8").put("20", "S");
        basicStrategy.get("8").put("s12", "PH");
        basicStrategy.get("8").put("s13", "H");
        basicStrategy.get("8").put("s14", "H");
        basicStrategy.get("8").put("s15", "H");
        basicStrategy.get("8").put("s16", "H");
        basicStrategy.get("8").put("s17", "H");
        basicStrategy.get("8").put("s18", "S");
        basicStrategy.get("8").put("s19", "S");
        basicStrategy.get("8").put("s20", "S");

        //dealer Upcard 9
        basicStrategy.put("9", new HashMap<>());
        basicStrategy.get("9").put("4", "H");
        basicStrategy.get("9").put("5", "H");
        basicStrategy.get("9").put("6", "H");
        basicStrategy.get("9").put("7", "H");
        basicStrategy.get("9").put("8", "H");
        basicStrategy.get("9").put("9", "H");
        basicStrategy.get("9").put("10", "DH");
        basicStrategy.get("9").put("11", "DH");
        basicStrategy.get("9").put("12", "H");
        basicStrategy.get("9").put("13", "H");
        basicStrategy.get("9").put("14", "H");
        basicStrategy.get("9").put("15", "H");
        basicStrategy.get("9").put("16", "PH");
        basicStrategy.get("9").put("17", "S");
        basicStrategy.get("9").put("18", "PS");
        basicStrategy.get("9").put("19", "S");
        basicStrategy.get("9").put("20", "S");
        basicStrategy.get("9").put("s12", "PH");
        basicStrategy.get("9").put("s13", "H");
        basicStrategy.get("9").put("s14", "H");
        basicStrategy.get("9").put("s15", "H");
        basicStrategy.get("9").put("s16", "H");
        basicStrategy.get("9").put("s17", "H");
        basicStrategy.get("9").put("s18", "H");
        basicStrategy.get("9").put("s19", "S");
        basicStrategy.get("9").put("s20", "S");

        //dealer Upcard 10
        basicStrategy.put("10", new HashMap<>());
        basicStrategy.get("10").put("4", "H");
        basicStrategy.get("10").put("5", "H");
        basicStrategy.get("10").put("6", "H");
        basicStrategy.get("10").put("7", "H");
        basicStrategy.get("10").put("8", "H");
        basicStrategy.get("10").put("9", "H");
        basicStrategy.get("10").put("10", "H");
        basicStrategy.get("10").put("11", "DH");
        basicStrategy.get("10").put("12", "H");
        basicStrategy.get("10").put("13", "H");
        basicStrategy.get("10").put("14", "H");
        basicStrategy.get("10").put("15", "H");
        basicStrategy.get("10").put("16", "PH");
        basicStrategy.get("10").put("17", "S");
        basicStrategy.get("10").put("18", "S");
        basicStrategy.get("10").put("19", "S");
        basicStrategy.get("10").put("20", "S");
        basicStrategy.get("10").put("s12", "PH");
        basicStrategy.get("10").put("s13", "H");
        basicStrategy.get("10").put("s14", "H");
        basicStrategy.get("10").put("s15", "H");
        basicStrategy.get("10").put("s16", "H");
        basicStrategy.get("10").put("s17", "H");
        basicStrategy.get("10").put("s18", "H");
        basicStrategy.get("10").put("s19", "S");
        basicStrategy.get("10").put("s20", "S");

        //dealer Upcard 11
        basicStrategy.put("11", new HashMap<>());
        basicStrategy.get("11").put("4", "H");
        basicStrategy.get("11").put("5", "H");
        basicStrategy.get("11").put("6", "H");
        basicStrategy.get("11").put("7", "H");
        basicStrategy.get("11").put("8", "H");
        basicStrategy.get("11").put("9", "H");
        basicStrategy.get("11").put("10", "H");
        basicStrategy.get("11").put("11", "H");
        basicStrategy.get("11").put("12", "H");
        basicStrategy.get("11").put("13", "H");
        basicStrategy.get("11").put("14", "H");
        basicStrategy.get("11").put("15", "H");
        basicStrategy.get("11").put("16", "PH");
        basicStrategy.get("11").put("17", "S");
        basicStrategy.get("11").put("18", "S");
        basicStrategy.get("11").put("19", "S");
        basicStrategy.get("11").put("20", "S");
        basicStrategy.get("11").put("s12", "PH");
        basicStrategy.get("11").put("s13", "H");
        basicStrategy.get("11").put("s14", "H");
        basicStrategy.get("11").put("s15", "H");
        basicStrategy.get("11").put("s16", "H");
        basicStrategy.get("11").put("s17", "H");
        basicStrategy.get("11").put("s18", "H");
        basicStrategy.get("11").put("s19", "S");
        basicStrategy.get("11").put("s20", "S");


    }

    public String nextAction(int dealerhand, int playerhand, boolean softHand, boolean doubleCard, int currentBet, int bankroll, int numberOfCards) {
        String player = "";
        if (softHand) { player = "s"; }
        player = player + playerhand;
        String dealer = dealerhand + "";

        String action = basicStrategy.get(dealer).get(player);


        if (action.charAt(0) == 'D') {
            if(numberOfCards == 2 && bankroll >= currentBet) { action = "D"; }
            else { action = action.charAt(1) + ""; }
        }

        if (action.charAt(0) == 'P') {
            if(doubleCard && bankroll >= currentBet) { action = "P"; }
            else { action = action.charAt(1) + ""; }
        }

        return action;
    }
}
