package com.weg.school_management_api.mapper;

import com.weg.school_management_api.dto.TurmaDTO.TurmaRequisicaoDTO;
import com.weg.school_management_api.dto.TurmaDTO.TurmaRespostaDTO;
import com.weg.school_management_api.model.Turma;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurmaMapper {

    public Turma paraEntidade(TurmaRequisicaoDTO requisicaoDTO) {
        return new Turma (
                requisicaoDTO.nome(),
                requisicaoDTO.cursoId(),
                requisicaoDTO.professorId()
        );
    }

    public TurmaRespostaDTO paraResposta (Turma turma, String nomeCurso, String nomeProfessor, List<String> nomeAlunos) {
        return new TurmaRespostaDTO (
                turma.getId(),
                turma.getNome(),
                nomeCurso,
                nomeProfessor,
                nomeAlunos
        );
    }
}
