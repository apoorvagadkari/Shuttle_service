package com.shuttle_service.state;

import com.shuttle_service.model.Person;
import org.springframework.http.ResponseEntity;

public interface PersonState {
    ResponseEntity<Person> handle(Person person);
}
