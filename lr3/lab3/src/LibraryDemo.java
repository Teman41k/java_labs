import java.util.List;

public class LibraryDemo {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ БИБЛИОТЕЧНОЙ СИСТЕМЫ ===\n");

        // Создание библиотек
        Library centralLibrary = new Library("Центральная библиотека");
        Library scienceLibrary = new Library("Научная библиотека");
        Library childrenLibrary = new Library("Детская библиотека");

        // Добавление книг
        centralLibrary.addBook(new Book("Преступление и наказание", "Фёдор Достоевский", 1866));
        centralLibrary.addBook(new Book("Война и мир", "Лев Толстой", 1869));
        centralLibrary.addBook(new Book("Идиот", "Фёдор Достоевский", 1869));

        scienceLibrary.addBook(new Book("Философия Java", "Брюс Эккель", 2006));
        scienceLibrary.addBook(new Book("Совершенный код", "Стив Макконнелл", 2004));

        childrenLibrary.addBook(new Book("Гарри Поттер", "Джоан Роулинг", 1997));
        childrenLibrary.addBook(new Book("Маленький принц", "Антуан де Сент-Экзюпери", 1943));

        // Вывод информации
        System.out.println("СОЗДАННЫЕ БИБЛИОТЕКИ:");
        Library[] libraries = {centralLibrary, scienceLibrary, childrenLibrary};
        for (Library library : libraries) {
            System.out.println("  " + library);
        }

        // Поиск по авторам
        System.out.println("\n=== ПОИСК КНИГ ПО АВТОРАМ ===");

        String[] authors = {"Фёдор Достоевский", "Брюс Эккель", "Джоан Роулинг"};

        for (String author : authors) {
            System.out.println("\nКниги автора '" + author + "':");
            boolean found = false;

            for (Library library : libraries) {
                List<Book> books = library.getBooksByAuthor(author);
                if (!books.isEmpty()) {
                    System.out.println("  В " + library.getLibraryName() + ":");
                    for (Book book : books) {
                        System.out.println("    - " + book.getTitle() + " (" + book.getPublicationYear() + ")");
                    }
                    found = true;
                }
            }

            if (!found) {
                System.out.println("  Книги не найдены");
            }
        }

        // Полная информация о книгах
        System.out.println("\n=== ПОЛНАЯ ИНФОРМАЦИЯ О КНИГАХ ===");
        Book sampleBook = centralLibrary.getAllBooks().get(0);
        System.out.println("Пример книги:");
        System.out.println("  Название: " + sampleBook.getTitle());
        System.out.println("  Автор: " + sampleBook.getAuthor());
        System.out.println("  Год: " + sampleBook.getPublicationYear());
        System.out.println("  toString(): " + sampleBook.toString());
    }
}