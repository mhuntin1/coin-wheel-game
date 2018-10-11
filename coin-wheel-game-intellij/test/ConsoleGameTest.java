import org.junit.Test;

/**
 * A test class for ConsoleGame.java
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */

import static org.junit.Assert.*;

public class ConsoleGameTest {

    /**
     * Tests getWelcome in ConsoleGame.java
     *
     */
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

    /**
     * Tests getGameInstructions in ConsoleGame.java
     *
     */
    @Test
    public void getGameIntructionsTest(){
        ConsoleGame cg = new ConsoleGame();
        String response = cg.getGameInstructions();
        assertEquals(response, "You will now be prompted to setup the game wheel!");
    }



}