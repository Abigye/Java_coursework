import java.nio.file.Paths;
import java.util.*;

public class RemoveCmd extends LibraryCommand {
    /**
     * an instance field for input argument
     */
     private String argumentInput;

    /**Create a remove  command.
     *
     * @param argumentInput argument input to be parsed
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public RemoveCmd(String argumentInput){
        super(CommandType.REMOVE,argumentInput);
        this.argumentInput = argumentInput;
    }

    /** removes books from the data either by TiTLE or By AUTHOR
     * overrides the execute method in LibraryCommand
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if data is null
     *
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"data can not be null.");
         int index = argumentInput.indexOf(" ");
         String firstPart = argumentInput.substring(0, index);
         String secondPart= argumentInput.substring(index+1);
         boolean found = false;
         if (firstPart.equals("TITLE")) {
             for (BookEntry book : data.getBookData()) {
                 if (book.getTitle().toLowerCase().contains(secondPart.toLowerCase())) {
                        data.getBookData().remove(book);
                        System.out.println(secondPart + ": removed successfully.");
                        found = true;
                        break;
                    }
                }
                 if(!found){
                    System.out.println(secondPart + ": not found.");
                }
            }
        else if (firstPart.equals("AUTHOR")) {
             List <String> removedBooks = new ArrayList <>();
             for (int i = 0;i<data.getBookData().size();i++){
                 BookEntry book = data.getBookData().get(i);
                 for (int j = 0;j < book.getAuthors().length; j++) {
                     if (book.getAuthors()[j].toLowerCase().equals(secondPart.toLowerCase())) {
                         removedBooks.add(book.getTitle());
                         data.getBookData().remove(book);
                     }
                 }
             }
             System.out.println(removedBooks.size() + " books removed for author: " + secondPart);
         }
    }

    /**
     * checks if the argumentInput contains TITLE or AUTHOR as the first half of the argumentInput
     * overrides the parseArgument method in LibraryCommand class
     * @param argumentInput argument input for this command
     * @return true if argumentInput is valid and false otherwise
     */

    @Override
    protected boolean parseArguments(String argumentInput) {
        super.parseArguments(argumentInput);
        if(argumentInput.equals("")){
            return false;
        }
        int index = argumentInput.indexOf(' ');
        if(!argumentInput.contains(" ")){
            return false;
        }
        String firstPart = argumentInput.substring(0, index);
        String secondPart = argumentInput.substring(index + 1);
        if (!((firstPart.equals("AUTHOR") && !secondPart.equals("")) ||
                (firstPart.equals("TITLE") && !secondPart.equals("")))) {
            return false;
            }
        return  true;
    }
}
