/**
 * A representation of the wheel.
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */
public class Wheel {

    private String _wheelStr;
    private int _coinsPerWheel;
    private int _revealsPerSpin;

    private String heads = "H";
    private String tails = "T";
    private char query = '?';

    /**
     * Construct an initialized wheel.
     * @param coinsPerWheel Number of coins the wheel has
     * @param revealsPerSpin Max number of coins that will be shown
     */
    public Wheel(int coinsPerWheel, int revealsPerSpin) {
        _coinsPerWheel = coinsPerWheel;
        _revealsPerSpin = Math.min(revealsPerSpin, coinsPerWheel);
        initializeRandomWheel(_coinsPerWheel);
    }

    /**
     * Initializes the wheel in a random state
     */
    private void initializeRandomWheel(int coinsPerWheel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coinsPerWheel; i++) {
            if (Math.random() < 0.5) {
                sb.append(heads);
            } else {
                sb.append(tails);
            }
        }
        _wheelStr = sb.toString();
    }

    /**
     * Get the revealed pattern of the wheel by keeping the "?"
     * and changing everything else to "-".
     */
    protected CharSequence getRevealedPattern(CharSequence reveal) {
        StringBuilder sb = new StringBuilder();

        int numShown = 0;
        for (int i = 0; i < reveal.length(); i++) {
            if (reveal.charAt(i) == query && numShown < _revealsPerSpin) {
                sb.append(_wheelStr.charAt(i));
                numShown++;
            } else {
                sb.append('-');
            }
        }
        return sb.toString();
    }

    /**
     * Sets new coin states for values in the string which are "-".
     *
     * @param newStates
     */
    protected void setNewStates(CharSequence newStates) {
        StringBuilder sb = new StringBuilder();

        int numSet = 0;
        for (int i = 0; i < _wheelStr.length(); i++) {
            if (newStates.charAt(i) != '-' && numSet < _revealsPerSpin) {
                sb.append(newStates.charAt(i));
                numSet++;
            } else {
                sb.append(_wheelStr.charAt(i));
            }
        }
        _wheelStr = sb.toString();
    }

    /**
     * A method which spins wheel by randomizing string elements.
     */
    protected void spinWheel() {
        int wheelLength = _wheelStr.length();
        int copyIndex = (int) (Math.random() * wheelLength);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wheelLength; i++) {
            sb.append(_wheelStr.charAt(copyIndex));
            copyIndex = (copyIndex + 1) % wheelLength;
        }
    }

    /**
     * Checks if the user won the game.
     */
    protected boolean didWin() {
        for (int i = 1; i < _wheelStr.length(); i++) {
            if (_wheelStr.charAt(i - 1) != _wheelStr.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
