
import java.util.*;

public class GroupCmd extends LibraryCommand {
    /**
     * an instance field for argument input
     */
    private String argumentInput;

    /**
     * Creates a group command.
     *
     * @param argumentInput argument input to be parsed
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
        this.argumentInput = argumentInput;
    }

    /**
     * sorts the data into groups either by TITLE or by AUTHOR in alphabetical order
     * overrides the execute method in LibraryCommand
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     *
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, " data cannot be null");
        if (data.getBookData().size() == 0) {
            System.out.println("The library has no book entries.");
        } else {
            System.out.println("Grouped data by " + argumentInput);
            if (argumentInput.equals("TITLE")) {
                groupByTitle(data);
            }
            else{
                groupByAuthor(data);
            }
        }
    }

    /**
     * sorts data into groups by AUTHOR and in alphabetical order
     * helper function for execute method
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     */

    private void groupByAuthor(LibraryData data) {
        Objects.requireNonNull(data, "data should not be null");
        Map <String, List <String>> authorMap = new TreeMap <>();
        // A treeMap to store authors of books as keys and lists of titles written
        // by the authors as values in alphabetical order
        for(BookEntry bookEntry : data.getBookData()){
            for(String author: bookEntry.getAuthors()){
                if (!authorMap.containsKey(author))
                    authorMap.put(author, new ArrayList());
              // adding books written by an author into a list of books by that author
                authorMap.get(author).add(bookEntry.getTitle());

            }
        }
        for (String author: authorMap.keySet()){
            System.out.println("## "+author);
            for(String bookTitle: authorMap.get(author)) {
                System.out.println("    " + bookTitle);
            }

        }
    }

    /**
     * sorts data into groups by TITLE and in alphabetical order
     * helper function for execute method
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     */
    private void groupByTitle(LibraryData data) {
        Objects.requireNonNull(data,"data should not be null");
        // list to store Titles of books
        List <String> listOfTitles = new ArrayList <>();
        // HashSet to store titles that begin with numbers
        HashSet <String> titleByNumber = new HashSet <>();

        for (int i = 0; i < data.getBookData().size(); i++) {
            String title = data.getBookData().get(i).getTitle();
            listOfTitles.add(title);
        }
        // A titleByLetter that stores the first letter of every tile as keys and
        // HashSet that contains titles of books which starts with that letter
        Map <String,HashSet<String>> titleByLetter  = new TreeMap <>();

        groupByFirstChar(listOfTitles, titleByNumber, titleByLetter);

        printingByFirstChar(titleByNumber, titleByLetter);
    }

    /**
     * prints the titles of books in groups
     * @param titleByNumber   contains the titles whose first character is a number
     * @param titleByLetter   contains the titles whose first character is a letter
     */
    private void printingByFirstChar(HashSet <String> titleByNumber, Map <String, HashSet <String>> titleByLetter) {

        for (String letter : titleByLetter.keySet()) {
            System.out.println("## " + letter.toUpperCase());
            for (String title : titleByLetter.get(letter)) {
                System.out.println("    " + title);
            }
        }

        //printing number
        if (titleByNumber.size() > 0){
            System.out.println("## [0-9]");
            for(String number : titleByNumber){
                System.out.println("    "+number);
            }
        }
    }

    /**
     * groups the titles by their first character
     *
     * @param listOfTitles  contains a list of book titles
     * @param titleByNumber   stores the titles whose first character is a number
     * @param titleByLetter   stores the titles whose first character is a letter
     */
    private void groupByFirstChar(List <String> listOfTitles, HashSet <String> titleByNumber, Map <String, HashSet <String>> titleByLetter) {
        String alphaRegex = "[A-Za-z].*";
        for (int j = 0; j < listOfTitles.size(); j++) {
            String titleFirstLetter = listOfTitles.get(j).substring(0, 1);
            if (titleFirstLetter.matches(alphaRegex)) { // if the first letter of a title is a letter
                if (!titleByLetter.containsKey(titleFirstLetter.toUpperCase())) {
                    titleByLetter.put(titleFirstLetter.toUpperCase(), new HashSet <>());
                }
                titleByLetter.get(titleFirstLetter.toUpperCase()).add(listOfTitles.get(j));
            } else { // if the first letter is a number
                titleByNumber.add(listOfTitles.get(j));
            }

        }
    }

    /**
     * checks if the argumentInput equals TITLE or AUTHOR
     * overrides the parseArgument method in LibraryCommand class
     * @param argumentInput argument input for this command
     * @return true if argumentInput is valid  and false otherwise
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        super.parseArguments(argumentInput);
        return argumentInput.equals("AUTHOR") || argumentInput.equals("TITLE");
    }
}

