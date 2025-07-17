package com.appRacer.Run.model;

import jakarta.persistence.*;

@Entity
public class TrainingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private float distanceKm;
    private String targetPace;
    private String intensity;
    private boolean completed;
    private String notes;

    public TrainingModel() {
    }

    public TrainingModel(Long id, String type, float distanceKm, String targetPace,
                         String intensity, boolean completed, String notes) {
        this.id = id;
        this.type = type;
        this.distanceKm = distanceKm;
        this.targetPace = targetPace;
        this.intensity = intensity;
        this.completed = completed;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public float getDistanceKm() { return distanceKm; }
    public void setDistanceKm(float distanceKm) { this.distanceKm = distanceKm; }

    public String getTargetPace() { return targetPace; }
    public void setTargetPace(String targetPace) { this.targetPace = targetPace; }

    public String getIntensity() { return intensity; }
    public void setIntensity(String intensity) { this.intensity = intensity; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
