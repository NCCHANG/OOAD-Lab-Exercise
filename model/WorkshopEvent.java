// src/model/WorkshopEvent.java
package model;

import java.time.LocalDate;

public class WorkshopEvent extends Event {
    public WorkshopEvent(String eventName, String eventDescription, LocalDate eventDate, String organizerName, String venue, int capacity, double registrationFee) {
        super(eventName, eventDescription, eventDate, organizerName, venue, capacity, registrationFee);
    }

    public String getEventType() {
        return "Workshop"; // Return the specific type for this event
    }
}