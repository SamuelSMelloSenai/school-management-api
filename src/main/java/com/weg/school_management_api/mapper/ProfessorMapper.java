package com.weg.school_management_api.mapper;

import com.weg.school_management_api.dto.professorDTO.ProfessorRequisicaoDTO;
import com.weg.school_management_api.dto.professorDTO.ProfessorRespostaDTO;
import com.weg.school_management_api.model.Professor;

public class ProfessorMapper {

    public Professor paraEntidade (ProfessorRequisicaoDTO requisicaoDTO) {
        return new Professor (
                requisicaoDTO.nome(),
                requisicaoDTO.email(),
                requisicaoDTO.disciplina()
        );
    }

    public ProfessorRespostaDTO paraResposta (Professor professor) {
        return new ProfessorRespostaDTO (
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina()
        );
    }
}
