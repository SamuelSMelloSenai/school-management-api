package com.weg.school_management_api.dto.nota;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NotaRequisicaoDTO (
        @NotNull(message = "O id do aluno é obrigatório!")
        @Positive(message = "O id do aluno deve ser positivo!")
        long alunoId,

        @NotNull(message = "O id da aula é obrigatorio!")
        @Positive(message = "O id da aula deve ser positivo!")
        long aulaId,

        @NotNull(message = "A nota do aluno é obrigatória!")
        @Min(value = 0, message = "A nota mínima é 0!")
        @Max(value = 10, message = "A nota máxima é 10!")
        float valor
){
}
