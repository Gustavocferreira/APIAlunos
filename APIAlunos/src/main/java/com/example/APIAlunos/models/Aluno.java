package com.example.APIAlunos.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno {

    private Long id;
    private String nome;
    private String email;
    private String dataNascimento;


    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {  // ✅ Getter correto (sem parâmetro)
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
