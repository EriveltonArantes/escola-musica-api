package com.escolamusicaapi.dto;

public class ProfessorResponseDTO {

    private Long id;
    private Long alunoId;
    private String nome;
    private String email;
    private String instrumentos;
    private String bio;
    private Boolean disponivel;
    private String telefone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
