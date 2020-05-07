package Strategy_Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Strategy_Engine {
    public static void main(String[] args){
        Observer o = new Observer(3);

        List<String> moves = new ArrayList<>();
        moves.add("hit");
        moves.add("stand");
        moves.add("double");
        //moves.add("split");

        //System.out.println("-----------------------");

        Observer o2 = new Observer(32);

        int dealerCard = 6;

        System.out.println("Your next move is: " + Engine.bestMove(o2.getShoe(), dealerCard, 11, false, moves));
    }

    public String bestMove(){
        //return Engine.bestMove();
        return "";
    }
}