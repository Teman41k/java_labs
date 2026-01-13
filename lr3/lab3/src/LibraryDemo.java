import java.util.List;
import java.util.Scanner;

public class LibraryDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // РЕФАКТОРИНГ: Используем репозиторий вместо отдельных библиотек
        LibraryRepository repository = new LibraryRepository();

        System.out.println("=== БИБЛИОТЕЧНАЯ СИСТЕМА С РЕПОЗИТОРИЕМ ===\n");

        // Создание и добавление библиотек через репозиторий
        System.out.println("Создание начальных библиотек...");

        Library centralLibrary = new Library("Центральная библиотека");
        centralLibrary.addBook(new Book("Преступление и наказание", "Фёдор Достоевский", 1866));
        centralLibrary.addBook(new Book("Война и мир", "Лев Толстой", 1869));
        centralLibrary.addBook(new Book("Идиот", "Фёдор Достоевский", 1869));
        repository.addLibrary(centralLibrary);

        Library scienceLibrary = new Library("Научная библиотека");
        scienceLibrary.addBook(new Book("Философия Java", "Брюс Эккель", 2006));
        scienceLibrary.addBook(new Book("Совершенный код", "Стив Макконнелл", 2004));
        repository.addLibrary(scienceLibrary);

        Library childrenLibrary = new Library("Детская библиотека");
        childrenLibrary.addBook(new Book("Гарри Поттер и философский камень", "Джоан Роулинг", 1997));
        childrenLibrary.addBook(new Book("Маленький принц", "Антуан де Сент-Экзюпери", 1943));
        repository.addLibrary(childrenLibrary);

        System.out.println("Инициализация завершена. Всего библиотек: " + repository.getLibraryCount());
        System.out.println("Всего книг в системе: " + repository.getTotalBookCount());

        // Основное меню
        boolean running = true;
        while (running) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("Всего библиотек: " + repository.getLibraryCount());
            System.out.println("Всего книг: " + repository.getTotalBookCount());
            System.out.println("1. Показать все библиотеки");
            System.out.println("2. Поиск книг по автору");
            System.out.println("3. Показать все книги");
            System.out.println("4. Управление библиотеками");
            System.out.println("5. Демонстрация работы системы");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAllLibraries(repository);
                    break;
                case "2":
                    searchBooksByAuthor(scanner, repository);
                    break;
                case "3":
                    showAllBooks(repository);
                    break;
                case "4":
                    manageLibrariesMenu(scanner, repository);
                    break;
                case "5":
                    runDemonstration(repository);
                    break;
                case "0":
                    running = false;
                    System.out.println("Выход из системы...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    /**
     * РЕФАКТОРИНГ: Метод для отображения всех библиотек
     */
    private static void showAllLibraries(LibraryRepository repository) {
        List<Library> libraries = repository.getAllLibraries();
        if (libraries.isEmpty()) {
            System.out.println("\nБиблиотек нет в системе.");
            return;
        }

        System.out.println("\n=== ВСЕ БИБЛИОТЕКИ ===");
        for (int i = 0; i < libraries.size(); i++) {
            Library library = libraries.get(i);
            System.out.printf("%d. %s\n", i + 1, library);

            // Показываем первые 3 книги каждой библиотеки
            List<Book> books = library.getAllBooks();
            if (!books.isEmpty()) {
                System.out.println("   Книги:");
                int limit = Math.min(3, books.size());
                for (int j = 0; j < limit; j++) {
                    System.out.println("     • " + books.get(j));
                }
                if (books.size() > 3) {
                    System.out.println("     ... и ещё " + (books.size() - 3) + " книг");
                }
            }
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод для поиска книг по автору
     */
    private static void searchBooksByAuthor(Scanner scanner, LibraryRepository repository) {
        System.out.print("\nВведите имя автора для поиска: ");
        String author = scanner.nextLine();

        List<Book> books = repository.findBooksByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("Книги автора \"" + author + "\" не найдены.");
        } else {
            System.out.println("\n=== КНИГИ АВТОРА: " + author + " ===");
            System.out.println("Найдено книг: " + books.size());

            // Группируем книги по библиотекам
            List<Library> libraries = repository.getAllLibraries();
            for (Library library : libraries) {
                List<Book> libraryBooks = library.getBooksByAuthor(author);
                if (!libraryBooks.isEmpty()) {
                    System.out.println("\nВ " + library.getLibraryName() + ":");
                    for (Book book : libraryBooks) {
                        System.out.println("  • " + book.getTitle() +
                                " (" + book.getPublicationYear() + ")");
                    }
                }
            }
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод для отображения всех книг
     */
    private static void showAllBooks(LibraryRepository repository) {
        List<Book> allBooks = repository.getAllBooks();

        if (allBooks.isEmpty()) {
            System.out.println("\nВ системе нет книг.");
            return;
        }

        System.out.println("\n=== ВСЕ КНИГИ В СИСТЕМЕ ===");
        System.out.println("Всего книг: " + allBooks.size());

        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            System.out.printf("%d. %s\n", i + 1, book);
        }
    }

    /**
     * РЕФАКТОРИНГ: Меню для управления библиотеками
     */
    private static void manageLibrariesMenu(Scanner scanner, LibraryRepository repository) {
        boolean managing = true;

        while (managing) {
            System.out.println("\n=== УПРАВЛЕНИЕ БИБЛИОТЕКАМИ ===");
            System.out.println("1. Добавить новую библиотеку");
            System.out.println("2. Добавить книгу в библиотеку");
            System.out.println("3. Удалить библиотеку");
            System.out.println("4. Найти библиотеку по названию");
            System.out.println("0. Назад в главное меню");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewLibrary(scanner, repository);
                    break;
                case "2":
                    addBookToLibrary(scanner, repository);
                    break;
                case "3":
                    removeLibrary(scanner, repository);
                    break;
                case "4":
                    findLibraryByName(scanner, repository);
                    break;
                case "0":
                    managing = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод для добавления новой библиотеки
     */
    private static void addNewLibrary(Scanner scanner, LibraryRepository repository) {
        System.out.print("\nВведите название новой библиотеки: ");
        String name = scanner.nextLine();

        try {
            Library newLibrary = new Library(name);
            repository.addLibrary(newLibrary);
            System.out.println("Библиотека \"" + name + "\" успешно добавлена!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод для добавления книги в библиотеку
     */
    private static void addBookToLibrary(Scanner scanner, LibraryRepository repository) {
        List<Library> libraries = repository.getAllLibraries();

        if (libraries.isEmpty()) {
            System.out.println("\nНет библиотек для добавления книг.");
            return;
        }

        System.out.println("\nВыберите библиотеку:");
        for (int i = 0; i < libraries.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, libraries.get(i));
        }

        System.out.print("Номер библиотеки: ");
        int libIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (libIndex < 0 || libIndex >= libraries.size()) {
            System.out.println("Неверный номер библиотеки.");
            return;
        }

        System.out.println("\nДобавление книги в библиотеку: " +
                libraries.get(libIndex).getLibraryName());

        System.out.print("Название книги: ");
        String title = scanner.nextLine();

        System.out.print("Автор: ");
        String author = scanner.nextLine();

        System.out.print("Год издания: ");
        int year = Integer.parseInt(scanner.nextLine());

        try {
            Book newBook = new Book(title, author, year);
            if (repository.addBookToLibrary(libIndex, newBook)) {
                System.out.println("Книга \"" + title + "\" успешно добавлена!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод для удаления библиотеки
     */
    private static void removeLibrary(Scanner scanner, LibraryRepository repository) {
        List<Library> libraries = repository.getAllLibraries();

        if (libraries.isEmpty()) {
            System.out.println("\nНет библиотек для удаления.");
            return;
        }

        System.out.println("\nВыберите библиотеку для удаления:");
        for (int i = 0; i < libraries.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, libraries.get(i));
        }

        System.out.print("Номер библиотеки (0 для отмены): ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index == -1) {
            return;
        }

        if (repository.removeLibrary(index)) {
            System.out.println("Библиотека успешно удалена!");
        } else {
            System.out.println("Неверный номер библиотеки.");
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод для поиска библиотеки по названию
     */
    private static void findLibraryByName(Scanner scanner, LibraryRepository repository) {
        System.out.print("\nВведите название библиотеки для поиска: ");
        String name = scanner.nextLine();

        List<Library> foundLibraries = repository.findLibrariesByName(name);

        if (foundLibraries.isEmpty()) {
            System.out.println("Библиотеки с названием \"" + name + "\" не найдены.");
        } else {
            System.out.println("\n=== НАЙДЕННЫЕ БИБЛИОТЕКИ ===");
            for (Library library : foundLibraries) {
                System.out.println("• " + library);
                List<Book> books = library.getAllBooks();
                if (!books.isEmpty()) {
                    System.out.println("  Книги:");
                    for (Book book : books) {
                        System.out.println("    - " + book);
                    }
                }
            }
        }
    }

    /**
     * РЕФАКТОРИНГ: Демонстрация работы системы (старый функционал)
     */
    private static void runDemonstration(LibraryRepository repository) {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ БИБЛИОТЕЧНОЙ СИСТЕМЫ ===\n");

        // Вывод информации о библиотеках
        System.out.println("СОЗДАННЫЕ БИБЛИОТЕКИ:");
        List<Library> libraries = repository.getAllLibraries();
        for (Library library : libraries) {
            System.out.println("  " + library);
        }

        // Поиск по авторам
        System.out.println("\n=== ПОИСК КНИГ ПО АВТОРАМ ===");

        String[] authors = {"Фёдор Достоевский", "Брюс Эккель", "Джоан Роулинг", "Лев Толстой"};

        for (String author : authors) {
            System.out.println("\nКниги автора '" + author + "':");
            List<Book> books = repository.findBooksByAuthor(author);

            if (books.isEmpty()) {
                System.out.println("  Книги не найдены");
            } else {
                System.out.println("  Найдено книг: " + books.size());
                for (Book book : books) {
                    System.out.println("    • " + book.getTitle() +
                            " (" + book.getPublicationYear() + ")");
                }
            }
        }

        // Пример использования новых методов
        System.out.println("\n=== ДОПОЛНИТЕЛЬНЫЕ ВОЗМОЖНОСТИ ===");

        if (!libraries.isEmpty()) {
            Library firstLibrary = libraries.get(0);
            System.out.println("\nВ " + firstLibrary.getLibraryName() + ":");

            // Поиск книг, изданных после 1900 года
            List<Book> modernBooks = firstLibrary.findBooksPublishedAfter(1900);
            System.out.println("Книги, изданные после 1900 года: " + modernBooks.size());

            // Поиск по части названия
            List<Book> warBooks = firstLibrary.findBooksByTitle("война");
            System.out.println("Книги, содержащие 'война' в названии: " + warBooks.size());
        }

        // Полная информация о первой книге
        List<Book> allBooks = repository.getAllBooks();
        if (!allBooks.isEmpty()) {
            System.out.println("\n=== ИНФОРМАЦИЯ О КНИГЕ ===");
            Book sampleBook = allBooks.get(0);
            System.out.println("Пример книги из системы:");
            System.out.println("  Название: " + sampleBook.getTitle());
            System.out.println("  Автор: " + sampleBook.getAuthor());
            System.out.println("  Год: " + sampleBook.getPublicationYear());
            System.out.println("  toString(): " + sampleBook);
        }
    }
}