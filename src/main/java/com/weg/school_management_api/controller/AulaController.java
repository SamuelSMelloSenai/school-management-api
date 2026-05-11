package com.weg.school_management_api.controller;

import java.time.LocalTime;

public class AulaController {
    private long id;
    private long turma_id;
    private LocalTime data_hora;
    private String assunto;

    public AulaController(long turma_id, LocalTime data_hora, String assunto) {
        this.turma_id = turma_id;
        this.data_hora = data_hora;
        this.assunto = assunto;
    }

    public AulaController(long id, long turma_id, LocalTime data_hora, String assunto) {
        this.id = id;
        this.turma_id = turma_id;
        this.data_hora = data_hora;
        this.assunto = assunto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(long turma_id) {
        this.turma_id = turma_id;
    }

    public LocalTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalTime data_hora) {
        this.data_hora = data_hora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
