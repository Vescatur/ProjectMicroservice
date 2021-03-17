package soa.group5.emergency;

public class EmergencyNotFoundException extends RuntimeException {

    public EmergencyNotFoundException(Long id) {
        super("Could not find emergency " + id);
    }
}