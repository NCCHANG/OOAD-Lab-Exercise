// src/model/SportsEvent.java
package model;

import java.time.LocalDate;

public class SportsEvent extends Event {
    public SportsEvent(String eventName, String eventDescription, LocalDate eventDate, String organizerName, String venue, int capacity, double registrationFee) {
        super(eventName, eventDescription, eventDate, organizerName, venue, capacity, registrationFee);
    }

    public String getEventType() {
        return "Sports"; // Return the specific type for this event
    }
}