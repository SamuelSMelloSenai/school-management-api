package com.weg.school_management_api.service;

import com.weg.school_management_api.dto.alunoDTO.AlunoRequisicaoDTO;
import com.weg.school_management_api.dto.alunoDTO.AlunoRespostaDTO;
import com.weg.school_management_api.mapper.AlunoMapper;
import com.weg.school_management_api.model.Aluno;
import com.weg.school_management_api.repository.alunoRepository.AlunoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public AlunoRespostaDTO cadastrarAluno (AlunoRequisicaoDTO alunoRequisicaoDTO) throws SQLException {
        Aluno aluno = alunoMapper.paraEntidade(alunoRequisicaoDTO);

        alunoRepository.cadastrarAluno(aluno);

        return alunoMapper.paraResposta(aluno);
    }

}
