import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий книгу в библиотеке.
 * Содержит информацию о названии, авторе и годе издания.
 */
class Book {
    private final String title;
    private final String author;
    private final int publicationYear;

    /**
     * Конструктор для создания новой книги.
     * РЕФАКТОРИНГ: Добавлена валидация входных параметров
     */
    public Book(String title, String author, int publicationYear) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор книги не может быть пустым");
        }
        if (publicationYear < 0 || publicationYear > 2100) {
            throw new IllegalArgumentException("Некорректный год издания");
        }

        this.title = title.trim();
        this.author = author.trim();
        this.publicationYear = publicationYear;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }

    /**
     * РЕФАКТОРИНГ: Улучшено строковое представление
     */
    @Override
    public String toString() {
        return String.format("«%s», автор: %s, год: %d",
                title, author, publicationYear);
    }

    /**
     * РЕФАКТОРИНГ: Добавлен метод для поиска по части имени автора
     */
    public boolean isAuthor(String authorName) {
        return this.author.equalsIgnoreCase(authorName);
    }
}

/**
 * Класс, представляющий библиотеку.
 * Содержит коллекцию книг и методы для работы с ней.
 */
class Library {
    private final String libraryName;
    private final List<Book> bookCollection;

    /**
     * Конструктор для создания новой библиотеки.
     */
    public Library(String libraryName) {
        if (libraryName == null || libraryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название библиотеки не может быть пустым");
        }
        this.libraryName = libraryName.trim();
        this.bookCollection = new ArrayList<>();
    }

    /**
     * Добавляет книгу в библиотеку.
     * РЕФАКТОРИНГ: Добавлена проверка на дубликаты
     */
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Книга не может быть null");
        }

        // Проверяем, нет ли уже такой книги
        for (Book existingBook : bookCollection) {
            if (existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                    existingBook.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                System.out.println("Внимание: книга \"" + book.getTitle() +
                        "\" уже есть в библиотеке!");
                return;
            }
        }

        bookCollection.add(book);
    }

    /**
     * Ищет книги по автору.
     * РЕФАКТОРИНГ: Оптимизирован поиск с использованием нового метода Book
     */
    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.isAuthor(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Возвращает все книги библиотеки.
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookCollection);
    }

    /**
     * Ищет книги по названию.
     * РЕФАКТОРИНГ: Новый метод для расширенного поиска
     */
    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Ищет книги, изданные после указанного года.
     * РЕФАКТОРИНГ: Новый метод для фильтрации по году
     */
    public List<Book> findBooksPublishedAfter(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getPublicationYear() > year) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Удаляет книгу по индексу.
     * РЕФАКТОРИНГ: Новый метод для удаления книг
     */
    public boolean removeBook(int index) {
        if (index >= 0 && index < bookCollection.size()) {
            bookCollection.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Возвращает количество книг в библиотеке.
     */
    public int getBookCount() {
        return bookCollection.size();
    }

    public String getLibraryName() {
        return libraryName;
    }

    /**
     * РЕФАКТОРИНГ: Улучшено строковое представление библиотеки
     */
    @Override
    public String toString() {
        return String.format("Библиотека «%s» (книг: %d)",
                libraryName, bookCollection.size());
    }
}