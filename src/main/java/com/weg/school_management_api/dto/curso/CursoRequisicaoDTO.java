package com.weg.school_management_api.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CursoRequisicaoDTO (
        @NotBlank(message = "O nome é obrigotário!")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 a 100 caracteres!")
        String nome,

        @NotBlank(message = "O codigo é obrigotário!")
        @Size(min = 20, max = 20, message = "O código do curso deve ter 20 caracteres!")
        String codigo,

        @NotEmpty(message = "A lista de professores deve ter pelo menos 1 valor!")
        List<Long> professoresIds
) {
}
