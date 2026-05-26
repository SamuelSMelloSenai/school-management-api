package com.weg.school_management_api.dto.aula;

import java.time.LocalDateTime;

public record AulaRespostaDTO(
        long id,
        String nomeTurma,
        LocalDateTime dataHora,
        String assunto
) {
}
