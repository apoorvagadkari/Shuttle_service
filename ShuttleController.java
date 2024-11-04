package com.shuttle_service.controller;

import com.shuttle_service.repository.ETACalculationRepository;
import com.shuttle_service.repository.PersonRepository;
import com.shuttle_service.repository.ShuttleRequestRepository;
import com.shuttle_service.state.ServiceUnavailableState;
import com.shuttle_service.state.ShuttleRequestState;
import com.shuttle_service.state.ValidRequestState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/shuttle")
public class ShuttleController {

    private final ShuttleRequestRepository shuttleRequestRepository;
    private final PersonRepository personRepository;
    private final ETACalculationRepository etaCalculationRepository;

    private static final double STUDENT_LAT = 40.730610;
    private static final double STUDENT_LON = -73.935242;
    private double busLat = randomLatitude();
    private double busLon = randomLongitude();

    public ShuttleController(ShuttleRequestRepository shuttleRequestRepository,
                             PersonRepository personRepository,
                             ETACalculationRepository etaCalculationRepository) {
        this.shuttleRequestRepository = shuttleRequestRepository;
        this.personRepository = personRepository;
        this.etaCalculationRepository = etaCalculationRepository;
    }

    @PostMapping("/shuttle_request")
    public ResponseEntity<String> requestPickup(@RequestParam Integer suid) {
        ShuttleRequestState state;

        if (personRepository.findBySuid(suid).isEmpty()) {
            state = new ServiceUnavailableState();
        } else {
            int eta = calculateETA();
            state = new ValidRequestState(shuttleRequestRepository, etaCalculationRepository, eta, suid);
        }

        return state.handleRequest(suid);
    }

    private int calculateETA() {
        double distance = haversineDistance(STUDENT_LAT, STUDENT_LON, busLat, busLon);
        return (int) (distance * 60 / 20);
    }

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private static double randomLatitude() {
        return ThreadLocalRandom.current().nextDouble(-90, 90);
    }

    private static double randomLongitude() {
        return ThreadLocalRandom.current().nextDouble(-180, 180);
    }
}
