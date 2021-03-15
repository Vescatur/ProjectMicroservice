package soa.group5.schedule;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(Long id) {
        super("Could not find schedule " + id);
    }
}