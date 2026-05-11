package com.weg.school_management_api.controller;

public class NotaController {
    private long id;
    private long aluno_id;
    private long aula_id;
    private float valor;

    public NotaController(long aluno_id, long aula_id, float valor) {
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }

    public NotaController(long id, long aluno_id, long aula_id, float valor) {
        this.id = id;
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(long aluno_id) {
        this.aluno_id = aluno_id;
    }

    public long getAula_id() {
        return aula_id;
    }

    public void setAula_id(long aula_id) {
        this.aula_id = aula_id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
