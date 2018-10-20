import java.util.ArrayList;
import java.util.List;

/**
 * A player that can play the game
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.2
 */
public class Player implements StrategicPlayer {

    private String _heads = "H";
    private String _tails = "T";
    private char _hidden = '-';

    private int _coinsPerWheel;
    private int _revealsPerSpin;
    private int _maxNumSpins;

    private int _turnNum;
    private boolean _isFourTwoGame;
    private List<String> _fourTwoReveals = makeFourTwoRevealPatterns();

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
        _revealsPerSpin = Math.min(revealsPerSpin, coinsPerWheel);
        _maxNumSpins = maxNumSpins;
        _turnNum = 0;

        if (coinsPerWheel == 4 && revealsPerSpin == 2) {
            _isFourTwoGame = true;
        } else {
            _isFourTwoGame = false;
        }
    }

    /**
     * Checks whether 4/2 strategy game is being called or general game is being called.  
     *
     * @return a proper reveal-pattern consisting of '-' and '?' with
     * exactly the number of '?' as permitted by reveals-per-spin in either 
     * strategy implementation or general implementation. 
     */
    public CharSequence getSlotsToReveal() {
        CharSequence resp;
        if (_isFourTwoGame) {
            resp = getSlotsToRevealFourTwo(_turnNum);
        } else {
            resp = getSlotsToRevealGeneral();
        }

        return resp.toString();
    }
     /**
     * Provides the request pattern for the current turn in a
     * general game where no strategy is implemented.
     * The returned pattern is of proper length, contains only
     * '?' and '-', and has exactly the number of '?'
     * as the permitted reveals per spin.
     *
     * @return a proper reveal-pattern consisting of '-' and '?' with
     * exactly the number of '?' as permitted by reveals-per-spin during the no strategy
     * general game play
     */

    /**
     * Requests to reveal the first n allowed consecutive positions.
     *
     * @return A string with the first n characters set to ?
     */
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
     /**
     * Provides the request pattern for the current turn in a 4 coin
     * 2 reveal strategy implementation.
     * The returned pattern is of proper length, contains only
     * '?' and '-', and has exactly the number of '?'
     * as the permitted reveals per spin.
     *
     * @return a proper reveal-pattern consisting of '-' and '?' with
     * exactly the number of '?' as permitted by reveals-per-spin during the 4 coin
     * 2 reveal strategy implementation.
     */

    /**
     * Follows the 4-2 game player play book. Asks consecutively for
     * predetermined revlead patterns that guarantee a win by the 5th turn.
     *
     * @param turnNum
     * @return
     */
    protected CharSequence getSlotsToRevealFourTwo(int turnNum) {
        String resp;
        if (turnNum < _fourTwoReveals.size()) {
            resp = _fourTwoReveals.get(turnNum);
        } else {
            resp = "??--";
        }

        return resp;
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
        if (_isFourTwoGame) {
            resp = getNewCoinStatesFourTwo(revealedPattern, _turnNum);
        } else {
            resp = getNewCoinStatesGeneral(revealedPattern);
        }
        _turnNum++;

        return resp.toString();
    }
    /**
     * Provides the coin-state set pattern for the current turn
     * in the general game where no strategy is implemented.
     * The returned pattern is a copy of the parameter
     * in which all '-' are preserved and each location of
     * 'H' and 'T' may be replaced by either 'H' or 'T'.
     *
     * @param revealedPattern a proper-length pattern
     *                        consisting of '-', 'H', and 'T'
     * @return a proper set-pattern consisting of '-', 'H', and 'T'
     */
    protected CharSequence getNewCoinStatesGeneral(CharSequence revealedPattern) {
        char heads = _heads.charAt(0), tails = _tails.charAt(0);
        int numHeads = Utility.getCharCount(revealedPattern, heads);

        char flipFrom = (numHeads > _revealsPerSpin / 2) ? tails : heads;
        char flipTo = (flipFrom == heads) ? tails : heads;

        CharSequence outSeq = revealedPattern
                .toString()
                .toUpperCase()
                .replace(flipFrom, flipTo);

        _turnNum++;

        return outSeq;
    }

    /**
     * Provides the coin-state set pattern for the current turn
     * in the 4 coin 2 reveal strategy implementation.
     * The returned pattern is a copy of the parameter
     * in which all '-' are preserved and each location of
     * 'H' and 'T' may be replaced by either 'H' or 'T'.
     *
     * @param revealedPattern a proper-length pattern
     *                        consisting of '-', 'H', and 'T'
     * @return a proper set-pattern consisting of '-', 'H', and 'T'
     */
    protected CharSequence getNewCoinStatesFourTwo(
            CharSequence revealedPattern,
            int turnNum) {
        String pattern = revealedPattern.toString();

        String resp;
        switch (turnNum) {
            case 0:
            case 1:
                resp = fourTwoFirstTwoTurnsResp(pattern);
                break;
            case 2:
                resp = fourTwoThirdTurnsResp(pattern);
                break;
            case 3:
                resp = fourTwoFourthTurnsResp(pattern);
                break;
            case 4:
                resp = fourTwoFifthTurnsResp(pattern);
                break;
            default:
                resp = getNewCoinStatesGeneral(pattern).toString();
        }
        return resp;
    }

    private String fourTwoFirstTwoTurnsResp(String pattern) {
        return pattern.replace(_tails, _heads);
    }

    private String fourTwoThirdTurnsResp(String pattern) {
        String resp;
        if (pattern.contains(_tails)) {
            resp = pattern.replace(_tails, _heads); // Win!
        } else {
            resp = pattern.replaceFirst(_heads, _tails);
        }

        return resp;
    }

    private String fourTwoFourthTurnsResp(String pattern) {
        String resp;
        int numHeads = Utility.getCharCount(pattern, _heads.charAt(0));
        if (numHeads == 0) {
            resp = pattern.replace(_tails, _heads); // Win!
        } else if (numHeads == 2) {
            resp = pattern.replace(_heads, _tails); // Win!
        } else {
            resp = flipAll(pattern).toString();
        }
        return resp;
    }

    private String fourTwoFifthTurnsResp(String pattern) {
        return flipAll(pattern).toString();
    }

    /**
     * Flips the state of each visible coin. H -> T and T -> H
     *
     * @param charSeq The sequence of states
     * @return The sequence of states with each state flipped
     */
    protected CharSequence flipAll(CharSequence charSeq) {
        String seq = charSeq.toString().toUpperCase();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seq.length(); i++) {
            char c = seq.charAt(i);

            if (c == _hidden) {
                sb.append(c);
            } else if (c == _heads.charAt(0)) {
                sb.append(_tails.charAt(0));
            } else {
                sb.append(_heads.charAt(0));
            }
        }
        return sb.toString();
    }

    /**
     * Returns the predetermined query strings for the 4-2 player
     * @return
     */
    private List<String> makeFourTwoRevealPatterns() {
        List<String> reveals = new ArrayList<>();
        reveals.add("-?-?");
        reveals.add("--??");
        reveals.add("?-?-");
        reveals.add("-??-");
        reveals.add("?-?-");

        return reveals;
    }

}
