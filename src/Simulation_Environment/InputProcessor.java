package Simulation_Environment;

import java.util.Scanner;

public class InputProcessor {
    Scanner sc;

    public InputProcessor(){
        sc = new Scanner(System.in);
    }

    public String getInput(String question){
        System.out.println(question);
        String input = sc.nextLine();

        return input;
    }

    //Hit
    //Stand
    //Split
    //Double
    //Insurance
    public String chooseAction(){
        String option = sc.nextLine();

        return normalize(option);
    }

    public double placeBet(){
        while(true){
            String input = sc.nextLine();
            input = input.replaceAll(",", ".");
            int bet = 0;
            try{
                bet = Integer.valueOf(input);
                if(bet%5 != 0){
                    System.out.println("Please choose a multiple of 5!");
                    continue;
                }
                return bet;
            }catch (Exception e){
                System.out.println("Please use whole numbers only!");
            }
        }
    }

    public String normalize(String option){
        String result = "";

        switch (option.toLowerCase()){
            case "hit":
            case "h":
                result = "hit";
                break;
            case "stand":
            case "s":
                result = "stand";
                break;
            case "split":
            case "p":
                result = "split";
                break;
            case "double":
            case "d":
                result = "double";
                break;
            case "insurance":
            case "i":
            case "insure":
                result = "insurance";
                break;
        }

        return result;
    }
}
