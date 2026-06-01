package com.weg.school_management_api.dto.turma;

import jakarta.validation.constraints.*;

import java.util.List;

public record TurmaRequisicaoDTO(
        @NotBlank(message = "O nome da turma é obrigatório!")
        @Size(min = 3, max = 50, message = "O nome da turma deve ter entre 3 a 50 caracteres!")
        String nome,

        @NotNull(message = "O id do curso é obrigatorio!")
        @Positive(message = "O id do curso deve ser positivo!")
        long cursoId,

        @NotNull(message = "O id da aula é obrigatorio!")
        @Positive(message = "O id da aula deve ser positivo!")
        long professorId,

        @NotEmpty(message = "A lista de professores deve ter pelo menos 1 valor!")
        List<Long> alunosIds
) {
}
