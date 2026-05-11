package com.weg.school_management_api.controller;

public class ProfessorController {

    private long id;
    private String nome;
    private String email;
    private String disciplina;

    public ProfessorController(String nome, String email, String disciplina) {
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
    }

    public ProfessorController(long id, String nome, String email, String disciplina) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}
