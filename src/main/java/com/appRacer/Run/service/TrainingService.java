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

        public TrainingModel save(TrainingModel treino) {
            return trainingRepository.save(treino);
        }

        public List<TrainingModel> listAll() {
            return trainingRepository.findAll();
        }

        public TrainingModel findById(Long id) {
            return trainingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Treino não encontrado com ID: " + id));
        }

        public TrainingModel update(Long id, TrainingModel treinoAtualizado) {

            TrainingModel treino = findById(id);
            treino.setType(treinoAtualizado.getType());
            treino.setDistanceKm(treinoAtualizado.getDistanceKm());
            treino.setTargetPace(treinoAtualizado.getTargetPace());
            treino.setIntensity(treinoAtualizado.getIntensity());
            treino.setCompleted(treinoAtualizado.isCompleted());
            treino.setNotes(treinoAtualizado.getNotes());

            return trainingRepository.save(treino);
        }

        public void delete(Long id) {
            trainingRepository.deleteById(id);
        }
    }