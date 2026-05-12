package com.weg.school_management_api.mapper;

import com.weg.school_management_api.dto.professorDTO.ProfessorRequisicaoDTO;
import com.weg.school_management_api.dto.professorDTO.ProfessorRespostaDTO;
import com.weg.school_management_api.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade (ProfessorRequisicaoDTO professorRequisicaoDTO) {
        return new Professor (
                professorRequisicaoDTO.nome(),
                professorRequisicaoDTO.email(),
                professorRequisicaoDTO.disciplina()
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
