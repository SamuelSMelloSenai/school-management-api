package com.weg.school_management_api.dto.aluno;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AlunoRequisicaoDTO(
        @NotBlank(message = "O nome é obrigatório!")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 a 100 caracteres!")
        String nome,

        @NotBlank(message = "O email é obrigatório!")
        @Email(message = "E-mail invalido!")
        String email,

        @NotBlank(message = "A matrícula é obrigatória!")
        @Size(min = 3, max = 20, message = "A matrícula deve ter entre 3 a 20 caractere numéricos!")
        String matricula,

        @NotBlank(message = "A data de nascimento é obrigatória!")
        @Past
        LocalDate dataNascimento
) {
}
