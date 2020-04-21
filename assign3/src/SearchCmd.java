
import java.util.Objects;

public class SearchCmd  extends LibraryCommand{
    /**
     * an instance field for input argument
     */
    private String argumentInput;

    /**Create  a search command.
     *
     * @param argumentInput argument input to be parsed
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
        this.argumentInput = argumentInput;
    }

    /**
     * prints the title of book that matches the argumentInput from the data
     *overrides the execute method in LibraryCommand
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"data cannot be null.");
        int i=0;
        boolean found = false;
        while(i < data.getBookData().size()){
            String eachTitle = data.getBookData().get(i).getTitle();
            if(eachTitle.toLowerCase().contains(argumentInput.toLowerCase())){
                System.out.println(eachTitle);
                found = true;
            }
            i++;
        }
        if (!found) {
            System.out.println("No hits found for search term: " + argumentInput);
        }
    }

    /**
     * checks the validity of argumentInput
     * overrides the parseArguments method in LibraryCommand class
     * @param argumentInput argument input for this command
     * @return false if argumentInput isBlank or contains spaces and true otherwise
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
         super.parseArguments(argumentInput);
        return !argumentInput.isBlank() && !argumentInput.contains(" ");
    }
}
