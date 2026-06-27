package com.escolamusicaapi.controller;

import com.escolamusicaapi.model.Professor;
import com.escolamusicaapi.model.Aluno;
import com.escolamusicaapi.model.Aula;
import com.escolamusicaapi.model.Matricula;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Dashboard", description = "KPIs e totais do sistema")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.escolamusicaapi.repository.ProfessorRepository professorRepository;

    @Autowired
    private com.escolamusicaapi.repository.AlunoRepository alunoRepository;

    @Autowired
    private com.escolamusicaapi.repository.AulaRepository aulaRepository;

    @Autowired
    private com.escolamusicaapi.repository.MatriculaRepository matriculaRepository;

    @Operation(summary = "Resumo com totais, somas e gráficos de status")
    @Transactional(readOnly = true)
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalProfessor", professorRepository.count());
        resumo.put("totalAluno", alunoRepository.count());
        resumo.put("totalAula", aulaRepository.count());
        resumo.put("somaValorAula", aulaRepository.findAll().stream().map(e -> e.getValor()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        resumo.put("graficoAula", aulaRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalMatricula", matriculaRepository.count());
        resumo.put("somaMensalidadeMatricula", matriculaRepository.findAll().stream().map(e -> e.getMensalidade()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        resumo.put("graficoMatricula", matriculaRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        return resumo;
    }
}
