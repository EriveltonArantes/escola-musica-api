package com.escolamusicaapi.dto;

import jakarta.validation.constraints.*;

public class AulaRequestDTO {

    @NotNull(message = "ProfessorId é obrigatório")
    @Positive(message = "ProfessorId deve ser um ID válido (positivo)")
    private Long professorId;
    @NotNull(message = "AlunoId é obrigatório")
    @Positive(message = "AlunoId deve ser um ID válido (positivo)")
    private Long alunoId;
    @NotBlank(message = "instrumento não pode estar em branco")
    private String instrumento;
    @FutureOrPresent(message = "data hora não pode ser retroativo")
    @NotNull(message = "data hora não pode ser nulo")
    private java.time.LocalDateTime dataHora;
    @NotNull(message = "duracao não pode ser nulo")
    private Integer duracao;
    @DecimalMin(value = "0.0", message = "valor não pode ser negativo")
    @NotNull(message = "valor não pode ser nulo")
    private java.math.BigDecimal valor;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    public Long getProfessorId() { return professorId; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public String getInstrumento() { return instrumento; }
    public void setInstrumento(String instrumento) { this.instrumento = instrumento; }
    public java.time.LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(java.time.LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
