// Event.java (New File)
package Model; // Recommended package for data models/entities

import java.time.LocalDate;

public class Event {
    private String eventName;
    private String eventDescription;
    private LocalDate eventDate; // Using LocalDate for dates
    private String organizerName;
    private String venue;
    private int capacity;
    private double registrationFee;

    // Constructor
    public Event(String eventName, String eventDescription, LocalDate eventDate,
                 String organizerName, String venue, int capacity, double registrationFee) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.organizerName = organizerName;
        this.venue = venue;
        this.capacity = capacity;
        this.registrationFee = registrationFee;
    }

    // Getters
    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public String getVenue() {
        return venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    // Setters (optional, but often useful for updating properties)
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    @Override
    public String toString() {
        // This is what will be displayed in your JList.
        // You can format it nicely to show key information.
        return String.format("%s (Date: %s, Venue: %s)", eventName, eventDate.toString(), venue);
    }
}