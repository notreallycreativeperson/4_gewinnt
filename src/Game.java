
import java.util.Scanner;

import static java.lang.System.out;

public class Game {
    Minimax minimax;
    private int[][] gameBord = new int[7][6];
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
        displayBord(new int[7][6]);
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
                displayBord(bord);
                out.println("Spieler " + (player == -1 ? 2 : 1) + " hat gewonnen!");

                break;
            }
            if (mode == 2) {
                player = (player == 1 ? -1 : 1);
                int aiMove = minimax.minimax(bord, 6, player == 1);
                int aiRow = Main.getRow(bord, aiMove);
                bord[aiMove][aiRow] = player;
                if (Main.is_won(bord)) {
                    displayBord(bord);
                    out.println("Spieler " + (player == -1 ? 2 : 1) + " hat gewonnen!");

                    break;
                }
            }


            displayBord(bord);

            player = (player == 1 ? -1 : 1);
            setGameBord(bord);


            gameContinues = bordNotFinished(bord);
        }
    }

    private int getMode() {

        out.println("In welchem Modus möchtest du spielen?");
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

    private boolean bordNotFinished(int[][] bord) {
        for (int i = 0; i < 7; i++) {
            if (bord[i][5] == 0) return true;
        }

        return false;
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


    private void displayBord(int[][] bord) {
        for (int i = 1; i < 8; i++) {
            out.print(" " + i + "  ");
        }
        out.println();
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                out.print("[");
                switch (bord[j][i]) {
                    case 1: {
                        out.print("x");
                        break;
                    }
                    case -1: {
                        out.print("o");
                        break;
                    }
                    default:
                        out.print(" ");
                }
                out.print("] ");
            }
            out.println();
        }
    }
}
