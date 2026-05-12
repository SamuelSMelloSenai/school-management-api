//package com.weg.school_management_api.mapper;
//
//import com.weg.school_management_api.dto.cursoDTO.CursoRequisicaoDTO;
//import com.weg.school_management_api.model.Curso;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CursoMapper {
//
//    public Curso paraEntidade (CursoRequisicaoDTO requisicaoDTO) {
//        return new Curso (
//                requisicaoDTO.nome(),
//                requisicaoDTO.codigo(),
//                requisicaoDTO.professoresIds()
//        );
//    }
//
//    public CursoRequisicaoDTO paraResposta (Curso curso) {
//        return new CursoRequisicaoDTO (
//                curso.getId(),
//                curso.getNome(),
//                curso.getCodigo(),
//                curso.getProfessoreNomes()
//        );
//    }
//}
