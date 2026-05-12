//package com.weg.school_management_api.mapper;
//
//import com.weg.school_management_api.dto.TurmaDTO.TurmaRequisicaoDTO;
//import com.weg.school_management_api.dto.TurmaDTO.TurmaRespostaDTO;
//import com.weg.school_management_api.model.Turma;
//
//public class TurmaMapper {
//
//    public Turma paraEntidade (TurmaRequisicaoDTO requisicaoDTO) {
//        return new Turma (
//                requisicaoDTO.nome(),
//                requisicaoDTO.curso_id(),
//                requisicaoDTO.professor_id(),
//                requisicaoDTO.alunosIds()
//        );
//    }
//
//    public TurmaRespostaDTO paraResposta (Turma turma) {
//        return new TurmaRespostaDTO (
//                turma.getId(),
//                turma.getNome(),
//                turma.getNomeCurso(),
//                turma.getNomeProfessor(),
//                turma.getAlunosNomes()
//        );
//    }
//}
//