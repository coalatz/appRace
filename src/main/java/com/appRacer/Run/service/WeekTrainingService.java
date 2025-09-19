package com.appRacer.Run.service;

import com.appRacer.Run.model.WeekTrainingModel;
import com.appRacer.Run.repository.WeekTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeekTrainingService {

    @Autowired
    private WeekTrainingRepository repository;

    public WeekTrainingModel save(WeekTrainingModel week) {
        updateProgress(week);
        return repository.save(week);
    }

    public WeekTrainingModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semana não encontrada com ID: " + id));
    }

    public WeekTrainingModel findByStartDate(LocalDate startDate) {
        return repository.findByStartDate(startDate)
                .orElseThrow(() -> new RuntimeException("Semana não encontrada com data: " + startDate));
    }

    public List<WeekTrainingModel> listAll() {
        return repository.findAll();
    }

    public WeekTrainingModel updateProgress(Long id) {
        WeekTrainingModel week = findById(id);
        updateProgress(week);
        return repository.save(week);
    }

    private void updateProgress(WeekTrainingModel week) {
        long total = week.getTrainings().size();
        long done = week.getTrainings().stream().filter(t -> t.isCompleted()).count();
        float rate = total > 0 ? (done * 100f) / total : 0f;
        week.setCompletionRate(rate);
        week.setCompleted(rate == 100f);
    }
}
