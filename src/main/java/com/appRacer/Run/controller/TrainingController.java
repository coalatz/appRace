package com.appRacer.Run.controller;

import com.appRacer.Run.model.TrainingModel;
import com.appRacer.Run.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService treinoService;

    @PostMapping
    public ResponseEntity<TrainingModel> cadastrarTreino(@RequestBody @Valid TrainingModel training) {
        TrainingModel Training = treinoService.save(training);
        return ResponseEntity.ok(training);
    }

    @GetMapping
    public ResponseEntity<List<TrainingModel>> listarTodos() {
        List<TrainingModel> lista = treinoService.listAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingModel> buscarPorId(@PathVariable Long id) {
        TrainingModel training = treinoService.findById(id);
        return ResponseEntity.ok(training);
    }
}