package com.weg.school_management_api.mapper;

import com.weg.school_management_api.dto.aluno.AlunoRequisicaoDTO;
import com.weg.school_management_api.dto.aluno.AlunoRespostaDTO;
import com.weg.school_management_api.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade (AlunoRequisicaoDTO requisicaoDTO) {
        return new Aluno (
                requisicaoDTO.nome(),
                requisicaoDTO.email(),
                requisicaoDTO.matricula(),
                requisicaoDTO.dataNascimento()
        );
    }

    public AlunoRespostaDTO paraResposta (Aluno aluno) {
        return new AlunoRespostaDTO (
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getDataNascimento()
        );
    }
}
