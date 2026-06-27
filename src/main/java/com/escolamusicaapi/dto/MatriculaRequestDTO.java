package com.escolamusicaapi.dto;

import jakarta.validation.constraints.*;

public class MatriculaRequestDTO {

    @NotNull(message = "AlunoId é obrigatório")
    @Positive(message = "AlunoId deve ser um ID válido (positivo)")
    private Long alunoId;
    @NotNull(message = "ProfessorId é obrigatório")
    @Positive(message = "ProfessorId deve ser um ID válido (positivo)")
    private Long professorId;
    @NotBlank(message = "instrumento não pode estar em branco")
    private String instrumento;
    @NotBlank(message = "nivel não pode estar em branco")
    private String nivel;
    @FutureOrPresent(message = "data inicio não pode ser retroativo")
    @NotNull(message = "data inicio não pode ser nulo")
    private java.time.LocalDateTime dataInicio;
    @DecimalMin(value = "0.0", message = "mensalidade não pode ser negativo")
    @NotNull(message = "mensalidade não pode ser nulo")
    private java.math.BigDecimal mensalidade;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public Long getProfessorId() { return professorId; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
    public String getInstrumento() { return instrumento; }
    public void setInstrumento(String instrumento) { this.instrumento = instrumento; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public java.time.LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(java.time.LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
    public java.math.BigDecimal getMensalidade() { return mensalidade; }
    public void setMensalidade(java.math.BigDecimal mensalidade) { this.mensalidade = mensalidade; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
