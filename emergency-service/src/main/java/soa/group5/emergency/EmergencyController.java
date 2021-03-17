package soa.group5.emergency;

import java.util.List;

import soa.group5.emergency.EmergencyNotFoundException;
import soa.group5.emergency.Emergency;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soa.group5.emergency.EmergencyRepository;

@RestController
public class EmergencyController {

    private final EmergencyRepository repository;

    EmergencyController(EmergencyRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/emergencies")
    List<Emergency> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/emergencies")
    Emergency newEmergency(@RequestBody Emergency newEmergency) {
        return repository.save(newEmergency);
    }

    // Single item

    @GetMapping("/emergencies/{id}")
    Emergency one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmergencyNotFoundException(id));
    }

    @PutMapping("/emergencies/{id}")
    Emergency replaceEmergency(@RequestBody Emergency newEmergency, @PathVariable Long id) {

        return repository.findById(id)
                .map(Emergency -> {
                    Emergency.setEmergencyType(newEmergency.getEmergencyType());
                    return repository.save(Emergency);
                })
                .orElseGet(() -> {
                    newEmergency.setId(id);
                    return repository.save(newEmergency);
                });
    }

    @DeleteMapping("/emergencies/{id}")
    void deleteEmergency(@PathVariable Long id) {
        repository.deleteById(id);
    }
}