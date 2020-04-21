import java.nio.file.Paths;
import java.util.Objects;

public class AddCmd extends LibraryCommand {

    /**
     * an instance field for input argument
     */
    private String argumentInput;

    /**Create an add command.
     *
     * @param argumentInput argument input to be parsed
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
        this.argumentInput = argumentInput;
    }

    /**
     * overrides the execute method in LibraryCommand and passes the path of
     * argumentInput to load method in LibraryFileLoader class to be added to list of books
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     *
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "data cannot be null.");
        data.loadData(Paths.get(argumentInput));
    }

    /**
     * checks if the argumentInput ends with ".csv
     * overrides the parseArgument method in LibraryCommand class
     * @param argumentInput argument input for this command
     * @return true if argumentInput is valid  and false otherwise
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given input argument must not be null.");
        super.parseArguments(argumentInput);
        return argumentInput.endsWith(".csv");

    }

}

