

public class Main {
    static boolean[][][] directions=new boolean[7][6][4];
    public static void main(String args[]){
        Game game= new Game();
        game.startGame();

    }
    public static boolean is_won(int[][] bord){ //can be optimized through cutting out certain areas

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if(bord[i][j]!=0){
                    if (directions[i][j][0]){
                        for (int k = 1; k < 4; k++) {
                            if (bord[i][j]!=bord[i+k][j]) {
                                break;
                            }
                        }

                        return true;
                    }
                    if (directions[i][j][1]){
                        for (int k = 1; k < 4; k++) {
                            if (bord[i][j]!=bord[i+k][j+k]){
                                break;
                            }
                        }

                        return true;
                    }
                    if (directions[i][j][2]){
                        for (int k = 1; k < 4; k++) {
                            if (bord[i][j]!=bord[i][j+k]) {
                                break;
                            }
                        }
                            return true;
                    }
                    if (directions[i][j][3]){
                        for (int k = 1; k < 4; k++) {
                            if (bord[i][j]!=bord[i-k][j+k]){
                                break;
                            }

                        }
                        return true;
                    }

                }
            }

        }
        return false;
    }

    public static boolean[] getDirections(int i, int j){
        return directions[i][j];
    }

    protected static void setDirections(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                directions[i][j][0]=i<4;
                directions[i][j][1]=(i<4&&j<3);
                directions[i][j][2]=j<3;
                directions[i][j][3]=(i>2&&j<3);
            }
        }
    }

    public static int getRow(int[][] bord,int move){
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


}

