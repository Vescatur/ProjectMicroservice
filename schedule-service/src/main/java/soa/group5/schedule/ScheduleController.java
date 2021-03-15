package soa.group5.schedule;

import java.util.List;

import soa.group5.schedule.ScheduleNotFoundException;
import soa.group5.schedule.Schedule;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soa.group5.schedule.ScheduleRepository;

@RestController
public class ScheduleController {

    private final ScheduleRepository repository;

    ScheduleController(ScheduleRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/schedules")
    List<Schedule> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/schedules")
    Schedule newSchedule(@RequestBody Schedule newSchedule) {
        return repository.save(newSchedule);
    }

    // Single item

    @GetMapping("/schedules/{id}")
    Schedule one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
    }

    @PutMapping("/schedules/{id}")
    Schedule replaceSchedule(@RequestBody Schedule newSchedule, @PathVariable Long id) {

        return repository.findById(id)
                .map(schedule -> {
                    schedule.setStart(newSchedule.getStart());
                    schedule.setEnd(newSchedule.getEnd());
                    return repository.save(schedule);
                })
                .orElseGet(() -> {
                    newSchedule.setId(id);
                    return repository.save(newSchedule);
                });
    }

    @DeleteMapping("/schedules/{id}")
    void deleteSchedule(@PathVariable Long id) {
        repository.deleteById(id);
    }
}