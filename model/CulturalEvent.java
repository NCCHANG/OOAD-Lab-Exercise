package model;

import java.time.LocalDate;

public class CulturalEvent extends Event {
    public CulturalEvent(String eventName, String eventDescription, LocalDate eventDate, String organizerName, String venue, int capacity, double registrationFee) {
        super(eventName, eventDescription, eventDate, organizerName, venue, capacity, registrationFee);
    }

    public String getEventType() { 
        return "Cultural"; 
    }
}