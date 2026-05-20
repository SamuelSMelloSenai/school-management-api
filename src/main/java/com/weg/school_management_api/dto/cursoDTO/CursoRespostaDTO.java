package com.weg.school_management_api.dto.cursoDTO;

import java.util.List;

public record CursoRespostaDTO (
        long id,
        String nome,
        String codigo,
        List<String> professoresNomes
) {
}
