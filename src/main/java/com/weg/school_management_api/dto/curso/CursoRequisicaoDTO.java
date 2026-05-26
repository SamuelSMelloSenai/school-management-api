package com.weg.school_management_api.dto.curso;

import java.util.List;

public record CursoRequisicaoDTO (
        String nome,
        String codigo,
        List<Long> professoresIds
) {
}
