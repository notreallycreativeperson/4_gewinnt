public class Minimax {
    Evaluation evaluation;

    Minimax() {
        evaluation = new Evaluation();
    }

    public int minimax(int[][] bord, int depth, boolean max) {
        int bestScore;
        int bestMove = -1;


        if (max) {
            bestScore = -1000000;
            for (int i = 0; i < 7; i++) {
                if (bord[i][5] == 0) {
                    int row = Main.getRow(bord, i);
                    bord[i][row] = 1;
                    int score = miniMaxRec(bord, depth - 1, false);
                    bord[i][row] = 0;
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = i;
                    }
                }
            }

        } else {
            bestScore = 1000000;
            for (int i = 0; i < 7; i++) {
                if (bord[i][5] == 0) {
                    int row = Main.getRow(bord, i);
                    bord[i][row] = -1;
                    int score = miniMaxRec(bord, depth - 1, true);
                    bord[i][row] = 0;
                    if (score < bestScore) {
                        bestScore = score;
                        bestMove = i;
                    }
                }
            }
        }
        return bestMove;


    }

    private int miniMaxRec(int[][] bord, int depth, boolean max) {
        if (depth == 0 || Main.is_won(bord)) {
            return evaluation.evaluate(bord, max);
        }
        int bestScore;


        if (max) {
            bestScore = -1000000;
            for (int i = 0; i < 7; i++) {
                if (bord[i][5] == 0) {
                    int row = Main.getRow(bord, i);
                    bord[i][row] = 1;
                    int score = miniMaxRec(bord, depth - 1, false);
                    bord[i][row] = 0;
                    if (score > bestScore) {
                        bestScore = score;
                    }
                }
            }

        } else {
            bestScore = 1000000;
            for (int i = 0; i < 7; i++) {
                if (bord[i][5] == 0) {
                    int row = Main.getRow(bord, i);
                    bord[i][row] = -1;
                    int score = miniMaxRec(bord, depth - 1, true);
                    bord[i][row] = 0;
                    if (score < bestScore) {
                        bestScore = score;
                    }
                }
            }
        }
        return bestScore;


    }
}
