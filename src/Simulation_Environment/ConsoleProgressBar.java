package Simulation_Environment;

public class ConsoleProgressBar {
    public ConsoleProgressBar(){

    }
    
    public int progress = 0;
    private int previousProgress = -1;

    public void displayProgressBar(){
        if(progress <= previousProgress){
            return;
        }
        if(progress >= 100){
            System.out.print("100/100% -- finished");
        }

        String bar = "|";
        for (int i = 0; i < 100; i++) {
            if(i<progress){
                bar += "=";
            }else{
                bar += " ";
            }
        }
        bar += "| " + progress + "/100%\r";

        previousProgress = progress;
        System.out.print(bar);
    }
}
