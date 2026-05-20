package com.weg.school_management_api.service;

import com.weg.school_management_api.dto.cursoDTO.CursoRequisicaoDTO;
import com.weg.school_management_api.dto.cursoDTO.CursoRespostaDTO;
import com.weg.school_management_api.mapper.CursoMapper;
import com.weg.school_management_api.model.Curso;
import com.weg.school_management_api.model.Professor;
import com.weg.school_management_api.repository.cursoRepository.CursoRepository;
import com.weg.school_management_api.repository.professorRepository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, ProfessorRepository professorRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
        this.cursoMapper = cursoMapper;
    }

    public CursoRespostaDTO cadastrarCurso(CursoRequisicaoDTO cursoRequisicaoDTO) throws SQLException {
        Curso curso = cursoMapper.paraEntidade(cursoRequisicaoDTO);

        cursoRepository.cadastrarCurso(curso);

        List<String> nomeProfessores = new ArrayList<>();

        for (Long id : cursoRequisicaoDTO.professoresIds()) {
            cursoRepository.associarProfessorACurso(curso.getId(), id);

            if (professorRepository.buscarProfessorPorId(id).isEmpty()) {
                throw new RuntimeException("Professor não encontrado!");
            }

            String nomeProfessor = professorRepository.buscarProfessorPorId(id).get().getNome();
            nomeProfessores.add(nomeProfessor);
        }

        return cursoMapper.paraResposta(curso, nomeProfessores);
    }

    public List<CursoRespostaDTO> buscarTodosOsCursos() throws SQLException {

        List<Curso> cursos = cursoRepository.buscarTodosOsCursos();

        if (cursos.isEmpty()) {
            throw new RuntimeException("Curso não cadastrado!");
        }

        List<CursoRespostaDTO> listaRespostas = new ArrayList<>();

        for (Curso curso : cursos) {
            List<Professor> professoresDoCurso = professorRepository.buscarTodosOsProfessoresDoCurso(curso);
            List<String> nomesDosProfessoresDoCurso = new ArrayList<>();

            professoresDoCurso.forEach(
                    (professor -> {
                        nomesDosProfessoresDoCurso.add(professor.getNome());
                    }
                    ));
            CursoRespostaDTO cursoRespostaDTO = cursoMapper.paraResposta(curso, nomesDosProfessoresDoCurso);
            listaRespostas.add(cursoRespostaDTO);
        }

        return listaRespostas;
    }

    public CursoRespostaDTO buscarCursoPorId(Long id) throws SQLException {
        Curso curso = cursoRepository.buscarCursoPorId(id).orElseThrow(() -> new RuntimeException("Erro ao buscar curso por id!"));

        List<Professor> professoresDoCurso = professorRepository.buscarTodosOsProfessoresDoCurso(curso);
        List<String> nomesDosProfessoresDoCurso = new ArrayList<>();

        professoresDoCurso.forEach(
                (professor -> {
                    nomesDosProfessoresDoCurso.add(professor.getNome());
                }
                ));

        return cursoMapper.paraResposta(curso, nomesDosProfessoresDoCurso);
    }

    public CursoRespostaDTO atualizarCurso(Long id, Curso curso) throws SQLException {
        if (!cursoRepository.existenciaDoCurso(id)) {
            throw new RuntimeException("Erro ao buscar existência do curso!");
        }

        curso.setId(id);
        cursoRepository.atualizarCurso(curso);

        List<Professor> professoresDoCurso = professorRepository.buscarTodosOsProfessoresDoCurso(curso);
        List<String> nomesDosProfessoresDoCurso = new ArrayList<>();

        professoresDoCurso.forEach(
                (professor -> {
                    nomesDosProfessoresDoCurso.add(professor.getNome());
                }
                ));

        return cursoMapper.paraResposta(curso, nomesDosProfessoresDoCurso);
    }

    public void deletarCurso(Long id) throws SQLException {
        if (!cursoRepository.existenciaDoCurso(id)) {
            throw new RuntimeException("Erro ao buscar existência do curso!");
        }

        cursoRepository.deletarCurso(id);
    }
}
