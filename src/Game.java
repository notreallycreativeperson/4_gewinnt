
import java.util.Scanner;

import static java.lang.System.out;

public class Game {
    Minimax minimax;
    private int[][] gameBord = Main.newBord();
    int player;
    //int mode = getMode();


    public Game() {
        Main.setDirections();
        player = setPlayer();//2 = kreis beginnt, 3 heißt zufälliger Spieler beginnt, default kreuz gebinnt
        minimax = new Minimax();

    }

    public void startGame() {
        game(2);
    }

    private void game(int mode) {
        boolean gameContinues = true;
        Main.displayBord(Main.newBord());
        while (gameContinues) {
            int[][] bord;
            bord = getGameBord();
            int row = 10;
            int move = 10;
            while (row == 10) {
                move = getMove(player);
                row = Main.getRow(bord, move); //todo: modi implementieren
            }
            bord[move][row] = player;
            if (Main.is_won(bord)) {
                Main.displayBord(bord);
                out.println("Spieler " + (player == -1 ? 2 : 1) + " hat gewonnen!");

                break;
            }
            if (mode == 2) {
                player = (player == 1 ? -1 : 1);
                int aiMove = minimax.miniMax(bord, 6,-100000,100000, player == 1,false);
                int aiRow = Main.getRow(bord, aiMove);
                bord[aiMove][aiRow] = player;
                if (Main.is_won(bord)) {
                    Main.displayBord(bord);
                    out.println("Spieler " + (player == -1 ? 2 : 1) + " hat gewonnen!");

                    break;
                }
            }


            Main.displayBord(bord);

            player = (player == 1 ? -1 : 1);
            setGameBord(bord);


            gameContinues = Main.bordNotFinished(bord);
        }
    }

    private int getMode() {

        out.println("In welchem Modus möchtest du spielen?"); //todo Modi implementieren
        out.println("Mensch -> 1 | Computer ->2");
        Scanner scnanner = new Scanner(System.in);
        return Integer.parseInt(scnanner.next());
    }



    private int setPlayer() {
        return 1;
    }

    private int[][] getGameBord() {
        return gameBord;
    }

    private void setGameBord(int[][] gameBord) {
        this.gameBord = gameBord;
    }



    private int getMove(int player) {
        out.println("Spieler " + (player == -1 ? 2 : player) + " ist am Zug. ");
        out.println("Bitte gib eine Spaltennummer von 1 bis 7.");
        try {
            Scanner scanner = new Scanner(System.in);
            int move = Integer.parseInt(scanner.next());
            if (move > 7 || move < 1) {
                out.println("Bitte gib eine Zulässige Zahl ein.");
                return 10;
            } else {
                return move - 1;
            }
        } catch (Exception e) {
            out.println("Bitte gib eine Zulässige Zahl ein.");
            return 10;
        }
    }

}
