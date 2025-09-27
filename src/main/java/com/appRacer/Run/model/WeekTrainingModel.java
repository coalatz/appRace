package com.appRacer.Run.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class WeekTrainingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrainingModel> trainings;

    private boolean completed;

    private float completionRate;

    public WeekTrainingModel() {}

    public WeekTrainingModel(Long id, LocalDate startDate, List<TrainingModel> trainings,
                             boolean completed, float completionRate) {
        this.id = id;
        this.startDate = startDate;
        this.trainings = trainings;
        this.completed = completed;
        this.completionRate = completionRate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public List<TrainingModel> getTrainings() { return trainings; }
    public void setTrainings(List<TrainingModel> trainings) { this.trainings = trainings; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public float getCompletionRate() { return completionRate; }
    public void setCompletionRate(float completionRate) { this.completionRate = completionRate; }
}
