package com.escolamusicaapi.controller;

import com.escolamusicaapi.dto.AlunoRequestDTO;
import com.escolamusicaapi.dto.AlunoResponseDTO;
import com.escolamusicaapi.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Aluno", description = "Gerenciamento de alunos")
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Operation(summary = "Listar todos os Aluno")
    @GetMapping
    public List<AlunoResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<AlunoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Aluno por ID")
    @GetMapping("/{id}")
    public AlunoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Aluno")
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criar(@Valid @RequestBody AlunoRequestDTO aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(aluno));
    }

    @Operation(summary = "Atualizar Aluno")
    @PutMapping("/{id}")
    public AlunoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO aluno) {
        return service.atualizar(id, aluno);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Aluno")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
