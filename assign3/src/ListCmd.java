import java.util.Objects;

public class ListCmd extends LibraryCommand {
    /**
     * an instance field for input argument
     */
    private String argumentInput;

    /**Create a list command.
     *
     * @param argumentInput argument input to be parsed
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public ListCmd( String argumentInput) {
        super(CommandType.LIST, argumentInput);
        this.argumentInput = argumentInput;
    }

    /**
     * if argumentInput equals short or  is blank , it prints the title of  book in data
     * if argumentInput equals long, it prints the formatted String form of the books in data
     * overrides the execute method in LibraryCommand class
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     *
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"The library has no book entries.");
        System.out.println(data.getBookData().size() + " books in library:");
      for(BookEntry book: data.getBookData()){
          if(argumentInput.equals("short") || argumentInput.isBlank()){
              System.out.println(book.getTitle());
          }
          else if(argumentInput.equals("long")){
             String output = book.toString()+"\n";
             System.out.println(output);
          }
      }

    }

    /**
     * checks if argumentInput equals long or short or is blank
     * overrides parseArguments ofLibraryCommand class
     * @param argumentInput argument input for this command
     * @return true if argument is valid and false otherwise
     */
    @Override
    protected boolean parseArguments(String argumentInput){
        super.parseArguments(argumentInput);
        return argumentInput.isBlank() || argumentInput.equals("short") || argumentInput.equals("long");
    }
}
