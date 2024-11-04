package com.shuttle_service.state;

import com.shuttle_service.model.ETACalculation;
import com.shuttle_service.model.ShuttleRequest;
import com.shuttle_service.repository.ETACalculationRepository;
import com.shuttle_service.repository.ShuttleRequestRepository;
import org.springframework.http.ResponseEntity;

public class ValidRequestState implements ShuttleRequestState {

    private final ShuttleRequestRepository shuttleRequestRepository;
    private final ETACalculationRepository etaCalculationRepository;
    private final int eta;
    private final Integer suid;

    public ValidRequestState(ShuttleRequestRepository shuttleRequestRepository,
                             ETACalculationRepository etaCalculationRepository, int eta, Integer suid) {
        this.shuttleRequestRepository = shuttleRequestRepository;
        this.etaCalculationRepository = etaCalculationRepository;
        this.eta = eta;
        this.suid = suid;
    }

    @Override
    public ResponseEntity<String> handleRequest(Integer suid) {
        // Save the request with PENDING status
        ShuttleRequest newRequest = new ShuttleRequest();
        newRequest.setSuid(suid);
        newRequest.setStatus("PENDING");
        shuttleRequestRepository.save(newRequest);

        // Save the suid and ETA to eta_calculation table
        ETACalculation etaCalculation = new ETACalculation();
        etaCalculation.setSuid(suid);
        etaCalculation.setEta(eta);
        etaCalculationRepository.save(etaCalculation);

        return ResponseEntity.ok("Pickup requested, ETA: " + eta + " seconds");
    }
}
