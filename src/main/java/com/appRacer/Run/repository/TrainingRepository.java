package com.appRacer.Run.repository;

import com.appRacer.Run.model.TrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingModel, Long> {
}
