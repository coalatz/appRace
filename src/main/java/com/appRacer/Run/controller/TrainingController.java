package com.appRacer.Run.controller;

import com.appRacer.Run.model.TrainingModel;
import com.appRacer.Run.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/training")
@Tag(name = "Training", description = "Training related operations")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @Operation(summary = "Registrar um novo treinamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treinamento registrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrainingModel.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TrainingModel> registerTraining(@RequestBody @Valid TrainingModel training) {
        TrainingModel savedTraining = trainingService.save(training);
        return ResponseEntity.ok(savedTraining);
    }


    @Operation(summary = "Listar todos os treinamentos")
    @ApiResponse(responseCode = "200", description = "Lista de treinamentos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrainingModel.class)))
    @GetMapping
    public ResponseEntity<List<TrainingModel>> listAll() {
        List<TrainingModel> list = trainingService.listAll();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Buscar treinamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treinamento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrainingModel.class))),
            @ApiResponse(responseCode = "404", description = "Treinamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainingModel> findById(@PathVariable Long id) {
        TrainingModel training = trainingService.findById(id);
        return ResponseEntity.ok(training);
    }
}