public class Evaluation {



    private final int[][] values={
            { 3,13,25,28,21,17},
            { 8,25,35,40,31,26},
            {15,34,40,45,37,30},
            {27,39,46,50,39,36},
            {15,34,40,45,37,30},
            { 8,25,35,40,31,26},
            { 3,13,25,28,21,17}

    };

    Evaluation(){

    }

    public int evaluate(int[][] bord,int mode, boolean max){
        int evaluation=0;
        if(Main.is_won(bord))return 1000*(max?-1:1);

        evaluation=evalField(bord)+evalPosition(bord);

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
                boolean[] directions=Main.getDirections(i,j);
                if(directions[0]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i+k][j]){
                            tempEval=tempEval*4;
                        };
                        eval_position=eval_position+tempEval;
                    }
                }
                if(directions[1]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i+k][j+k]){
                            tempEval=tempEval*4;
                        };
                        eval_position=eval_position+tempEval;
                    }
                }
                if(directions[2]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i][j+k]){
                            tempEval=tempEval*4;
                        };
                        eval_position=eval_position+tempEval;
                    }
                }
                if(directions[3]){
                    int tempEval=bord[i][j];
                    for (int k = 1; k < 4; k++) {
                        if(bord[i][j]==bord[i-k][j+k]){
                            tempEval=tempEval*4;
                        };
                        eval_position=eval_position+tempEval;
                    }
                }
            }
        }
        return eval_position;
    }
}
