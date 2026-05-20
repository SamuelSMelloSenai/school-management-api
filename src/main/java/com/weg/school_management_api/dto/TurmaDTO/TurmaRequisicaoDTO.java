package com.weg.school_management_api.dto.TurmaDTO;

import com.weg.school_management_api.model.Aluno;

import java.util.List;

public record TurmaRequisicaoDTO(
        String nome,
        long cursoId,
        long professorId,
        List<Long> alunosIds
) {
}
