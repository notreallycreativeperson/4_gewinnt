
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameTest {
    Game testGame = new Game();
    int [][] bord = new int[][]{
            {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}

    };
    @Test
    void GameWonTest(){

        Assertions.assertTrue(Main.is_won(bord));
    }

    @Test
    void bordFinishedTest(){
        Assertions.assertFalse(Main.bordNotFinished(bord));
    }

    @Test
    void displayBordTest(){
        
    }

}
