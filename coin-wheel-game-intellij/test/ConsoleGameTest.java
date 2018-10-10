import org.junit.Test;

import static org.junit.Assert.*;

public class ConsoleGameTest {
    @Test
    public void getWelcomeTest(){
        ConsoleGame cg = new ConsoleGame();
        String response = cg.getWelcome();
        assertEquals(response, "Welcome to Flip and Spin!");
    }

//    @Test
//    public void getStartChoiceTest(){
//        ConsoleGame cg = new ConsoleGame();
//
//    }

    @Test
    public void getGameIntructionsTest(){
        ConsoleGame cg = new ConsoleGame();
        String response = cg.getGameInstructions();
        assertEquals(response, "You will now be prompted to setup the game wheel!");
    }



}