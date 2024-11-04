package com.shuttle_service.state;

import org.springframework.http.ResponseEntity;

public class ServiceUnavailableState implements ShuttleRequestState {

    @Override
    public ResponseEntity<String> handleRequest(Integer suid) {
        return ResponseEntity.status(503).body("Service Unavailable");
    }
}
