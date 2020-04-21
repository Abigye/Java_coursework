import java.util.Arrays;
import java.util.Objects;
import java.lang.Float;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {

    /**
     * the title of the book
     */
    public final String title;

    /**
     * list of authors of a book
     */
    public final String[] authors;

    /**
     *the rating of a book
     */
    public final float rating;

    /**
     *the ISBN of a book
     */
    public final String ISBN;

    /**
     * the number of pages of a book
     */
    public final int pages;

    /**
     * initialises a bookEntry with the parameters
     * @param title  title of the book
     * @param authors authors of the book
     * @param rating  rating of the book
     * @param ISBN    ISBN of the book
     * @param pages   number of pages of the book
     * @throws NullPointerException  if authors or ISBN or title is null
     * @throws IllegalArgumentException if rating is not between 0 and 5 or pages is less than 0
     */
    public BookEntry(String title, String[] authors, float rating, String ISBN, int pages) {
        Objects.requireNonNull(title, "title should not be null");
        Objects.requireNonNull(authors, "authors should not be null");
        Objects.requireNonNull(ISBN, "ISBN should not be null");
        this.title = title;
        this.authors = authors;
        this.ISBN = ISBN;
        if (rating < 0 && rating > 5) {
            throw new IllegalArgumentException("pages should be between 0 and 5");
        } else {
            this.rating = rating;
        }
        if (pages < 0) {
            throw new IllegalArgumentException(" number of pages should be non-negative");
        } else {
            this.pages = pages;
        }
    }

    /**
     * getter for title of book
     * @return   title of a book
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter for authors
     * @return  authors of a book
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * getter for rating
     * @return rating of a book
     */
    public float getRating() {
        return rating;
    }

    /**
     * getter for ISBN
     * @return ISBN of a book
     */

    public String getISBN() {
        return ISBN;
    }

    /**
     * getter for pages
     * @return   the number of pages of a book
     */

    public int getPages() {
        return pages;
    }

    /**
     * prints the parameters of  a Book Entry
     * overrides toString method of Object class
     * @return  formatted  String form of the parameters of a bookEntry
     */
    @Override
    public String toString() {
        String authorsString = String.join(",", getAuthors());
        return String.format("%s\nby %s\nRating: %.2f\nISBN: %s\n%d pages", getTitle(),
                authorsString, getRating(), getISBN(), getPages());
    }

    /**
     * compares the parameter of this and objects to see if they are equal
     * overrides the equals method of Object class
     * @param object  object to be compared with
     * @return  true  <i>this</i> is  equal to object
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BookEntry)) {
            return false;
        }
        BookEntry bookEntry = (BookEntry) object;

        return Float.compare(bookEntry.getRating(), getRating()) == 0 &&
                getPages() == bookEntry.getPages() &&
                getTitle().equals(bookEntry.getTitle()) &&
                Arrays.equals(getAuthors(), bookEntry.getAuthors()) &&
                getISBN().equals(bookEntry.getISBN());
    }

    /**
     * generates a hash code from all five instance fields.
     * overrides hashcode method of Object class
     * @return int
     */

    @Override
    public int hashCode() {
        int result = Objects.hash(getTitle(), getRating(), getISBN(), getPages());
        result = 31 * result + Arrays.hashCode(getAuthors());
        return result;
    }
}


