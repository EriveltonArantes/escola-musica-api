package com.escolamusicaapi.mapper;

import com.escolamusicaapi.dto.ProfessorRequestDTO;
import com.escolamusicaapi.dto.ProfessorResponseDTO;
import com.escolamusicaapi.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mapping(target = "aluno", ignore = true)
    Professor toEntity(ProfessorRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    ProfessorResponseDTO toResponseDTO(Professor entity);
}
