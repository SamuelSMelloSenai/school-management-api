package com.weg.school_management_api.mapper;

import com.weg.school_management_api.dto.curso.CursoRequisicaoDTO;
import com.weg.school_management_api.dto.curso.CursoRespostaDTO;
import com.weg.school_management_api.model.Curso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {

    public Curso paraEntidade (CursoRequisicaoDTO requisicaoDTO) {
        return new Curso (
                requisicaoDTO.nome(),
                requisicaoDTO.codigo()
        );
    }

    public CursoRespostaDTO paraResposta (Curso curso, List<String> nomeProfessores) {
        return new CursoRespostaDTO (
                curso.getId(),
                curso.getNome(),
                curso.getCodigo(),
                nomeProfessores
        );
    }
}
