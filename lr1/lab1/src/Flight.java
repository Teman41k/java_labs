import java.util.List;

class Flight {
    private final String destination;
    private final String flightNumber;
    private final String aircraftType;
    private final String departureTime;
    private final List<String> daysOfWeek;

    Flight(String destination, String flightNumber, String aircraftType,
           String departureTime, List<String> daysOfWeek) {
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.aircraftType = aircraftType;
        this.departureTime = departureTime;
        this.daysOfWeek = daysOfWeek;
    }

    public String getDestination() { return destination; }
    public String getFlightNumber() { return flightNumber; }
    public String getAircraftType() { return aircraftType; }
    public String getDepartureTime() { return departureTime; }
    public List<String> getDaysOfWeek() { return daysOfWeek; }

    public String toString() {
        return "Рейс " + getFlightNumber() + " в " + getDestination() +
                ", самолет: " + getAircraftType() +
                ", время: " + getDepartureTime() +
                ", дни: " + getDaysOfWeek();
    }
}