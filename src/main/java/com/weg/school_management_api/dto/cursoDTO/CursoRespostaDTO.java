package com.weg.school_management_api.dto.cursoDTO;

import com.weg.school_management_api.model.Professor;

import java.util.List;

public record CursoRespostaDTO (
        long id,
        String nome,
        String codigo,
        List<Professor> professoresNomes
) {
}
