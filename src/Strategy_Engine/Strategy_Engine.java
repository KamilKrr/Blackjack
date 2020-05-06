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
        moves.add("split");

        //System.out.println("-----------------------");

        Observer o2 = new Observer(1);

        int dealerCard = 9;

        o2.removeCard(dealerCard);

        Engine.bestMove(o2.getShoe(), dealerCard, 15, false, moves);
    }

    public String bestMove(){
        //return Engine.bestMove();
        return "";
    }
}