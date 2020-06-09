package com.project.truckerfleetmanagement.repository;

import com.project.truckerfleetmanagement.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, UUID> {
    public List<Reading> findAllByVinAndTimestampIsGreaterThanEqual(String vin, LocalDateTime time);
}
