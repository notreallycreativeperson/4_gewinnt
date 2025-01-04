
import java.util.Scanner;
import java.util.Random;

public class Game {
    Minimax minimax;
    private int[][]gameBord = new int[7][6];
    int player;
    int mode = getMode();


    public Game(){
        Main.setDirections();
        player = setPlayer(0);//2 = kreis beginnt, 3 heißt zufälliger Spieler beginnt, default kreuz gebinnt
        minimax = new Minimax();
        game(mode);
    }

    private void game(int mode){
        boolean gameContinues = true;
        while(gameContinues){
            int[][] bord;
            bord = getGameBord();
            int row=10;
            int move=10;
            while (row==10){
                move=getMove(player);
                row = Main.getRow(bord,move); //todo: modi implementieren
            }
            bord[move][row]=player;
            if (Main.is_won(bord)){
                displayBord(bord);
                System.out.println("Spieler "+(player==-1?2:1)+" hat gewonnen!");

                break;
            }
            if (mode==2&&player==1){
                player=-1;
                int aiMove = minimax.minimax(bord,5,false);
                int aiRow=Main.getRow(bord,aiMove);
                bord[aiMove][aiRow]=player;
                if (Main.is_won(bord)){
                    displayBord(bord);
                    System.out.println("Spieler "+(player==-1?2:1)+" hat gewonnen!");

                    break;
                }
            }



            displayBord(bord);

            player=(player==1?-1:1);
            setGameBord(bord);




            gameContinues=bordNotFinished(bord);
        }
    }

    private int getMode(){
        int mode =0;
        System.out.println("In welchem Modus möchtest du spielen?");
        System.out.println("Mensch -> 1 | Computer ->2");
        Scanner scnanner = new Scanner(System.in);
        int m=Integer.parseInt(scnanner.next());
        mode=m;
        return mode;
    }

    private int setPlayer(int mode){
        int player;
        switch (mode){
            case 2:
                player=-1;
            case 3:{
                Random rand= new Random();
                if (rand.nextInt(2)==0) player = 1;
                else player = -1;
            }
            default:{
                player =1;
            }
        }
        return player;
    }

    private int[][] getGameBord() {
        return gameBord;
    }

    private void setGameBord(int[][] gameBord) {this.gameBord=gameBord;}

    private boolean bordNotFinished(int[][] bord){
        for (int i = 0; i < 7; i++) {
            if (bord[i][5]==0)return true;
        }

        return false;
    }

    private int getMove(int player){
        System.out.println("Spieler "+(player==-1?2:player)+" ist am Zug.  " +"Bitte gib eine Spaltennummer von 1 bis 7.");
        try{
            Scanner scanner = new Scanner(System.in);
            int move=Integer.parseInt(scanner.next());
            if(move>7||move<1){
                System.out.println("Bitte gib eine Zulässige Zahl ein.");
                return 10;
            }else{
                return move-1;
            }
        }catch (Exception e){
            System.out.println("Bitte gib eine Zulässige Zahl ein.");
            return 10;
        }
    }

    private void displayBord(int[][] bord){
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                System.out.print("(");
                switch (bord[j][i]){
                    case 1: {
                        System.out.print("x");
                        break;
                    }
                    case -1: {
                        System.out.print("o");
                        break;
                    }
                    default: System.out.print(" ");
                }
                System.out.print(") ");
            }
            System.out.println();
        }
    }
}
