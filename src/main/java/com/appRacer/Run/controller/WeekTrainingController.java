package com.appRacer.Run.controller;

import com.appRacer.Run.model.WeekTrainingModel;
import com.appRacer.Run.service.WeekTrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/week-training")
@Tag(name = "Week Training", description = "Operações relacionadas ao planejamento semanal de treinos")
public class WeekTrainingController {

    @Autowired
    private WeekTrainingService service;

    @Operation(summary = "Criar uma nova semana de treino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Semana criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeekTrainingModel.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<WeekTrainingModel> createWeek(@RequestBody @Valid WeekTrainingModel week) {
        WeekTrainingModel saved = service.save(week);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Listar todas as semanas de treino")
    @ApiResponse(responseCode = "200", description = "Lista de semanas",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeekTrainingModel.class)))
    @GetMapping
    public ResponseEntity<List<WeekTrainingModel>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @Operation(summary = "Buscar semana por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Semana encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeekTrainingModel.class))),
            @ApiResponse(responseCode = "404", description = "Semana não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WeekTrainingModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Buscar semana por data de início")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Semana encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeekTrainingModel.class))),
            @ApiResponse(responseCode = "404", description = "Semana não encontrada")
    })
    @GetMapping("/by-date")
    public ResponseEntity<WeekTrainingModel> findByStartDate(@RequestParam("startDate") String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return ResponseEntity.ok(service.findByStartDate(date));
    }

    @Operation(summary = "Atualizar progresso da semana")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Progresso atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeekTrainingModel.class))),
            @ApiResponse(responseCode = "404", description = "Semana não encontrada")
    })
    @PutMapping("/{id}/progress")
    public ResponseEntity<WeekTrainingModel> updateProgress(@PathVariable Long id) {
        return ResponseEntity.ok(service.updateProgress(id));
    }
}
