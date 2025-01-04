
import java.util.Scanner;
import java.util.Random;

public class Game {

    private int[][]gameBord = new int[7][6];
    int player;
    static boolean[][][] directions=new boolean[7][6][4];
    public Game(){
        setDirections();
        player = setPlayer(0);//2 = kreis beginnt, 3 heißt zufälliger Spieler beginnt, default kreuz gebinnt
        game();
    }

    private void game(){
        boolean gameContinues = true;
        while(gameContinues){
            int[][] bord;
            bord = getGameBord();
            int row=10;
            int move=10;
            while (row==10){
                move=getMove(player);
                row = getRow(bord,move);
            }
            bord[move][row]=player;
            if (Main.is_won(bord)){
                displayBord(bord);
                System.out.println("Spieler "+(player==-1?2:1)+" hat gewonnen!");
                break;
            }
            player=(player==1?-1:1);
            displayBord(bord);
            setGameBord(bord);




            gameContinues=bordNotFinished(bord);
        }
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



        return true;
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

    private int getRow(int[][] bord,int move){
        if (move==10)return 10;
        int row=10;
        for (int i = 0; i < 6; i++) {
            if (bord[move][i]==0){
                row=i;
                break;
            }
        }
        if (row==10){
            System.out.println("Hier ist leider schon alles Voll.");
        }
        return row;


    }

    private void setDirections(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                directions[i][j][0]=i<4;
                directions[i][j][1]=(i<4&&j<3);
                directions[i][j][2]=j<3;
                directions[i][j][3]=(i>2&&j<3);
            }
        }
    }



}
