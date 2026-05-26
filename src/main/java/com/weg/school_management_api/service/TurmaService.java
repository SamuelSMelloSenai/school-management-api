package com.weg.school_management_api.service;

import com.weg.school_management_api.dto.turma.TurmaRequisicaoDTO;
import com.weg.school_management_api.dto.turma.TurmaRespostaDTO;
import com.weg.school_management_api.mapper.TurmaMapper;
import com.weg.school_management_api.model.Aluno;
import com.weg.school_management_api.model.Turma;
import com.weg.school_management_api.repository.alunoRepository.AlunoRepository;
import com.weg.school_management_api.repository.cursoRepository.CursoRepository;
import com.weg.school_management_api.repository.professorRepository.ProfessorRepository;
import com.weg.school_management_api.repository.turmaRepository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaMapper turmaMapper;

    public TurmaService(AlunoRepository alunoRepository, TurmaRepository turmaRepository, CursoRepository cursoRepository, ProfessorRepository professorRepository, TurmaMapper turmaMapper) {
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
        this.turmaMapper = turmaMapper;
    }

    public TurmaRespostaDTO cadastrarTurma (TurmaRequisicaoDTO turmaRequisicaoDTO) throws SQLException {
        Turma turma = turmaMapper.paraEntidade(turmaRequisicaoDTO);

        turmaRepository.cadastrarTurma(turma);

        String nomeCurso = cursoRepository.buscarCursoPorId(turma.getCursoId()).get().getNome();

        String nomeProfessor = professorRepository.buscarProfessorPorId(turma.getProfessorId()).get().getNome();

        List<String> nomeAlunos = new ArrayList<>();

        for (Long id : turmaRequisicaoDTO.alunosIds()) {
            if (!alunoRepository.existenciaDoAluno(id)) {
                throw new RuntimeException("Aluno não encontrado!");
            }

            turmaRepository.associarAlunoATurma(turma.getId(), id);

            String nomeAluno = alunoRepository.buscarAlunoPorId(id).get().getNome();
            nomeAlunos.add(nomeAluno);
        }

        return turmaMapper.paraResposta(turma, nomeCurso, nomeProfessor, nomeAlunos);
    }

    public List<TurmaRespostaDTO> buscarTodasAsTurmas () throws SQLException {
        List<Turma> turmas = turmaRepository.buscarTodasAsTurmas();

        if (turmas.isEmpty()) {
            throw new RuntimeException("Curso não cadastrado!");
        }

        List<TurmaRespostaDTO> listaRespostas = new ArrayList<>();

        for (Turma turma : turmas) {
            List<Aluno> nomeDosAlunos = alunoRepository.buscarTodosOsAlunoDaTurma(turma);
            List<String> nomeDosAlunosDaTurma = new ArrayList<>();

            if (nomeDosAlunos != null) {
                nomeDosAlunos.forEach(
                        aluno -> {
                            nomeDosAlunosDaTurma.add(aluno.getNome());
                        });
            }
            String nomeCurso = cursoRepository.buscarCursoPorId(turma.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Erro ao buscar o curso da turma por id!"))
                    .getNome();

            String nomeProfessor = professorRepository.buscarProfessorPorId(turma.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Erro ao buscar o professor da turma por id!"))
                    .getNome();

            TurmaRespostaDTO turmaRespostaDTO = turmaMapper.paraResposta(turma, nomeCurso, nomeProfessor, nomeDosAlunosDaTurma);
            listaRespostas.add(turmaRespostaDTO);
        }
        return listaRespostas;
    }

    public TurmaRespostaDTO buscarTurmaPorId (Long id) throws SQLException {
        Turma turma = turmaRepository.buscarTurmaPorId(id).orElseThrow(() -> new RuntimeException("Erro ao buscar turma por id!"));

        String nomeCurso = cursoRepository.buscarCursoPorId(turma.getCursoId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar o curso da turma por id!"))
                .getNome();

        String nomeProfessor = professorRepository.buscarProfessorPorId(turma.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar o professor da turma por id!"))
                .getNome();

        List<Aluno> nomeDosAlunos = alunoRepository.buscarTodosOsAlunoDaTurma(turma);
        List<String> nomeDosAlunosDaTurma = new ArrayList<>();

        if (nomeDosAlunos != null) {
            nomeDosAlunos.forEach(
                    aluno -> {
                        nomeDosAlunosDaTurma.add(aluno.getNome());
                    });
        }

        return turmaMapper.paraResposta(turma, nomeCurso, nomeProfessor, nomeDosAlunosDaTurma);
    }

    public TurmaRespostaDTO atualizarTurma (Long id, Turma turma) throws SQLException {
        if (!turmaRepository.existenciaDaTurma(id)) {
            throw new RuntimeException("Erro ao buscar existência da turma!");
        }

        turma.setId(id);
        turmaRepository.atualizarTurma(turma);

        String nomeCurso = cursoRepository.buscarCursoPorId(turma.getCursoId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar o curso da turma por id!"))
                .getNome();

        String nomeProfessor = professorRepository.buscarProfessorPorId(turma.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar o professor da turma por id!"))
                .getNome();

        List<Aluno> nomeDosAlunos = alunoRepository.buscarTodosOsAlunoDaTurma(turma);
        List<String> nomeDosAlunosDaTurma = new ArrayList<>();

        if (nomeDosAlunos != null) {
            nomeDosAlunos.forEach(
                    aluno -> {
                        nomeDosAlunosDaTurma.add(aluno.getNome());
                    });
        }

        return turmaMapper.paraResposta(turma, nomeCurso, nomeProfessor, nomeDosAlunosDaTurma);
    }

    public void deletarTurma (Long id) throws SQLException {
        if (!turmaRepository.existenciaDaTurma(id)) {
            throw new RuntimeException("Erro ao buscar existência da turma!");
        }

        turmaRepository.deletarTurma(id);
    }
}
