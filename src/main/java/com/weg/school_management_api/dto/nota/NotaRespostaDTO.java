package com.weg.school_management_api.dto.nota;

public record NotaRespostaDTO (
        long id,
        String nomeAluno,
        String aulaAssunto,
        float valor
){
}
