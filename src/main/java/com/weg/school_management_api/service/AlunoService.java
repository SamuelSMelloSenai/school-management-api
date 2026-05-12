package com.weg.school_management_api.service;

import com.weg.school_management_api.dto.alunoDTO.AlunoRequisicaoDTO;
import com.weg.school_management_api.dto.alunoDTO.AlunoRespostaDTO;
import com.weg.school_management_api.mapper.AlunoMapper;
import com.weg.school_management_api.model.Aluno;
import com.weg.school_management_api.repository.alunoRepository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    public List<AlunoRespostaDTO> buscarTodosOsAlunos () throws SQLException {
        List<Aluno> alunos = alunoRepository.buscarTodosOsAlunos();

        return alunos.stream().map(
                alunoMapper::paraResposta
        ).toList();
    }

    public AlunoRespostaDTO buscarAlunoPorId(Long id) throws SQLException {
        Aluno aluno = alunoRepository.buscarAlunoPorId(id).orElseThrow(() -> new RuntimeException("Erro ao encontrar aluno!"));

        return alunoMapper.paraResposta(aluno);
    }

    public AlunoRespostaDTO atualizarAluno (Long id, Aluno aluno) throws SQLException {
        if (!alunoRepository.existenciaDoAluno(id)) {
            throw new RuntimeException("O aluno não existe!");
        }

        aluno.setId(id);
        alunoRepository.atualizarAluno(aluno);

        return alunoMapper.paraResposta(aluno);
    }

    public void deletarAluno (Long id) throws SQLException {
        if (!alunoRepository.existenciaDoAluno(id)) {
            throw new RuntimeException("O aluno não existe!");
        }

        alunoRepository.deletarAluno(id);
    }

}
