import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // РЕФАКТОРИНГ: Используем репозиторий вместо простого списка
        FlightRepository flightRepository = new FlightRepository();

        // РЕФАКТОРИНГ: Добавляем тестовые данные через репозиторий
        flightRepository.addFlight(new Flight("Москва", "SU100", "Боинг-737", "08:00",
                Arrays.asList("Понедельник", "Среда", "Пятница")));
        flightRepository.addFlight(new Flight("Минск", "B2781", "Airbus A320", "14:30",
                Arrays.asList("Вторник", "Четверг")));
        flightRepository.addFlight(new Flight("Гомель", "GOM123", "Embraer E175", "18:45",
                Arrays.asList("Суббота", "Воскресенье")));

        System.out.println("Добавить дополнительные рейсы? (да/нет)");
        String addMore = scanner.nextLine();

        if (addMore.equalsIgnoreCase("да")) {
            System.out.println("Сколько рейсов вы хотите добавить?");
            int count = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < count; i++) {
                System.out.println("\nДобавление рейса " + (i + 1) + ":");
                System.out.println("Введите пункт назначения:");
                String destination = scanner.nextLine();

                System.out.println("Введите номер рейса:");
                String flightNumber = scanner.nextLine();

                System.out.println("Введите тип самолета:");
                String aircraftType = scanner.nextLine();

                System.out.println("Введите время вылета (формат ЧЧ:ММ):");
                String departureTime = scanner.nextLine();

                System.out.println("Введите дни недели через запятую:");
                String daysInput = scanner.nextLine();
                List<String> daysOfWeek = Arrays.asList(daysInput.split("\\s*,\\s*"));

                // РЕФАКТОРИНГ: Добавляем рейс через репозиторий
                flightRepository.addFlight(new Flight(destination, flightNumber, aircraftType,
                        departureTime, daysOfWeek));
            }
        }

        while (true) {
            System.out.println("\n=== Система управления рейсами ===");
            System.out.println("Всего рейсов в системе: " + flightRepository.getFlightCount());
            System.out.println("Меню:");
            System.out.println("1 - Найти рейсы по пункту назначения");
            System.out.println("2 - Найти рейсы по дню недели");
            System.out.println("3 - Найти рейсы по дню и времени");
            System.out.println("4 - Показать все рейсы");
            System.out.println("5 - Управление рейсами (добавить/удалить/изменить)");
            System.out.println("0 - Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Введите пункт назначения:");
                    String dest = scanner.nextLine();
                    List<Flight> foundByDest = flightRepository.findFlightsByDestination(dest);
                    printFlights(foundByDest, "рейсы в " + dest);
                    break;

                case "2":
                    System.out.println("Введите день недели:");
                    String day = scanner.nextLine();
                    List<Flight> foundByDay = flightRepository.findFlightsByDay(day);
                    printFlights(foundByDay, "рейсы в " + day);
                    break;

                case "3":
                    System.out.println("Введите день недели:");
                    String day3 = scanner.nextLine();
                    System.out.println("Введите минимальное время (например, 15:00):");
                    String time = scanner.nextLine();
                    List<Flight> foundByDayTime = flightRepository.findFlightsByDayAndTime(day3, time);
                    printFlights(foundByDayTime, "рейсы в " + day3 + " после " + time);
                    break;

                case "4":
                    List<Flight> allFlights = flightRepository.getAllFlights();
                    printFlights(allFlights, "все рейсы");
                    break;

                case "5":
                    manageFlightsMenu(scanner, flightRepository);
                    break;

                case "0":
                    System.out.println("Выход из системы...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        }
    }

    /**
     * РЕФАКТОРИНГ: Вынесена логика вывода рейсов в отдельный метод
     */
    private static void printFlights(List<Flight> flights, String description) {
        if (flights.isEmpty()) {
            System.out.println("Не найдено " + description);
        } else {
            System.out.println("\nНайдено " + flights.size() + " " + description + ":");
            for (int i = 0; i < flights.size(); i++) {
                System.out.println((i + 1) + ". " + flights.get(i));
            }
        }
    }

    /**
     * РЕФАКТОРИНГ: Новый метод для управления рейсами через репозиторий
     */
    private static void manageFlightsMenu(Scanner scanner, FlightRepository repository) {
        System.out.println("\n=== Управление рейсами ===");
        System.out.println("1 - Добавить рейс");
        System.out.println("2 - Удалить рейс");
        System.out.println("3 - Изменить рейс");
        System.out.println("0 - Назад");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addFlightInteractive(scanner, repository);
                break;
            case "2":
                removeFlightInteractive(scanner, repository);
                break;
            case "3":
                updateFlightInteractive(scanner, repository);
                break;
            case "0":
                return;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    /**
     * РЕФАКТОРИНГ: Интерактивное добавление рейса
     */
    private static void addFlightInteractive(Scanner scanner, FlightRepository repository) {
        System.out.println("\nДобавление нового рейса:");
        System.out.println("Введите пункт назначения:");
        String destination = scanner.nextLine();

        System.out.println("Введите номер рейса:");
        String flightNumber = scanner.nextLine();

        System.out.println("Введите тип самолета:");
        String aircraftType = scanner.nextLine();

        System.out.println("Введите время вылета:");
        String departureTime = scanner.nextLine();

        System.out.println("Введите дни недели через запятую:");
        String daysInput = scanner.nextLine();
        List<String> daysOfWeek = Arrays.asList(daysInput.split("\\s*,\\s*"));

        repository.addFlight(new Flight(destination, flightNumber, aircraftType, departureTime, daysOfWeek));
        System.out.println("Рейс успешно добавлен!");
    }

    /**
     * РЕФАКТОРИНГ: Интерактивное удаление рейса
     */
    private static void removeFlightInteractive(Scanner scanner, FlightRepository repository) {
        List<Flight> allFlights = repository.getAllFlights();
        if (allFlights.isEmpty()) {
            System.out.println("Нет рейсов для удаления.");
            return;
        }

        printFlights(allFlights, "все рейсы");
        System.out.println("Введите номер рейса для удаления:");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (repository.removeFlight(index)) {
            System.out.println("Рейс успешно удален!");
        } else {
            System.out.println("Неверный номер рейса.");
        }
    }

    /**
     * РЕФАКТОРИНГ: Интерактивное обновление рейса
     */
    private static void updateFlightInteractive(Scanner scanner, FlightRepository repository) {
        List<Flight> allFlights = repository.getAllFlights();
        if (allFlights.isEmpty()) {
            System.out.println("Нет рейсов для изменения.");
            return;
        }

        printFlights(allFlights, "все рейсы");
        System.out.println("Введите номер рейса для изменения:");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= allFlights.size()) {
            System.out.println("Неверный номер рейса.");
            return;
        }

        Flight oldFlight = allFlights.get(index);
        System.out.println("Изменение рейса: " + oldFlight);

        System.out.println("Введите новый пункт назначения (оставьте пустым для сохранения старого):");
        String destination = scanner.nextLine();
        if (destination.isEmpty()) destination = oldFlight.getDestination();

        System.out.println("Введите новый номер рейса (оставьте пустым для сохранения старого):");
        String flightNumber = scanner.nextLine();
        if (flightNumber.isEmpty()) flightNumber = oldFlight.getFlightNumber();

        System.out.println("Введите новый тип самолета (оставьте пустым для сохранения старого):");
        String aircraftType = scanner.nextLine();
        if (aircraftType.isEmpty()) aircraftType = oldFlight.getAircraftType();

        System.out.println("Введите новое время вылета (оставьте пустым для сохранения старого):");
        String departureTime = scanner.nextLine();
        if (departureTime.isEmpty()) departureTime = oldFlight.getDepartureTime();

        System.out.println("Введите новые дни недели через запятую (оставьте пустым для сохранения старых):");
        String daysInput = scanner.nextLine();
        List<String> daysOfWeek = daysInput.isEmpty() ?
                oldFlight.getDaysOfWeek() :
                Arrays.asList(daysInput.split("\\s*,\\s*"));

        Flight newFlight = new Flight(destination, flightNumber, aircraftType, departureTime, daysOfWeek);
        if (repository.updateFlight(index, newFlight)) {
            System.out.println("Рейс успешно обновлен!");
        } else {
            System.out.println("Ошибка при обновлении рейса.");
        }
    }
}