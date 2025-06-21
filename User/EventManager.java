package User;

import model.Event;
import java.util.List;
import java.util.Optional;

public interface EventManager {
    //may need some parameters for the methods
    void createEvent(Event event);
    void deleteEvent(String eventName);
    void updateEvent(String oldEventName, Event newEventName);
    List<Event> getAllEvents();
    Optional<Event> getEventByName(String eventName);
}
