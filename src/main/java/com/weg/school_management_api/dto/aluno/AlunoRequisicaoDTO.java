package com.weg.school_management_api.dto.aluno;

import java.time.LocalDate;

public record AlunoRequisicaoDTO(
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
