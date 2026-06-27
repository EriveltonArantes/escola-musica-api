package com.escolamusicaapi.mapper;

import com.escolamusicaapi.dto.AlunoRequestDTO;
import com.escolamusicaapi.dto.AlunoResponseDTO;
import com.escolamusicaapi.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    Aluno toEntity(AlunoRequestDTO dto);

    AlunoResponseDTO toResponseDTO(Aluno entity);
}
