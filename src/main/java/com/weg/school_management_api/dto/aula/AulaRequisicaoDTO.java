package com.weg.school_management_api.dto.aula;

import java.time.LocalDateTime;

public record AulaRequisicaoDTO(
        long turmaId,
        LocalDateTime dataHora,
        String assunto
) {
}
