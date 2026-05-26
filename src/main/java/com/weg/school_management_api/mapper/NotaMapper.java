package com.weg.school_management_api.mapper;

import com.weg.school_management_api.dto.nota.NotaRequisicaoDTO;
import com.weg.school_management_api.dto.nota.NotaRespostaDTO;
import com.weg.school_management_api.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota paraEntidade (NotaRequisicaoDTO requisicaoDTO) {
        return new Nota (
                requisicaoDTO.alunoId(),
                requisicaoDTO.aulaId(),
                requisicaoDTO.valor()
        );
    }

    public NotaRespostaDTO paraResposta (Nota nota, String nomeAluno, String aulaAssunto) {
        return new NotaRespostaDTO (
                nota.getId(),
                nomeAluno,
                aulaAssunto,
                nota.getValor()
        );
    }
}
