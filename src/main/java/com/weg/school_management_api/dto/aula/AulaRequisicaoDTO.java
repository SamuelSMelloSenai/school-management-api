package com.weg.school_management_api.dto.aula;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record AulaRequisicaoDTO(
        @NotNull(message = "O id da turma é obrigatório!")
        @Positive(message = "O id da turma deve ser positivo!")
        long turmaId,

        @NotNull(message = "A data e hora da aula é obrigatória!")
        @Past
        @Future
        LocalDateTime dataHora,

        @NotBlank(message = "O assunto da aula deve ser obrigatório!")
        @Size(min = 5, max = 100, message = "O assunto da aula deve ter entre 5 a 100 caracteres!")
        String assunto
) {
}
