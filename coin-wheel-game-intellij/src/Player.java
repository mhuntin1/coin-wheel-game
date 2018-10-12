/**
 * A player that can play the game
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */
public class Player implements StrategicPlayer {

    private int _coinsPerWheel;
    private int _revealsPerSpin;

    /**
     * Default constructor
     */
    public Player() {

    }

    /**
     * Establishes that the player is beginning a new game.
     *
     * @param coinsPerWheel  the number of coins in the wheel
     * @param revealsPerSpin the number of coins revealed per turn/spin
     * @param maxNumSpins    the maximum number of spin allowed for the game
     */
    public void beginGame(int coinsPerWheel, int revealsPerSpin, int maxNumSpins) {

    }

    /**
     * Provides the request pattern for the current turn.
     * The returned pattern is of proper length, contains only
     * '?' and '-', and has exactly the number of '?'
     * as the permitted reveals per spin.
     *
     * @return a proper reveal-pattern consisting of '-' and '?' with
     * exactly the number of '?' as permitted by reveals-per-spin
     */
    public CharSequence getSlotsToReveal() {
        int numReveals = Math.min(_revealsPerSpin, _coinsPerWheel);
        int numUnseen = _coinsPerWheel - numReveals;

        StringBuilder sb = new StringBuilder(_coinsPerWheel);

        for (int i = 0; i < numReveals; i++) {
            sb.append("?");
        }

        for (int i = 0; i < numUnseen; i++) {
            sb.append("-");
        }

        return sb;
    }

    /**
     * Provides the coin-state set pattern for the current turn.
     * The returned pattern is a copy of the parameter
     * in which all '-' are preserved and each location of
     * 'H' and 'T' may be replaced by either 'H' or 'T'.
     *
     * @param revealedPattern a proper-length pattern
     *                        consisting of '-', 'H', and 'T'
     * @return a proper set-pattern consisting of '-', 'H', and 'T'
     */
    public CharSequence getNewCoinStates(CharSequence revealedPattern) {
        char heads = 'H', tails = 'T';
        int numHeads = getCharCount(revealedPattern, heads);

        char flipFrom = (numHeads >= _revealsPerSpin / 2) ? tails : heads;
        char flipTo = (flipFrom == heads) ? tails : heads;

        CharSequence outSeq = revealedPattern
                .toString()
                .toUpperCase()
                .replace(flipFrom, flipTo);

        return outSeq;
    }


    /**
     * Counts the number of lower and upper case instances of token
     * in the char sequence
     *
     * @param charSeq     The sequence to consider
     * @param charToCount The character to count occurrences of
     * @return The number of times that upper or lower case charToCount
     * occurs in charSeq
     */
    private int getCharCount(CharSequence charSeq, char charToCount) {
        int headsCount = (int) charSeq
                .chars()
                .map(Character::toUpperCase)
                .filter(c -> c == charToCount)
                .count();

        return headsCount;
    }
}
