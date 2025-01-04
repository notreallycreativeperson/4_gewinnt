public class Minimax {
    Evaluation evaluation;
    Minimax(){
        evaluation = new Evaluation();
    }

    public int minimax(int[][] bord,int depth,boolean max){
        int n = (max?1:-1);
        int bestScore =-10000*n;
        int bestMove=-1;
        int score;

        if(max){
            for (int i = 0; i < 7; i++) {
                if (bord[i][5]==0){
                    int row= Main.getRow(bord,i);
                    bord[i][row]=1;
                    score= minimaxrec(bord,depth-1,-10000,10000,false);
                    bord[i][row]=0;
                    if(score>bestScore){
                        bestScore=score;
                        bestMove=i;
                    }
                }
            }

        }else {
            for (int i = 0; i < 7; i++) {
                if (bord[i][5]==0){
                    int row= Main.getRow(bord,i);
                    bord[i][row]=-1;
                    score= minimaxrec(bord,depth-1,-10000,10000,true);
                    bord[i][row]=0;
                    if(score<bestScore){
                        bestScore=score;
                        bestMove=i;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimaxrec(int[][]bord,int depth,int alpha,int beta, boolean max){
        if (depth==0||Main.is_won(bord)) {
            return evaluation.evaluate(bord,1,max);
        }
        int n = (max?1:-1);
        int bestScore =-10000*n;
        int score;

        if(max){
            for (int i = 0; i < 7; i++) {
                if (bord[i][5]==0){
                    int row= Main.getRow(bord,i);
                    bord[i][row]=1;
                    score= minimaxrec(bord,depth-1,-10000,10000,false);
                    bord[i][row]=0;
                    if(score>bestScore){
                        bestScore=score;
                    }
                }
            }

        }else {
            for (int i = 0; i < 7; i++) {
                if (bord[i][5]==0){
                    int row= Main.getRow(bord,i);
                    bord[i][row]=-1;
                    score= minimaxrec(bord,depth-1,-10000,10000,true);
                    bord[i][row]=0;
                    if(score<bestScore){
                        bestScore=score;
                    }
                }
            }
        }


        return bestScore;
    }
}
