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
}
