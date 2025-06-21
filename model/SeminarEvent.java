package model;

import java.time.LocalDate;

public class SeminarEvent extends Event {
    public SeminarEvent(String eventName, String eventDescription, LocalDate eventDate, String organizerName, String venue, int capacity, double registrationFee) {
        super(eventName, eventDescription, eventDate, organizerName, venue, capacity, registrationFee);
    }

    public String getEventType() {
        return "Seminar";
    }
}