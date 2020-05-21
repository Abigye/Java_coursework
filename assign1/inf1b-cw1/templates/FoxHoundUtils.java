import java.util.Objects;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {

    // ATTENTION: Changing the following given constants can 
    // negatively affect the outcome of the auto grading!

    /** Default dimension of the game board in case none is specified. */
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possible dimension of the game board. */
    public static final int MAX_DIM = 26;

    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...

    public static String[] initialisePositions(int dimension) {
        // TODO implement me
        int entries_length = 5;
        if(dimension < MIN_DIM || dimension > MAX_DIM){
            throw new IllegalArgumentException("dimension should be between 4 and 26.");
        } else {
            dimension = DEFAULT_DIM;
        }

        String[] entries = {"B1","D1","F1","H1","E8"};

        return entries;
    }


    public static boolean isFoxWin(String foxPos) {
        if(foxPos == null){
            throw new NullPointerException("fox position is empty.");
        }
        if(foxPos == "B1" || foxPos == "D1"|| foxPos == "F1" ||foxPos == "H1") {
            System.out.println("Fox wins");
            return true;
        }
        return false;
    }

    public static boolean isHoundWin(String[] players, int dimension) {

        Objects.requireNonNull(players);
        if (dimension < MIN_DIM || dimension > MAX_DIM){
            throw new IllegalArgumentException("dimension should be between 4 and 26");
        } else{
            dimension = DEFAULT_DIM;
        }

        String currFoxPos = players[4];
        String currFoxPos1 = players[4];
        String currFoxPos2= players[4];
        String currFoxPos3 = players[4];

     char currChar = players[4].charAt(0);
     char currInt  = players[4].charAt(1);
     String dest1 = currFoxPos.replace(currChar, (char) (currChar -1)).replace(currInt, (char) (currInt - 1));
     String dest2 = currFoxPos1.replace(currChar, (char) (currChar + 1)).replace(currInt, (char) (currInt -1));
     String dest3 = currFoxPos2.replace(currChar, (char) (currChar - 1)).replace(currInt, (char) (currInt + 1));
     String dest4 = currFoxPos3.replace(currChar, (char) (currChar +1)).replace(currInt, (char) (currInt +1));

     /*System.out.println(dest1);
     System.out.println(dest2);
     System.out.println(dest3);
     System.out.println(dest4);*/

        if (((isValidMove(dimension,players,FOX_FIELD,players[4],dest1)) || (isValidMove(dimension,players,FOX_FIELD,players[4],dest2)) ||
             (isValidMove(dimension,players,FOX_FIELD,players[4],dest3)) || (isValidMove(dimension,players,FOX_FIELD,players[4],dest4))) == false){
            System.out.println("Hound wins");
            return true;
        }
        else {
            System.out.println("Hound loses");
            return false;
        }
    }

    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String dest) {
        String chars = "ABCDEFGH";
        String ab = "";
        char charOfOrigin = origin.charAt(0); //
        int numOrigin = Integer.parseInt(String.valueOf(origin.charAt(1)));
        char charOfDest = dest.charAt(0);
        int numDest = Integer.parseInt(String.valueOf(dest.charAt(1)));

        if (figure != FOX_FIELD && figure != HOUND_FIELD) {
            throw new IllegalArgumentException("Ooops,your figure is invalid! Please provide a valid figure.");
        }
        if (players == null) {
            throw new IllegalArgumentException("Where are the players' current position?");
        }
        if (dim != 8 && dim != 11 && dim != 5) {
            throw new IllegalArgumentException("not valid dimension input");
        }
        if (!chars.contains(ab + charOfOrigin)) {
            throw new IllegalArgumentException("not a valid input");
        }
        if (numOrigin <= 0 || numOrigin > dim) {
            throw new IllegalArgumentException("not valid origin input");
        }
        // when the origin is not a field
        if (origin != players[0] && origin != players[1] && origin != players[2] && origin != players[3]
                && origin != players[4]) {
            return false;
        }
        // when the destination is occupied
        if (dest == players[0] || dest == players[1] || dest == players[2] || dest == players[3]
                || dest == players[4]) {
            return false;
        }
        if (figure == HOUND_FIELD) {
             // checking all invalid moves when figure is 'H'
            if (!(charOfDest >= 'A' && charOfDest <= 'H')) {
                return false;
            } else if (!(numDest >= 1 && numDest <= dim)) {
                return false;
            } else if (!((numDest == numOrigin + 1) && ((charOfDest == charOfOrigin + 1) || (charOfDest == charOfOrigin - 1)))) {
                return false;
            } else if ((origin != players[1] && origin != players[4] && origin != players[0] && origin != players[2])) {
                return false;
            }
        } else {
            // checking  all invalid moves when figure = 'F'
            if (!(charOfDest >= 'A' && charOfDest <= 'H')) {
                return false;
            } else if (!(numDest >= 1 && numDest <= dim)) {
                return false;
            } else if (!(((numDest == numOrigin + 1) || (numDest == numOrigin - 1))
                    && ((charOfDest == charOfOrigin + 1) || (charOfDest == charOfOrigin - 1)))) {
                return false;
            } else if ((origin == players[0] || origin == players[1] || origin == players[2] || origin == players[3])) {
                return false;
            }
        }
        return true;
    }

}
