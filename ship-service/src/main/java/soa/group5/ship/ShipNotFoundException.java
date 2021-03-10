package soa.group5.ship;

public class ShipNotFoundException extends RuntimeException {

    public ShipNotFoundException(Long id) {
        super("Could not find ship " + id);
    }
}