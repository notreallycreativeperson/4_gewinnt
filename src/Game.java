
import java.util.Scanner;
import java.util.Random;

public class Game {
    private final int[][] values={
            { 3,13,25,28,21,17},
            { 8,25,35,40,31,26},
            {15,34,40,45,37,30},
            {27,39,46,50,39,36},
            {15,34,40,45,37,30},
            { 8,25,35,40,31,26},
            { 3,13,25,28,21,17}

    };
    int[][]bord = new int[7][6];
    int player;
    int mode = getMode();
    boolean[][][] directions=new boolean[7][6][4];
    int bestEval=0;
    int bestMove;
    int bestEvalThisIteration;
    int bestMoveThisIteration;
    public Game(){
        setDirections();
        player = setPlayer(0);//2 = kreis beginnt, 3 heißt zufälliger Spieler beginnt, default kreuz gebinnt


    }

    public void startGame(){game(mode);}

    private void game(int mode) {
        boolean gameContinues = true;
        while (gameContinues) {


            int row = 10;
            int move = 10;
            while (row == 10) {
                move = getMove(player);
                row = getRow(move); //todo: modi implementieren
            }
            bord[move][row] = player;
            if (is_won(bord)) {
                displayBord(bord);
                System.out.println("Spieler " + (player == -1 ? 2 : 1) + " hat gewonnen!");

                break;
            }
            if (mode == 2 && player == 1) {
                switchPlayer();
                int aiMove = bord[bestMove][getRow(bestMove)];
                int aiRow = getRow(aiMove);
                bord[aiMove][aiRow] = player;
                if (is_won(bord)) {
                    displayBord(bord);
                    System.out.println("Spieler " + (player == -1 ? 2 : 1) + " hat gewonnen!");

                    break;
                }
            }


            displayBord(bord);

            switchPlayer();


            gameContinues = bordNotFinished(bord);
        }
    }

    private int getMode(){
        int mode;
        System.out.println("In welchem Modus möchtest du spielen?");
        System.out.println("Mensch -> 1 | Computer ->2");
        Scanner scnanner = new Scanner(System.in);
        mode= Integer.parseInt(scnanner.next());
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
    public boolean is_won(int[][] gameBord){ //can be optimized through cutting out certain areas

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if(gameBord[i][j]!=0){
                    if (directions[i][j][0]){
                        if ((gameBord[i][j]==gameBord[i+1][j])&&(gameBord[i][j]==gameBord[i+2][j])&&(gameBord[i][j]==gameBord[i+3][j])){
                            return true;
                        }
                    }

                    if (directions[i][j][1]){
                        if ((gameBord[i][j]==gameBord[i+1][j+1])&&(gameBord[i][j]==gameBord[i+2][j+2])&&(gameBord[i][j]==gameBord[i+3][j+3])){
                            return true;
                        }
                    }

                    if (directions[i][j][2]){
                        if ((gameBord[i][j]==gameBord[i][j+1])&&(gameBord[i][j]==gameBord[i][j+2])&&(gameBord[i][j]==gameBord[i][j+3])){
                            return true;
                        }
                    }

                    if (directions[i][j][3]){
                        if ((gameBord[i][j]==gameBord[i-1][j+1])&&(gameBord[i][j]==gameBord[i-2][j+2])&&(gameBord[i][j]==gameBord[i-3][j+3])){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean[] getDirections(int i, int j){
        return directions[i][j];
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

    public int getRow(int move){
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





    public int evaluate(int mode,int[][] gameBord){
        boolean max=(player==1);
        int evaluation;
        if(is_won(gameBord))return 1000*(max?-1:1);

        evaluation=evalField(gameBord)+evalPosition(gameBord);

        return evaluation;
    }
    private int evalField(int[][] bord){
        int value=0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if(bord[i][j]==0)break;
                value=value+(values[i][j]*bord[i][j]);

            }
        }

        return value;
    }
    private int evalPosition(int[][] bord){
        int eval_position=0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (bord[i][j]==0)break;
                boolean[] directions=getDirections(i,j);
                if(directions[0]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i+k][j]){
                            tempEval=tempEval*4;
                        }
                        eval_position=eval_position+tempEval;
                    }
                }
                if(directions[1]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i+k][j+k]){
                            tempEval=tempEval*4;
                        }
                        eval_position=eval_position+tempEval;
                    }
                }
                if(directions[2]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i][j+k]){
                            tempEval=tempEval*4;
                        }
                        eval_position=eval_position+tempEval;
                    }
                }
                if(directions[3]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i-k][j+k]){
                            tempEval=tempEval*4;
                        }
                        eval_position=eval_position+tempEval;
                    }
                }
            }
        }
        return eval_position;
    }

    private int search(int[][] gameBord,int depth,int alpha,int beta){
        if (depth==0)return evaluate(0,gameBord);

        for (int i = 0; i < 7; i++) {
            if (gameBord[i][5]!=0)break;
            switchPlayer();
            gameBord[i][getRow(i)]=player;
            int evaluation= search(gameBord,depth-1,-alpha,-beta);
            if (evaluation<=beta)return beta;
            if(evaluation>alpha)alpha=evaluation;
        }
        return alpha;
    }

    public void switchPlayer(){
        player= player == 1 ? -1 : 1;
    }

    public void startSearch(int depth){
        bestEvalThisIteration=bestEval=bestMoveThisIteration=bestMove=0;
        
    }
}