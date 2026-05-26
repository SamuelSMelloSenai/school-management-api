package com.weg.school_management_api.dto.aluno;

import java.time.LocalDate;

public record AlunoRespostaDTO (
        long id,
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
