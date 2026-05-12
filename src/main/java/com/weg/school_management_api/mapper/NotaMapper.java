//package com.weg.school_management_api.mapper;
//
//import com.weg.school_management_api.dto.notaDTO.NotaRequisicaoDTO;
//import com.weg.school_management_api.dto.notaDTO.NotaRespostaDTO;
//import com.weg.school_management_api.model.Nota;
//
//public class NotaMapper {
//
//    public Nota paraEntidade (NotaRequisicaoDTO requisicaoDTO) {
//        return new Nota (
//                requisicaoDTO.aluno_id(),
//                requisicaoDTO.aula_id(),
//                requisicaoDTO.valor()
//        );
//    }
//
//    public NotaRespostaDTO paraResposta (Nota nota) {
//        return new NotaRespostaDTO (
//                nota.getId(),
//                nota.getNomeAluno(),
//                nota.getAulaAssunto(),
//                nota.getValor()
//        );
//    }
//}
//