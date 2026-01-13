import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для управления коллекцией библиотек.
 * Реализует базовые CRUD-операции для библиотек и их книг.
 */
public class LibraryRepository {
    private final List<Library> libraries = new ArrayList<>();

    /**
     * Добавляет новую библиотеку в репозиторий.
     * @param library библиотека для добавления
     */
    public void addLibrary(Library library) {
        libraries.add(library);
    }

    /**
     * Удаляет библиотеку по индексу.
     * @param index индекс удаляемой библиотеки
     * @return true если удаление успешно, false если индекс неверный
     */
    public boolean removeLibrary(int index) {
        if (index >= 0 && index < libraries.size()) {
            libraries.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Обновляет существующую библиотеку.
     * @param index индекс обновляемой библиотеки
     * @param library новые данные библиотеки
     * @return true если обновление успешно, false если индекс неверный
     */
    public boolean updateLibrary(int index, Library library) {
        if (index >= 0 && index < libraries.size()) {
            libraries.set(index, library);
            return true;
        }
        return false;
    }

    /**
     * Возвращает все библиотеки.
     * @return список всех библиотек
     */
    public List<Library> getAllLibraries() {
        return new ArrayList<>(libraries);
    }

    /**
     * Ищет библиотеки по названию.
     * @param name название библиотеки для поиска
     * @return список библиотек с указанным названием
     */
    public List<Library> findLibrariesByName(String name) {
        List<Library> result = new ArrayList<>();
        for (Library library : libraries) {
            if (library.getLibraryName().equalsIgnoreCase(name)) {
                result.add(library);
            }
        }
        return result;
    }

    /**
     * Ищет книги по автору во всех библиотеках.
     * @param authorName имя автора
     * @return список книг указанного автора
     */
    public List<Book> findBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Library library : libraries) {
            result.addAll(library.getBooksByAuthor(authorName));
        }
        return result;
    }

    /**
     * Возвращает все книги из всех библиотек.
     * @return список всех книг
     */
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        for (Library library : libraries) {
            allBooks.addAll(library.getAllBooks());
        }
        return allBooks;
    }

    /**
     * Возвращает общее количество книг во всех библиотеках.
     * @return общее количество книг
     */
    public int getTotalBookCount() {
        int total = 0;
        for (Library library : libraries) {
            total += library.getBookCount();
        }
        return total;
    }

    /**
     * Возвращает количество библиотек в репозитории.
     * @return количество библиотек
     */
    public int getLibraryCount() {
        return libraries.size();
    }

    /**
     * Добавляет книгу в указанную библиотеку.
     * @param libraryIndex индекс библиотеки
     * @param book книга для добавления
     * @return true если добавление успешно, false если индекс неверный
     */
    public boolean addBookToLibrary(int libraryIndex, Book book) {
        if (libraryIndex >= 0 && libraryIndex < libraries.size()) {
            libraries.get(libraryIndex).addBook(book);
            return true;
        }
        return false;
    }

    /**
     * Возвращает библиотеку по индексу.
     * @param index индекс библиотеки
     * @return библиотека или null если индекс неверный
     */
    public Library getLibraryByIndex(int index) {
        if (index >= 0 && index < libraries.size()) {
            return libraries.get(index);
        }
        return null;
    }
}