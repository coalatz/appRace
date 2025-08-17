package com.appRacer.Run.service;

import com.appRacer.Run.model.TrainingModel;
import com.appRacer.Run.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

        @Autowired
        private TrainingRepository trainingRepository;

        public TrainingModel save(TrainingModel training) {
            return trainingRepository.save(training);
        }

        public List<TrainingModel> listAll() {
            return trainingRepository.findAll();
        }

        public TrainingModel findById(Long id) {
            return trainingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Treino não encontrado com ID: " + id));
        }

        public TrainingModel update(Long id, TrainingModel trainingUpdate) {

            TrainingModel training = findById(id);
            training.setType(trainingUpdate.getType());
            training.setDistanceKm(trainingUpdate.getDistanceKm());
            training.setTargetPace(trainingUpdate.getTargetPace());
            training.setIntensity(trainingUpdate.getIntensity());
            training.setCompleted(trainingUpdate.isCompleted());
            training.setNotes(trainingUpdate.getNotes());

            return trainingRepository.save(training);
        }

        public void delete(Long id) {
            trainingRepository.deleteById(id);
        }
    }