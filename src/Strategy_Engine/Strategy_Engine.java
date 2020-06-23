package Strategy_Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

public class Strategy_Engine {
    public static void main(String[] args) {
        Observer o = new Observer(3);


        //moves.add("split");

        //System.out.println("-----------------------");

        long millis = currentTimeMillis();
        Observer o2 = new Observer(8);

        /*System.out.println(Engine.advantageCalculation(o2.getShoe()));
        System.out.println(currentTimeMillis() - millis);

        System.out.println("-----------------");
        System.out.println(Engine.initCount);
        System.out.println(Engine.recursionCount);
        System.out.println(Engine.endCount);
        System.out.println("-----------------");
        System.out.println(Engine.otherCount);*/
        int dealerCard = 6;
        int playerSum = 11;
        //Engine.bestMove(o2.getShoe(), dealerCard, 11, false, Engine.getMoves(3));
        System.out.println("Your next move is: " + Engine.bestMove(o2.getShoe(), dealerCard, playerSum, false, Engine.getMoves(3), false));
        System.out.println("-----------------");
        System.out.println("This took " + (currentTimeMillis() - millis) + "ms");

    }

    public static String bestMove(Observer o, int dealerCard, int playerSum, boolean isPlayerSoftHand, List<String> moves){
        return Engine.bestMove(o.getShoe(), dealerCard, playerSum, isPlayerSoftHand, moves, false);
    }
}