package com.escolamusicaapi.controller;

import com.escolamusicaapi.dto.AulaRequestDTO;
import com.escolamusicaapi.dto.AulaResponseDTO;
import com.escolamusicaapi.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Aula", description = "Gerenciamento de aulas")
@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaService service;

    @Operation(summary = "Listar todos os Aula")
    @GetMapping
    public List<AulaResponseDTO> listar(@RequestParam(required = false) String instrumento, @RequestParam(required = false) Long professorId, @RequestParam(required = false) Long alunoId) {
        List<AulaResponseDTO> resultado = service.listar();
        if (instrumento != null && !instrumento.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getInstrumento() != null &&
                item.getInstrumento().toLowerCase().contains(instrumento.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (professorId != null) {
            resultado = resultado.stream().filter(item -> professorId.equals(item.getProfessorId())).collect(java.util.stream.Collectors.toList());
        }
        if (alunoId != null) {
            resultado = resultado.stream().filter(item -> alunoId.equals(item.getAlunoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Aula por ID")
    @GetMapping("/{id}")
    public AulaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Aula")
    @PostMapping
    public ResponseEntity<AulaResponseDTO> criar(@Valid @RequestBody AulaRequestDTO aula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(aula));
    }

    @Operation(summary = "Atualizar Aula")
    @PutMapping("/{id}")
    public AulaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AulaRequestDTO aula) {
        return service.atualizar(id, aula);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Aula")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
