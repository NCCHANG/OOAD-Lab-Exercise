package model;

import java.time.LocalDate;

public abstract class Event {
    private String eventName;
    private String eventDescription;
    private LocalDate eventDate;
    private String organizerName;
    private String venue;
    private int capacity;
    private double registrationFee;

    public Event(String eventName, String eventDescription, LocalDate eventDate, String organizerName, String venue, int capacity, double registrationFee) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.organizerName = organizerName;
        this.venue = venue;
        this.capacity = capacity;
        this.registrationFee = registrationFee;
    }

    //Getter methods for the event attributes
    public String getEventName() { return eventName; }
    public String getEventDescription() { return eventDescription; }
    public LocalDate getEventDate() { return eventDate; }
    public String getOrganizerName() { return organizerName; }
    public String getVenue() { return venue; }
    public int getCapacity() { return capacity; }
    public double getRegistrationFee() { return registrationFee; }

    //Setter methods for the event attributes
    public void setEventName(String eventName) { this.eventName = eventName; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }
    public void setVenue(String venue) { this.venue = venue; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setRegistrationFee(double registrationFee) { this.registrationFee = registrationFee; }

    // Abstract method to get the type of event - ENSURE THIS LINE IS PRESENT
    public abstract String getEventType();

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventDate=" + eventDate +
                ", organizerName='" + organizerName + '\'' +
                ", venue='" + venue + '\'' +
                ", capacity=" + capacity +
                ", registrationFee=" + registrationFee +
                '}';
    }
}