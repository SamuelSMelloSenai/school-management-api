package com.weg.school_management_api.dto.TurmaDTO;

import com.weg.school_management_api.model.Aluno;

import java.util.List;

public record TurmaRequisicaoDTO(
        String nome,
        long curso_id,
        long professor_id,
        List<Aluno> alunosIds
) {
}
