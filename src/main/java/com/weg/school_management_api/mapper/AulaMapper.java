package com.weg.school_management_api.mapper;

import com.weg.school_management_api.dto.aula.AulaRequisicaoDTO;
import com.weg.school_management_api.dto.aula.AulaRespostaDTO;
import com.weg.school_management_api.model.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade (AulaRequisicaoDTO requisicaoDTO) {
        return new Aula (
                requisicaoDTO.turmaId(),
                requisicaoDTO.dataHora(),
                requisicaoDTO.assunto()
        );
    }

    public AulaRespostaDTO paraResposta (Aula aula, String nomeTurma) {
        return new AulaRespostaDTO (
                aula.getId(),
                nomeTurma,
                aula.getDataHora(),
                aula.getAssunto()
        );
    }
}
