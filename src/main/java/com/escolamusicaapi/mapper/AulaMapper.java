package com.escolamusicaapi.mapper;

import com.escolamusicaapi.dto.AulaRequestDTO;
import com.escolamusicaapi.dto.AulaResponseDTO;
import com.escolamusicaapi.model.Aula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AulaMapper {

    @Mapping(target = "professor", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    Aula toEntity(AulaRequestDTO dto);

    @Mapping(target = "professorId", source = "professor.id")
    @Mapping(target = "alunoId", source = "aluno.id")
    AulaResponseDTO toResponseDTO(Aula entity);
}
