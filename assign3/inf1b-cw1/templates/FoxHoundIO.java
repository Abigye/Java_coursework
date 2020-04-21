import java.nio.file.Path;
import java.util.Objects;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {


    public static char loadGame(String[] players, Path input) {
        Objects.requireNonNull(input,"path can not be empty");
        return 'o';
    }

    public static boolean saveGame(String[] players, char nextMove, Path saveFile) {
        Objects.requireNonNull(saveFile," path can not be empty");
        return false;
    }

}
