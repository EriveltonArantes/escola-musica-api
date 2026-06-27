package com.escolamusicaapi.controller;

import com.escolamusicaapi.dto.ProfessorRequestDTO;
import com.escolamusicaapi.dto.ProfessorResponseDTO;
import com.escolamusicaapi.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Professor", description = "Gerenciamento de professors")
@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @Operation(summary = "Listar todos os Professor")
    @GetMapping
    public List<ProfessorResponseDTO> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) Long alunoId) {
        List<ProfessorResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (alunoId != null) {
            resultado = resultado.stream().filter(item -> alunoId.equals(item.getAlunoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Professor por ID")
    @GetMapping("/{id}")
    public ProfessorResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Professor")
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criar(@Valid @RequestBody ProfessorRequestDTO professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(professor));
    }

    @Operation(summary = "Atualizar Professor")
    @PutMapping("/{id}")
    public ProfessorResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProfessorRequestDTO professor) {
        return service.atualizar(id, professor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Professor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
