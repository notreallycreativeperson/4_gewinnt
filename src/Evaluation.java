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
        if(Main.is_won(bord))return 1000*(max?1:-1);

        evaluation=eval_positions(bord);

        return evaluation;
    }
    private int eval_positions(int[][] bord){
        int value=0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                value=value+(values[i][j]*bord[i][j]);
            }
        }

        return value;
    }



}
