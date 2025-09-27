package com.appRacer.Run.repository;

import com.appRacer.Run.model.WeekTrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeekTrainingRepository extends JpaRepository<WeekTrainingModel, Long> {
    Optional<WeekTrainingModel> findByStartDate(LocalDate startDate);
}
