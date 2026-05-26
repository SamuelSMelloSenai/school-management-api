package com.weg.school_management_api.dto.professor;

public record ProfessorRespostaDTO(
        long id,
        String nome,
        String email,
        String disciplina
) {
}
