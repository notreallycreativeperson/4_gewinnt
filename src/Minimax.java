public class Minimax {
    Evaluation evaluation;

    Minimax() {
        evaluation = new Evaluation();
    }

    public int miniMax(int[][] bord, int depth, int alpha, int beta,boolean max, boolean isScore) {
        if(depth==0||Main.is_won(bord)){
            return evaluation.evaluate(bord,max);
        }


        int bestScore;
        int bestMove = -1;



        if (max) {
            bestScore = -1000000;
            for (int i = 0; i < 7; i++) {
                if (bord[i][5] == 0) {
                    int row = Main.getRow(bord, i);
                    bord[i][row] = 1;
                    int score = miniMax(bord, depth - 1,alpha,beta, false, true);
                    bord[i][row] = 0;
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = i;
                    }
                    alpha=Math.max(alpha,score);
                    if (beta<= alpha){
                        break;
                    }
                }
            }

        } else {
            bestScore = 1000000;
            for (int i = 0; i < 7; i++) {
                if (bord[i][5] == 0) {
                    int row = Main.getRow(bord, i);
                    bord[i][row] = -1;
                    int score = miniMax(bord, depth - 1,alpha,beta, true, true);
                    bord[i][row] = 0;
                    if (score < bestScore) {
                        bestScore = score;
                        bestMove = i;
                    }
                    beta= Math.min(beta,score);
                    if (beta<=alpha){
                        break;
                    }
                }
            }
        }


        return (isScore?bestScore:bestMove);
    }





}
