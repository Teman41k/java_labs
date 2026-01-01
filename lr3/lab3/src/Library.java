import java.util.ArrayList;
import java.util.List;

class Book {
    private final String title;
    private final String author;
    private final int publicationYear;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }

    @Override
    public String toString() {
        return "Книга: \"" + title + "\", автор: " + author + ", год: " + publicationYear;
    }
}

class Library {
    private final String libraryName;
    private final List<Book> bookCollection;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.bookCollection = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookCollection.add(book);
    }

    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookCollection);
    }

    public String getLibraryName() { return libraryName; }
    public int getBookCount() { return bookCollection.size(); }

    @Override
    public String toString() {
        return "Библиотека: \"" + libraryName + "\", количество книг: " + bookCollection.size();
    }
}