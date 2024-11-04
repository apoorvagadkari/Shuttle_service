package com.shuttle_service.repository;

import com.shuttle_service.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findBySuid(Integer suid);
}
