package soa.group5.headquarter;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HeadquarterController {

    HeadquarterController() {
    }


    @GetMapping("/generateSchedule")
    List<Schedule> all() {
        var list = new ArrayList<Schedule>();
        list.add(new Schedule());
        return list;
    }


    @PostMapping("/saveSchedule")
    Schedule newSchedule(@RequestBody Schedule newSchedule) {

        return newSchedule;
    }



}
