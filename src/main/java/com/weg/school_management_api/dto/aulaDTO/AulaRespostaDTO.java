package com.weg.school_management_api.dto.aulaDTO;

import java.time.LocalTime;

public record AulaRespostaDTO(
        long id,
        String nomeTurma,
        LocalTime data_hora,
        String assunto
) {
}
