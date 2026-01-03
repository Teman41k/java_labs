import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для управления коллекцией рейсов.
 * Реализует базовые CRUD-операции.
 */
public class FlightRepository {
    private final List<Flight> flights = new ArrayList<>();

    /**
     * Добавляет новый рейс в коллекцию.
     * @param flight рейс для добавления
     */
    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    /**
     * Удаляет рейс по индексу.
     * @param index индекс удаляемого рейса
     * @return true если удаление успешно, false если индекс неверный
     */
    public boolean removeFlight(int index) {
        if (index >= 0 && index < flights.size()) {
            flights.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Обновляет существующий рейс.
     * @param index индекс обновляемого рейса
     * @param flight новые данные рейса
     * @return true если обновление успешно, false если индекс неверный
     */
    public boolean updateFlight(int index, Flight flight) {
        if (index >= 0 && index < flights.size()) {
            flights.set(index, flight);
            return true;
        }
        return false;
    }

    /**
     * Возвращает все рейсы.
     * @return список всех рейсов
     */
    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights); // Возвращаем копию для безопасности
    }

    /**
     * Ищет рейсы по пункту назначения.
     * @param destination пункт назначения
     * @return список рейсов в указанный пункт
     */
    public List<Flight> findFlightsByDestination(String destination) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDestination().equalsIgnoreCase(destination)) {
                result.add(flight);
            }
        }
        return result;
    }

    /**
     * Ищет рейсы по дню недели.
     * @param day день недели
     * @return список рейсов в указанный день
     */
    public List<Flight> findFlightsByDay(String day) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDaysOfWeek().contains(day)) {
                result.add(flight);
            }
        }
        return result;
    }

    /**
     * Ищет рейсы по дню и времени.
     * @param day день недели
     * @param minTime минимальное время вылета
     * @return список рейсов, удовлетворяющих условиям
     */
    public List<Flight> findFlightsByDayAndTime(String day, String minTime) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDaysOfWeek().contains(day) &&
                    flight.getDepartureTime().compareTo(minTime) > 0) {
                result.add(flight);
            }
        }
        return result;
    }

    /**
     * Возвращает количество рейсов в репозитории.
     * @return количество рейсов
     */
    public int getFlightCount() {
        return flights.size();
    }
}