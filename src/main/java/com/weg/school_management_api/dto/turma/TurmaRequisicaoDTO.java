package com.weg.school_management_api.dto.turma;

import java.util.List;

public record TurmaRequisicaoDTO(
        String nome,
        long cursoId,
        long professorId,
        List<Long> alunosIds
) {
}
