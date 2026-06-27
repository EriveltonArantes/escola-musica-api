package com.escolamusicaapi.dto;

import jakarta.validation.constraints.*;

public class ProfessorRequestDTO {

    @NotNull(message = "AlunoId é obrigatório")
    @Positive(message = "AlunoId deve ser um ID válido (positivo)")
    private Long alunoId;
    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "email não pode estar em branco")
    @Email(message = "email precisa ser um e-mail válido")
    private String email;
    @NotBlank(message = "instrumentos não pode estar em branco")
    private String instrumentos;
    @NotBlank(message = "bio não pode estar em branco")
    private String bio;
    @NotNull(message = "disponivel não pode ser nulo")
    private Boolean disponivel;
    @NotBlank(message = "telefone não pode estar em branco")
    private String telefone;

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getInstrumentos() { return instrumentos; }
    public void setInstrumentos(String instrumentos) { this.instrumentos = instrumentos; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public Boolean getDisponivel() { return disponivel; }
    public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
