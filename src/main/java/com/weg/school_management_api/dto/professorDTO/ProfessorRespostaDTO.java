package com.weg.school_management_api.dto.professorDTO;

public record ProfessorRespostaDTO(
        long id,
        String nome,
        String email,
        String disciplina
) {
}
