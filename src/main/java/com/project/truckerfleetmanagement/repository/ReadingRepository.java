package com.project.truckerfleetmanagement.repository;

import com.project.truckerfleetmanagement.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, UUID> {

}
