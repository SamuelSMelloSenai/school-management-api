package com.weg.school_management_api.model;

public class Nota {
    private long id;
    private long alunoId;
    private long aulaId;
    private float valor;

    public Nota(long id, long alunoId, long aulaId, float valor) {
        this.id = id;
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valor = valor;
    }

    public Nota(long alunoId, long aulaId, float valor) {
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valor = valor;
    }

    public Nota() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(long alunoId) {
        this.alunoId = alunoId;
    }

    public long getAulaId() {
        return aulaId;
    }

    public void setAulaId(long aulaId) {
        this.aulaId = aulaId;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
