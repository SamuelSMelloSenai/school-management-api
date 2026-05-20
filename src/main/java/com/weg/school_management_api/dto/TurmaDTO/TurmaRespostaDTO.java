package com.weg.school_management_api.dto.TurmaDTO;

import java.util.List;

public record TurmaRespostaDTO(
        long id,
        String nome,
        String nomeCurso,
        String nomeProfessor,
        List<String> alunosNomes
) {
}
