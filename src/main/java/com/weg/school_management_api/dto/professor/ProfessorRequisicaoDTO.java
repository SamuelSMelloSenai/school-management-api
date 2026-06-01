package com.weg.school_management_api.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfessorRequisicaoDTO(
        @NotBlank(message = "O nome do professor é obrigatório!")
        @Size(min = 3, max = 100, message = "O nome do professor deve ter entre 3 a 100 caracteres!")
        String nome,

        @NotBlank(message = "O e-mail do professor é obrigatório!")
        @Email(message = "Email inválido!")
        String email,

        @NotBlank(message = "A disciplina do professor é obrigatório!")
        @Size(min = 3, max = 100, message = "A disciplina do professor deve ter entre 3 a 100 caracteres!")
        String disciplina
) {
}
