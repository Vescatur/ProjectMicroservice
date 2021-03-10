package soa.group5.ship;

import java.util.List;

import soa.group5.ship.ShipNotFoundException;
import soa.group5.ship.Ship;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soa.group5.ship.ShipRepository;

@RestController
public class ShipController {

    private final ShipRepository repository;

    ShipController(ShipRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/ships")
    List<Ship> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/ships")
    Ship newShip(@RequestBody Ship newShip) {
        return repository.save(newShip);
    }

    // Single item

    @GetMapping("/ships/{id}")
    Ship one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ShipNotFoundException(id));
    }

    @PutMapping("/ships/{id}")
    Ship replaceShip(@RequestBody Ship newShip, @PathVariable Long id) {

        return repository.findById(id)
                .map(ship -> {
                    ship.setName(newShip.getName());
                    return repository.save(ship);
                })
                .orElseGet(() -> {
                    newShip.setId(id);
                    return repository.save(newShip);
                });
    }

    @DeleteMapping("/ships/{id}")
    void deleteShip(@PathVariable Long id) {
        repository.deleteById(id);
    }
}