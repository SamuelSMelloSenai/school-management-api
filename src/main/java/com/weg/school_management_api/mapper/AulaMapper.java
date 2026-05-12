//package com.weg.school_management_api.mapper;
//
//import com.weg.school_management_api.dto.aulaDTO.AulaRequisicaoDTO;
//import com.weg.school_management_api.dto.aulaDTO.AulaRespostaDTO;
//import com.weg.school_management_api.model.Aula;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AulaMapper {
//
//    public Aula paraEntidade (AulaRequisicaoDTO requisicaoDTO) {
//        return new Aula (
//                requisicaoDTO.turma_id(),
//                requisicaoDTO.data_hora(),
//                requisicaoDTO.assunto()
//        );
//    }
//
//    public AulaRespostaDTO paraResposta (Aula aula) {
//        return new AulaRespostaDTO (
//                aula.getId(),
//                aula.getNomeTurma(),
//                aula.getData_hora(),
//                aula.getAssunto()
//        );
//    }
//}
