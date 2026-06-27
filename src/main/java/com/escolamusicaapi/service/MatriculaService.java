package com.escolamusicaapi.service;

import com.escolamusicaapi.dto.MatriculaRequestDTO;
import com.escolamusicaapi.dto.MatriculaResponseDTO;
import com.escolamusicaapi.exception.ResourceNotFoundException;
import com.escolamusicaapi.mapper.MatriculaMapper;
import com.escolamusicaapi.model.Matricula;
import com.escolamusicaapi.repository.MatriculaRepository;
import com.escolamusicaapi.repository.AlunoRepository;
import com.escolamusicaapi.model.Aluno;
import com.escolamusicaapi.repository.ProfessorRepository;
import com.escolamusicaapi.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private MatriculaMapper mapper;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public List<MatriculaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MatriculaResponseDTO buscar(Long id) {
        Matricula entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Matricula não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public MatriculaResponseDTO criar(MatriculaRequestDTO dto) {
        Matricula entity = mapper.toEntity(dto);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Professor professor = professorRepository.findById(dto.getProfessorId())
            .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com id: " + dto.getProfessorId()));
        entity.setProfessor(professor);
        Matricula salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public MatriculaResponseDTO atualizar(Long id, MatriculaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Matricula não encontrado com id: " + id);
        }
        Matricula entity = mapper.toEntity(dto);
        entity.setId(id);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Professor professor = professorRepository.findById(dto.getProfessorId())
            .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com id: " + dto.getProfessorId()));
        entity.setProfessor(professor);
        Matricula salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Matricula não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
