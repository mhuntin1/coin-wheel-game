import java.util.ArrayList;
import java.util.List;

/**
 * A player that can play the game
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */
public class Player implements StrategicPlayer {

    private int _coinsPerWheel;
    private int _revealsPerSpin;
    private int _maxNumSpins;

    private int _turnNum;
    private boolean _isFourTwoGame;

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

        _coinsPerWheel = coinsPerWheel;
        _revealsPerSpin = revealsPerSpin;
        _maxNumSpins = maxNumSpins;
        _turnNum = 0;

        if (coinsPerWheel == 4 && revealsPerSpin == 2) {
            _isFourTwoGame = true;
        } else {
            _isFourTwoGame = false;
        }
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
        CharSequence resp;
        if (_isFourTwoGame)
            resp = getSlotsToRevealFourTwo(_turnNum);
        else
            resp = getSlotsToRevealGeneral();

        return resp.toString();
    }

    protected CharSequence getSlotsToRevealGeneral() {
        int numReveals = Math.min(_revealsPerSpin, _coinsPerWheel);
        int numUnseen = _coinsPerWheel - numReveals;

        StringBuilder sb = new StringBuilder(_coinsPerWheel);

        for (int i = 0; i < numReveals; i++) {
            sb.append("?");
        }

        for (int i = 0; i < numUnseen; i++) {
            sb.append("-");
        }

        return sb.toString();
    }

    protected CharSequence getSlotsToRevealFourTwo(int turnNum) {
        List<String> reveals = new ArrayList<>();
        reveals.add("-?-?");
        reveals.add("--??");
        reveals.add("?-?-");
        reveals.add("-??-");
        reveals.add("?-?-");

        return reveals.get(turnNum);
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
        CharSequence resp;
        if (_isFourTwoGame)
            resp = getNewCoinStatesFourTwo(revealedPattern, _turnNum);
        else
            resp = getNewCoinStatesGeneral(revealedPattern);

        _turnNum++;

        return resp.toString();
    }

    protected CharSequence getNewCoinStatesGeneral(CharSequence revealedPattern) {
        char heads = 'H', tails = 'T';
        int numHeads = getCharCount(revealedPattern, heads);

        char flipFrom = (numHeads > _revealsPerSpin / 2) ? tails : heads;
        char flipTo = (flipFrom == heads) ? tails : heads;

        CharSequence outSeq = revealedPattern
                .toString()
                .toUpperCase()
                .replace(flipFrom, flipTo);

        _turnNum++;

        return outSeq;
    }

    protected CharSequence getNewCoinStatesFourTwo(
            CharSequence revealedPattern,
            int turnNum) {
        String pattern = revealedPattern.toString();

        String resp = "";
        if (turnNum < 2) {
            resp = pattern.replace("T", "H");
        } else if (turnNum == 2) {
            if (pattern.contains("T")) {
                resp = pattern.replace("T", "H"); // Win!
            } else {
                resp = pattern.replaceFirst("H", "T");
            }
        } else if (turnNum == 3) {
            int numHeads = getCharCount(pattern, 'H');
            if (numHeads == 0) {
                resp = pattern.replace("T", "H"); // Win!
            } else if (numHeads == 2) {
                resp = pattern.replace("H", "T"); // Win!
            } else {
                resp = flipAll(pattern).toString();
            }
        } else if (turnNum == 4) {
            resp = flipAll(pattern).toString(); // Win!
        }

        return resp;
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

    /**
     * Flips the state of each visible coin. H -> T and T -> H
     * @param charSeq The sequence of states
     * @return The sequence of states with each state flipped
     */
    private CharSequence flipAll(CharSequence charSeq) {
        String seq = charSeq.toString().toUpperCase();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seq.length(); i++) {
            char c = seq.charAt(i);

            if (c == '-') {
                sb.append(c);
            } else if (c == 'H') {
                sb.append('T');
            } else {
                sb.append('H');
            }
        }
        return sb.toString();
    }


}
