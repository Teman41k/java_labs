import java.util.List;

/**
 * Класс, представляющий рейс авиакомпании.
 * Содержит информацию о рейсе: пункт назначения, номер, тип самолета,
 * время вылета и дни недели выполнения.
 */
class Flight {
    private final String destination;
    private final String flightNumber;
    private final String aircraftType;
    private final String departureTime;
    private final List<String> daysOfWeek;

    /**
     * Конструктор для создания нового рейса.
     * РЕФАКТОРИНГ: Добавлена валидация входных параметров
     */
    Flight(String destination, String flightNumber, String aircraftType,
           String departureTime, List<String> daysOfWeek) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Пункт назначения не может быть пустым");
        }
        if (flightNumber == null || flightNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер рейса не может быть пустым");
        }

        this.destination = destination.trim();
        this.flightNumber = flightNumber.trim();
        this.aircraftType = aircraftType != null ? aircraftType.trim() : "";
        this.departureTime = departureTime != null ? departureTime.trim() : "";
        this.daysOfWeek = daysOfWeek;
    }

    // Геттеры
    public String getDestination() { return destination; }
    public String getFlightNumber() { return flightNumber; }
    public String getAircraftType() { return aircraftType; }
    public String getDepartureTime() { return departureTime; }
    public List<String> getDaysOfWeek() { return daysOfWeek; }

    /**
     * РЕФАКТОРИНГ: Улучшено строковое представление объекта
     */
    @Override
    public String toString() {
        return String.format("Рейс %s в %s, самолет: %s, время: %s, дни: %s",
                flightNumber, destination,
                aircraftType.isEmpty() ? "не указан" : aircraftType,
                departureTime.isEmpty() ? "не указано" : departureTime,
                daysOfWeek);
    }

    /**
     * РЕФАКТОРИНГ: Добавлен метод для проверки совпадения с днем недели
     */
    public boolean fliesOnDay(String day) {
        return daysOfWeek != null && daysOfWeek.contains(day);
    }
}