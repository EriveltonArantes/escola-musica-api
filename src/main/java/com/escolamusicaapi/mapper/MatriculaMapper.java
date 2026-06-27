package com.escolamusicaapi.mapper;

import com.escolamusicaapi.dto.MatriculaRequestDTO;
import com.escolamusicaapi.dto.MatriculaResponseDTO;
import com.escolamusicaapi.model.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "professor", ignore = true)
    Matricula toEntity(MatriculaRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "professorId", source = "professor.id")
    MatriculaResponseDTO toResponseDTO(Matricula entity);
}
