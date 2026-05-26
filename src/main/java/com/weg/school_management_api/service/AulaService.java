package com.weg.school_management_api.service;

import com.weg.school_management_api.dto.aula.AulaRequisicaoDTO;
import com.weg.school_management_api.dto.aula.AulaRespostaDTO;
import com.weg.school_management_api.mapper.AulaMapper;
import com.weg.school_management_api.model.Aula;
import com.weg.school_management_api.repository.aulaRepository.AulaRepository;
import com.weg.school_management_api.repository.turmaRepository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;
    private final TurmaRepository turmaRepository;

    public AulaService(AulaRepository aulaRepository, AulaMapper aulaMapper, TurmaRepository turmaRepository) {
        this.aulaRepository = aulaRepository;
        this.aulaMapper = aulaMapper;
        this.turmaRepository = turmaRepository;
    }

    public AulaRespostaDTO cadastrarAula (AulaRequisicaoDTO aulaRequisicaoDTO) throws SQLException {
        Aula aula = aulaMapper.paraEntidade(aulaRequisicaoDTO);

        aula = aulaRepository.cadastrarAula(aula);

        String nomeTurma = turmaRepository.buscarTurmaPorId(aula.getTurmaId()).get().getNome();

        return aulaMapper.paraResposta(aula, nomeTurma);
    }

    public List<AulaRespostaDTO> buscarTodasAsAulas () throws SQLException {
        List<Aula> aulas = aulaRepository.buscarTodasAsAulas();

        if (aulas.isEmpty()) {
            throw new RuntimeException("Nenhuma turma encontrada!");
        }

        List<AulaRespostaDTO> listaResposta = new ArrayList<>();

        for (Aula aula : aulas) {

            String nomeTurma = turmaRepository.buscarTurmaPorId(aula.getTurmaId())
                    .orElseThrow(() -> new RuntimeException("Erro ao encontrar nomes das turmas!"))
                    .getNome();

            AulaRespostaDTO aulaRespostaDTO = aulaMapper.paraResposta(aula, nomeTurma);
            listaResposta.add(aulaRespostaDTO);
        }
        return listaResposta;
    }

    public AulaRespostaDTO buscarAulaPorId (Long id) throws SQLException {
        Aula aula = aulaRepository.buscarAulaPorId(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada!"));

        String nomeTurma = turmaRepository.buscarTurmaPorId(aula.getTurmaId()).get().getNome();

        return aulaMapper.paraResposta(aula, nomeTurma);
    }

    public AulaRespostaDTO atualizarAula (Long id, Aula aula) throws SQLException {
        if (!aulaRepository.existenciaAula(id)) {
            throw new RuntimeException("Erro ao buscar existência da aula!");
        }

        aula.setId(id);
        aulaRepository.atualizarAula(aula);

        String nomeTurma = turmaRepository.buscarTurmaPorId(aula.getTurmaId()).get().getNome();

        return aulaMapper.paraResposta(aula, nomeTurma);
    }

    public void deletarAula (Long id) throws SQLException {
        if (!aulaRepository.existenciaAula(id)) {
            throw new RuntimeException("Erro ao buscar existência da aula!");
        }

        aulaRepository.deletarAula(id);
    }
}
