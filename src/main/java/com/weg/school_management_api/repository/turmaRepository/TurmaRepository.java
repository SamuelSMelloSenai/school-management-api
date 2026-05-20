package com.weg.school_management_api.repository.turmaRepository;

import com.weg.school_management_api.model.Turma;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TurmaRepository {

    Turma cadastrarTurma (Turma turma) throws SQLException;

    void associarAlunoATurma (Long turmaId, Long alunoId) throws SQLException;

    List<Turma> buscarTodasAsTurmas () throws SQLException;

    Optional<Turma> buscarTurmaPorId (Long id) throws SQLException;

    boolean existenciaDaTurma (Long id) throws SQLException;

    void atualizarTurma (Turma turma) throws SQLException;

    void deletarTurma (Long id) throws SQLException;

}
