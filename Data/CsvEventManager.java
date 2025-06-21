// CsvEventManager.java
package Data;

import Model.Event; // Import the new Event class
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate; // Import LocalDate
import java.time.format.DateTimeParseException; // For parsing dates
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // For stream operations

public class CsvEventManager {
    private static final String CSV_FILE_PATH = "events.csv";
    // Updated CSV_HEADER to include all new attributes
    private static final String CSV_HEADER = "eventName,eventDescription,eventDate,organizerName,venue,capacity,registrationFee";

    public CsvEventManager() {
        ensureCsvFileExists();
    }

    private void ensureCsvFileExists() {
        java.io.File file = new java.io.File(CSV_FILE_PATH);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
                writer.write(CSV_HEADER);
                writer.newLine();
                System.out.println("Created new CSV file: " + CSV_FILE_PATH);
            } catch (IOException e) {
                System.err.println("Error creating CSV file: " + e.getMessage());
            }
        }
    }

    /**
     * Adds a new event to the CSV file.
     * @param event The Event object to add.
     * @return true if the event was added successfully, false otherwise.
     */
    public boolean addEvent(Event event) {
        // Prevent duplicate event names (you might want to use a unique ID instead for robustness)
        List<Event> currentEvents = getAllEvents();
        for (Event existingEvent : currentEvents) {
            if (existingEvent.getEventName().equalsIgnoreCase(event.getEventName())) {
                System.out.println("Event with name '" + event.getEventName() + "' already exists.");
                return false;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) { // true for append mode
            writer.write(convertToCsvLine(event));
            writer.newLine();
            System.out.println("Event added to CSV: " + event.getEventName());
            return true;
        } catch (IOException e) {
            System.err.println("Error adding event to CSV: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all events from the CSV file.
     * @return A list of Event objects.
     */
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            boolean firstLine = true; // To skip header
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    if (line.equals(CSV_HEADER)) { // Skip header only if it matches
                        continue;
                    }
                }
                Event event = parseCsvLine(line);
                if (event != null) {
                    events.add(event);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading events from CSV: " + e.getMessage());
        }
        return events;
    }

    /**
     * Updates an existing event in the CSV file.
     * This involves reading all events, updating the specific one, and rewriting the entire file.
     * @param oldEventName The name of the event to update (acts as a unique identifier for now).
     * @param newEvent The updated Event object.
     * @return true if the event was updated successfully, false otherwise.
     */
    public boolean updateEvent(String oldEventName, Event newEvent) {
        List<Event> allEvents = getAllEvents();
        boolean found = false;
        for (int i = 0; i < allEvents.size(); i++) {
            if (allEvents.get(i).getEventName().equalsIgnoreCase(oldEventName)) {
                allEvents.set(i, newEvent);
                found = true;
                break;
            }
        }

        if (found) {
            writeAllEventsToCsv(allEvents);
            System.out.println("Event updated: " + oldEventName);
            return true;
        } else {
            System.out.println("Event not found for update: " + oldEventName);
            return false;
        }
    }

    /**
     * Deletes an event from the CSV file.
     * This involves reading all events, removing the specific one, and rewriting the entire file.
     * @param eventName The name of the event to delete.
     * @return true if the event was deleted successfully, false otherwise.
     */
    public boolean deleteEvent(String eventName) {
        List<Event> allEvents = getAllEvents();
        boolean removed = allEvents.removeIf(event -> event.getEventName().equalsIgnoreCase(eventName));

        if (removed) {
            writeAllEventsToCsv(allEvents);
            System.out.println("Event deleted: " + eventName);
            return true;
        } else {
            System.out.println("Event not found for deletion: " + eventName);
            return false;
        }
    }

    /**
     * Writes a list of Event objects back to the CSV file, overwriting existing content.
     * Used internally by update and delete operations.
     * @param events The list of events to write.
     */
    private void writeAllEventsToCsv(List<Event> events) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, false))) { // false for overwrite mode
            writer.write(CSV_HEADER);
            writer.newLine();
            for (Event event : events) {
                writer.write(convertToCsvLine(event));
                writer.newLine();
            }
            System.out.println("All events rewritten to CSV.");
        } catch (IOException e) {
            System.err.println("Error rewriting events to CSV: " + e.getMessage());
        }
    }

    // --- CSV Conversion Helper Methods ---

    /**
     * Converts an Event object into a CSV formatted line.
     * Handles potential commas/quotes within values by enclosing in quotes and escaping internal quotes.
     */
    private String convertToCsvLine(Event event) {
        return String.join(",",
                escapeCsv(event.getEventName()),
                escapeCsv(event.getEventDescription()),
                escapeCsv(event.getEventDate().toString()), // LocalDate to String
                escapeCsv(event.getOrganizerName()),
                escapeCsv(event.getVenue()),
                String.valueOf(event.getCapacity()), // int to String
                String.valueOf(event.getRegistrationFee()) // double to String
        );
    }

    /**
     * Parses a CSV line into an Event object.
     */
    private Event parseCsvLine(String line) {
        // Basic CSV parsing: split by comma, but handle quoted fields
        List<String> parts = parseCsvLineStrict(line); // Use a more robust parser

        if (parts.size() == 7) { // Ensure all 7 attributes are present
            try {
                String eventName = unescapeCsv(parts.get(0));
                String eventDescription = unescapeCsv(parts.get(1));
                LocalDate eventDate = LocalDate.parse(unescapeCsv(parts.get(2))); // String to LocalDate
                String organizerName = unescapeCsv(parts.get(3));
                String venue = unescapeCsv(parts.get(4));
                int capacity = Integer.parseInt(unescapeCsv(parts.get(5))); // String to int
                double registrationFee = Double.parseDouble(unescapeCsv(parts.get(6))); // String to double

                return new Event(eventName, eventDescription, eventDate, organizerName, venue, capacity, registrationFee);
            } catch (NumberFormatException | DateTimeParseException e) {
                System.err.println("Error parsing data from CSV line (format mismatch): " + line + " - " + e.getMessage());
                return null;
            }
        } else {
            System.err.println("Skipping malformed CSV line (incorrect number of columns): " + line);
            return null;
        }
    }

    // Helper for robust CSV line splitting (handles commas within quoted fields)
    private List<String> parseCsvLineStrict(String line) {
        List<String> parts = new ArrayList<>();
        boolean inQuote = false;
        StringBuilder currentPart = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuote = !inQuote;
                currentPart.append(c); // Keep quotes for unescapeCsv to process
            } else if (c == ',' && !inQuote) {
                parts.add(currentPart.toString());
                currentPart = new StringBuilder();
            } else {
                currentPart.append(c);
            }
        }
        parts.add(currentPart.toString()); // Add the last part
        return parts;
    }


    // Utility methods to handle CSV escaping/unescaping (enhanced)
    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }
        // If the value contains comma, double quote, or newline, enclose in double quotes
        if (value.contains(",") || value.contains("\"") || value.contains("\n") || value.trim().isEmpty()) { // Also handle empty string to ensure consistent quoting
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    private String unescapeCsv(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        // If value starts and ends with double quotes, and is not just "\""
        if (value.startsWith("\"") && value.endsWith("\"") && value.length() > 1) {
            // Remove outer quotes and replace "" with "
            return value.substring(1, value.length() - 1).replace("\"\"", "\"");
        }
        return value;
    }
}