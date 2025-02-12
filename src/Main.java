import static java.lang.System.out;

public class Main {
    static boolean[][][] directions = new boolean[7][6][4];

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

    }

    public static boolean is_won(int[][] bord) { //can be optimized through cutting out certain areas

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (bord[i][j] != 0) {
                    if (directions[i][j][0]) {
                        if ((bord[i][j] == bord[i + 1][j]) && (bord[i][j] == bord[i + 2][j]) && (bord[i][j] == bord[i + 3][j])) {
                            return true;
                        }
                    }
                    if (directions[i][j][1]) {
                        if ((bord[i][j] == bord[i + 1][j + 1]) && (bord[i][j] == bord[i + 2][j + 2]) && (bord[i][j] == bord[i + 3][j + 3])) {
                            return true;
                        }
                    }
                    if (directions[i][j][2]) {
                        if ((bord[i][j] == bord[i][j + 1]) && (bord[i][j] == bord[i][j + 2]) && (bord[i][j] == bord[i][j + 3])) {
                            return true;
                        }
                    }
                    if (directions[i][j][3]) {
                        if ((bord[i][j] == bord[i - 1][j + 1]) && (bord[i][j] == bord[i - 2][j + 2]) && (bord[i][j] == bord[i - 3][j + 3])) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    public static boolean[] getDirections(int i, int j) {
        return directions[i][j];
    }

    protected static void setDirections() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                directions[i][j][0] = i < 4;
                directions[i][j][1] = (i < 4 && j < 3);
                directions[i][j][2] = j < 3;
                directions[i][j][3] = (i > 2 && j < 3);
            }
        }
    }

    public static int getRow(int[][] bord, int move) {
        if (move == 10) return 10;
        int row = 10;
        for (int i = 0; i < 6; i++) {
            if (bord[move][i] == 0) {
                row = i;
                break;
            }
        }
        if (row == 10) {
            out.println("Hier ist leider schon alles Voll.");
        }
        return row;


    }

    public static int[][] toBord(int[] moves){
        int[][] bord = new int[7][6];
        int player=1;
        for (int move : moves) {
            int row = getRow(bord, move);
            bord[move][row] = player;
            player = (player == 1 ? -1 : 1);
        }
        return bord;
    }


    public static void displayBord(int[][] bord) {
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

    public static int[][] newBord() {
        int[][] bord = new int[7][7];
        bord[0][6]=-1;
        bord[1][6]=-1;
        bord[2][6]=-1;
        bord[6][6]=0;


        return bord;
    }

    public static int[][] move(int[][] bord,int move){
        int row=getRow(bord,move);
        bord[2][6]=bord[1][6];
        bord[1][6]=bord[0][6];
        bord[0][6]=move*row;
        bord[move][row]=getPLayer(bord[6][6]);
        bord[6][6]+=1;
        return bord;
    }

    public static int getPLayer(int moves){
        return (moves%2==0?1:-1);
    }

    public static boolean bordNotFinished(int[][] bord) {
        for (int i = 0; i < 7; i++) {
            if (bord[i][5] == 0) return true;
        }

        return false;
    }

}

