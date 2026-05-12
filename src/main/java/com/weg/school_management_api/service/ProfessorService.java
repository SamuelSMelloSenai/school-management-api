package com.weg.school_management_api.service;

import com.weg.school_management_api.dto.professorDTO.ProfessorRequisicaoDTO;
import com.weg.school_management_api.dto.professorDTO.ProfessorRespostaDTO;
import com.weg.school_management_api.mapper.ProfessorMapper;
import com.weg.school_management_api.model.Professor;
import com.weg.school_management_api.repository.professorRepository.ProfessorRepository;
import org.springframework.stereotype.Service;

import javax.management.RuntimeMBeanException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorRespostaDTO cadastrarProfessor (ProfessorRequisicaoDTO professorRequisicaoDTO) throws SQLException {
        Professor professor = professorMapper.paraEntidade(professorRequisicaoDTO);

        professorRepository.cadastrarProfessor(professor);

        return professorMapper.paraResposta(professor);
    }

    public List<ProfessorRespostaDTO> buscarTodosOsProfessors () throws SQLException {
        List<Professor> professores = professorRepository.buscarTodosOsProfessors();

        return professores.stream().map(
                professorMapper::paraResposta
        ).toList();
    }

    public ProfessorRespostaDTO buscarProfessorPorId (Long id) throws SQLException {
        Professor professor = professorRepository.buscarProfessorPorId(id).orElseThrow(() -> new RuntimeException("Erro ao encontrar professor!"));

        return professorMapper.paraResposta(professor);
    }

    public ProfessorRespostaDTO atualizarProfessor (Long id, Professor professor) throws SQLException {
        if (!professorRepository.existenciaDoProfessor(id)) {
            throw new RuntimeException("O professor não existe!");
        }

        professor.setId(id);
        professorRepository.atualizarProfessor(professor);

        return professorMapper.paraResposta(professor);
    }

    public void deletarProfessor (Long id) throws SQLException {
        if (!professorRepository.existenciaDoProfessor(id)) {
            throw new RuntimeException("O professor não existe!");
        }

        professorRepository.deletarProfessor(id);
    }
}
