package com.escolamusicaapi.service;

import com.escolamusicaapi.dto.AulaRequestDTO;
import com.escolamusicaapi.dto.AulaResponseDTO;
import com.escolamusicaapi.exception.ResourceNotFoundException;
import com.escolamusicaapi.mapper.AulaMapper;
import com.escolamusicaapi.model.Aula;
import com.escolamusicaapi.repository.AulaRepository;
import com.escolamusicaapi.repository.ProfessorRepository;
import com.escolamusicaapi.model.Professor;
import com.escolamusicaapi.repository.AlunoRepository;
import com.escolamusicaapi.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AulaService {

    @Autowired
    private AulaRepository repository;

    @Autowired
    private AulaMapper mapper;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public List<AulaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AulaResponseDTO buscar(Long id) {
        Aula entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public AulaResponseDTO criar(AulaRequestDTO dto) {
        Aula entity = mapper.toEntity(dto);
        Professor professor = professorRepository.findById(dto.getProfessorId())
            .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com id: " + dto.getProfessorId()));
        entity.setProfessor(professor);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Aula salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public AulaResponseDTO atualizar(Long id, AulaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Aula não encontrado com id: " + id);
        }
        Aula entity = mapper.toEntity(dto);
        entity.setId(id);
        Professor professor = professorRepository.findById(dto.getProfessorId())
            .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com id: " + dto.getProfessorId()));
        entity.setProfessor(professor);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Aula salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Aula não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
