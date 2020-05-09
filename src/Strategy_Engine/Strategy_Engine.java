package Strategy_Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

public class Strategy_Engine {
    public static void main(String[] args){
        Observer o = new Observer(3);


        //moves.add("split");

        //System.out.println("-----------------------");

        long millis = currentTimeMillis();
        Observer o2 = new Observer(1);
        System.out.println(Engine.advantageCalculation(o2.getShoe()));
        System.out.println(currentTimeMillis() - millis);
        System.out.println("-----------------");
        System.out.println(Engine.initCount);
        System.out.println(Engine.recursionCount);
        System.out.println(Engine.endCount);
        System.out.println("-----------------");
        System.out.println(Engine.otherCount);
        int dealerCard = 6;

        //System.out.println("Your next move is: " + Engine.bestMove(o2.getShoe(), dealerCard, 11, false, Engine.getMoves(3), Engine.dealerProbabilities(o2.getShoe(), dealerCard, 1.0, Engine.dealer, false)));
    }

    public String bestMove(){
        //return Engine.bestMove();
        return "";
    }
}