import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * A console interface to play the game
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */
public class ConsoleGame {
    private int PLAY = 1;
    private int QUIT = 2;

    public ConsoleGame() {
    }

    /**
     * Start a console game
     */
    public void start() {
        System.out.println(this.getWelcome());

        List<CharSequence> userOpts = new ArrayList<>();
        userOpts.add("1. Play the game");
        userOpts.add("2. Quit");
        int choice = this.getStartChoices(userOpts);

        if (choice == PLAY){
            System.out.println(this.getGameInstructions());
        }
        else if (choice == QUIT){
            System.out.println("Thanks for playing!");
        }
    }

    /**
     * Return welcome message to user
     *
     * @return The welcome message
     */
    public String getWelcome() {
        return "Welcome to Flip and Spin!";
    }

    /**
     * Display options to a user and return a valid user input
     *
     * @param options A list of choices to offer the user
     * @return The choice of the user
     */
    public int getStartChoices(List<CharSequence> options) {
        int option = -1;
        while (option < 1 || option > options.size()) {
            System.out.println("Please enter a displayed option number:");
            for (CharSequence cs : options) {
                System.out.println(cs);
            }

            System.out.print("> ");
            Scanner consoleIn = new Scanner(System.in);
            try {
                option = consoleIn.nextInt();
            } catch (InputMismatchException e) {
                String errStr = "***ERROR: Please enter an integer for a " +
                        "displayed option";
                System.out.println(errStr);
            }
        }
        return option;
    }

    /**
     * Print starting information for the user
     *
     * @return An informative string to help the user start the game
     */
    public String getGameInstructions() {
        return "You will now be prompted to setup the game wheel!";
    }
}