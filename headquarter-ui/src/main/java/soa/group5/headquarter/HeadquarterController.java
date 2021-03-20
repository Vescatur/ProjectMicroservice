package soa.group5.headquarter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class HeadquarterController {

    HeadquarterController() {
    }

    List<Order> getOrders(){
        var list = new ArrayList<Order>();
        list.add(new Order("Earth","Mars"));
        list.add(new Order("Venus","Mars"));
        list.add(new Order("Pluto","XFS-342"));
        list.add(new Order("EMV-743","Venus"));
        list.add(new Order("EMV-743","XFS-342"));
        list.add(new Order("Earth-433","428-ZXY"));
        return list;
    }

    List<Ship> getShips(RestTemplate restTemplate){
        ResponseEntity<Ship[]> ships = restTemplate.getForEntity("http://localhost:8081/ships", Ship[].class);
        return Arrays.asList(Objects.requireNonNull(ships.getBody()));
    }

    @GetMapping("/generateSchedule")
    List<Schedule> all(RestTemplate restTemplate) {
        var orders = getOrders();
        var ships = getShips(restTemplate);
        var list = new ArrayList<Schedule>();
        var shipIndex = 0;
        for(int i = 0;i<orders.size();i++){
            var order = orders.get(i);
            var ship = ships.get(shipIndex);
            list.add(new Schedule(order.getStart(),order.getEnd(),ship.getId()));
            shipIndex = (shipIndex+1) % ships.size();
        }

        return list;
    }


    @PostMapping("/saveSchedule")
    List<Schedule> newSchedule(RestTemplate restTemplate,@RequestBody List<Schedule> newSchedule) {
        var responseSchedules = new ArrayList<Schedule>();

        newSchedule.forEach(schedule -> {
            var response = restTemplate.postForEntity("http://localhost:8083/schedules",
                    schedule,Schedule.class);
            responseSchedules.add(response.getBody());
        });

        return responseSchedules;
    }


}
