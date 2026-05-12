package com.weg.school_management_api.dto.aulaDTO;

import java.time.LocalTime;

public record AulaRequisicaoDTO(
        long turma_id,
        LocalTime data_hora,
        String assunto
) {
}
