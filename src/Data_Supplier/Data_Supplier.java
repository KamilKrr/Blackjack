package Data_Supplier;

import Simulation_Environment.Simulation_Environment;
import Strategy_Engine.Engine;
import Strategy_Engine.Observer;

import static java.lang.System.currentTimeMillis;

public class Data_Supplier {

    public static void main(String[] args) {
        //Simulation_Environment simulation_environment = new Simulation_Environment(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        Simulation_Environment simulation_environment = new Simulation_Environment(1, 1, 1, 1);



        //Engine Test
        /*
        long millis = currentTimeMillis();
        Observer o = new Observer(1);

        //System.out.println(Engine.advantageCalculation(o.getShoe()));
        System.out.println(currentTimeMillis() - millis);

        int dealerCard = 6;
        int playerSum = 11;
        System.out.println("Your next move is: " + Engine.bestMove(o.getShoe(), dealerCard, playerSum, false, Engine.getMoves(3), false));
        /**/
    }
}
