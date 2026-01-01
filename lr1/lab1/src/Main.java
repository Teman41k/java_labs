import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Flight> flights = new ArrayList<>();

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

            System.out.println("Введите время вылета:");
            String departureTime = scanner.nextLine();

            System.out.println("Введите дни недели через запятую:");
            String daysInput = scanner.nextLine();
            List<String> daysOfWeek = Arrays.asList(daysInput.split(","));

            flights.add(new Flight(destination, flightNumber, aircraftType, departureTime, daysOfWeek));
            flights.add(new Flight("Москва", "2", "Боинг","12:00", List.of("Понедельник","Среда","Пятница")));
            flights.add(new Flight("Минск", "3", "Джет","13:00", List.of("Вторник","Четверг")));
            flights.add(new Flight("Гомель", "4", "Кукурузник","00:00", List.of("Суббота","Воскресенье")));
        }

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1 - Найти рейсы по пункту назначения");
            System.out.println("2 - Найти рейсы по дню недели");
            System.out.println("3 - Найти рейсы по дню и времени");
            System.out.println("0 - Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Введите пункт назначения:");
                    String dest = scanner.nextLine();
                    for (Flight f : flights) {
                        if (f.getDestination().equalsIgnoreCase(dest)) {
                            System.out.println(f);
                        }
                    }
                    break;

                case "2":
                    System.out.println("Введите день недели:");
                    String day = scanner.nextLine();
                    for (Flight f : flights) {
                        if (f.getDaysOfWeek().contains(day)) {
                            System.out.println(f);
                        }
                    }
                    break;

                case "3":
                    System.out.println("Введите день недели:");
                    String day3 = scanner.nextLine();
                    System.out.println("Введите минимальное время (например, 15:00):");
                    String time = scanner.nextLine();
                    for (Flight f : flights) {
                        if (f.getDaysOfWeek().contains(day3) && f.getDepartureTime().compareTo(time) > 0) {
                            System.out.println(f);
                        }
                    }
                    break;

                case "0":
                    System.out.println("Выход...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        }
    }
}