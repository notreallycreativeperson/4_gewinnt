

public class Main {
    public static void main(String args[]){
        Game game= new Game();
    }
    public static boolean is_won(int[][] bord){

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if(bord[i][j]!=0){
                    if (Game.directions[i][j][0]){
                        if ((bord[i][j]==bord[i+1][j])&&(bord[i][j]==bord[i+2][j])&&(bord[i][j]==bord[i+3][j])){
                            return true;
                        }
                    }
                    if (Game.directions[i][j][1]){
                        if ((bord[i][j]==bord[i+1][j+1])&&(bord[i][j]==bord[i+2][j+2])&&(bord[i][j]==bord[i+3][j+3])){
                            return true;
                        }
                    }
                    if (Game.directions[i][j][2]){
                        if ((bord[i][j]==bord[i][j+1])&&(bord[i][j]==bord[i][j+2])&&(bord[i][j]==bord[i][j+3])){
                            return true;
                        }
                    }
                    if (Game.directions[i][j][3]){
                        if ((bord[i][j]==bord[i-1][j+1])&&(bord[i][j]==bord[i-2][j+2])&&(bord[i][j]==bord[i-3][j+3])){
                            return true;
                        }
                    }
                }

            }
        }

        return false;
    }
}

