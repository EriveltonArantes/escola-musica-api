package com.escolamusicaapi.controller;

import com.escolamusicaapi.dto.MatriculaRequestDTO;
import com.escolamusicaapi.dto.MatriculaResponseDTO;
import com.escolamusicaapi.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Matricula", description = "Gerenciamento de matriculas")
@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    @Operation(summary = "Listar todos os Matricula")
    @GetMapping
    public List<MatriculaResponseDTO> listar(@RequestParam(required = false) String instrumento, @RequestParam(required = false) Long alunoId, @RequestParam(required = false) Long professorId) {
        List<MatriculaResponseDTO> resultado = service.listar();
        if (instrumento != null && !instrumento.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getInstrumento() != null &&
                item.getInstrumento().toLowerCase().contains(instrumento.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (alunoId != null) {
            resultado = resultado.stream().filter(item -> alunoId.equals(item.getAlunoId())).collect(java.util.stream.Collectors.toList());
        }
        if (professorId != null) {
            resultado = resultado.stream().filter(item -> professorId.equals(item.getProfessorId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Matricula por ID")
    @GetMapping("/{id}")
    public MatriculaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Matricula")
    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> criar(@Valid @RequestBody MatriculaRequestDTO matricula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(matricula));
    }

    @Operation(summary = "Atualizar Matricula")
    @PutMapping("/{id}")
    public MatriculaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody MatriculaRequestDTO matricula) {
        return service.atualizar(id, matricula);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Matricula")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
