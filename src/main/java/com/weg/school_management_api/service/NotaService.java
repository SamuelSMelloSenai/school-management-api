package com.weg.school_management_api.service;

import com.weg.school_management_api.dto.nota.NotaRequisicaoDTO;
import com.weg.school_management_api.dto.nota.NotaRespostaDTO;
import com.weg.school_management_api.mapper.NotaMapper;
import com.weg.school_management_api.model.Nota;
import com.weg.school_management_api.repository.alunoRepository.AlunoRepository;
import com.weg.school_management_api.repository.aulaRepository.AulaRepository;
import com.weg.school_management_api.repository.notaRepository.NotaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;
    private final AlunoRepository alunoRepository;
    private final AulaRepository aulaRepository;

    public NotaService(NotaRepository notaRepository, NotaMapper notaMapper, AlunoRepository alunoRepository, AulaRepository aulaRepository) {
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
        this.alunoRepository = alunoRepository;
        this.aulaRepository = aulaRepository;
    }

    public NotaRespostaDTO cadastrarNota (NotaRequisicaoDTO notaRequisicaoDTO) throws SQLException {
        Nota nota = notaMapper.paraEntidade(notaRequisicaoDTO);

        nota = notaRepository.cadastrarNota(nota);

        String nomeAluno = alunoRepository.buscarAlunoPorId(nota.getAlunoId()).get().getNome();

        String aulaAssunto = aulaRepository.buscarAulaPorId(nota.getAulaId()).get().getAssunto();

        return notaMapper.paraResposta(nota, nomeAluno, aulaAssunto);
    }

    public List<NotaRespostaDTO> buscarTodasAsNotas () throws SQLException {
        List<Nota> notas = notaRepository.buscarTodasAsNotas();

        if (notas.isEmpty()) {
            throw new RuntimeException("Nenhuma nota encontrada!");
        }

        List<NotaRespostaDTO> listaResposta = new ArrayList<>();

        for (Nota nota : notas) {

            String nomeAluno = alunoRepository.buscarAlunoPorId(nota.getAlunoId())
                    .orElseThrow(() -> new RuntimeException("Erro ao buscar aluno!"))
                    .getNome();

            String aulaAssunto = aulaRepository.buscarAulaPorId(nota.getAulaId())
                    .orElseThrow(() -> new RuntimeException("Erro ao buscar aluno!"))
                    .getAssunto();

            NotaRespostaDTO notaRespostaDTO = notaMapper.paraResposta(nota, nomeAluno, aulaAssunto);
            listaResposta.add(notaRespostaDTO);
        }
        return listaResposta;
    }

    public NotaRespostaDTO buscarNotaPorId (Long id) throws SQLException {
        Nota nota = notaRepository.buscarNotaPorId(id)
                .orElseThrow(() -> new RuntimeException("Erro ao buscar aluno!"));

        String nomeAluno = alunoRepository.buscarAlunoPorId(nota.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar aluno!"))
                .getNome();

        String aulaAssunto = aulaRepository.buscarAulaPorId(nota.getAulaId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar aluno!"))
                .getAssunto();

        return notaMapper.paraResposta(nota, nomeAluno, aulaAssunto);
    }

    public NotaRespostaDTO atualizarNota (Long id, Nota nota) throws SQLException {
        if (!notaRepository.existenciaNota(id)) {
            throw new RuntimeException("Erro ao buscar existencia da nota!");
        }

        nota.setId(id);
        notaRepository.atualizarNota(nota);

        String nomeAluno = alunoRepository.buscarAlunoPorId(nota.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar aluno!"))
                .getNome();

        String aulaAssunto = aulaRepository.buscarAulaPorId(nota.getAulaId())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar aluno!"))
                .getAssunto();

        return notaMapper.paraResposta(nota, nomeAluno, aulaAssunto);
    }

    public void deletarNota (Long id) throws SQLException {
        if (!notaRepository.existenciaNota(id)) {
            throw new RuntimeException("Erro ao buscar existencia da nota!");
        }

        notaRepository.deletarNota(id);
    }

}
