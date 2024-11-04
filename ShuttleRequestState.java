package com.shuttle_service.state;

import org.springframework.http.ResponseEntity;

public interface ShuttleRequestState {
    ResponseEntity<String> handleRequest(Integer suid);
}
