package soa.group5.ship;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
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
import soa.group5.ship.integrations.task.DispatchTask;

@RestController
public class ShipController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JmsConfiguration.class);

    private final ShipRepository repository;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${queue.dispatch}")
    private String dispatchQueue;
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

    @GetMapping("/ships/test")
    void test() {
        log.info("BEFORE TEST");

        DispatchTask task = new DispatchTask();
        task.setShipId(1);
        task.setType("INTERNAL");
        task.setDispatched(true);
        jmsTemplate.convertAndSend(dispatchQueue, task);
        log.info("AFTER TEST");

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