package com.weg.school_management_api.repository.alunoRepository;

import com.weg.school_management_api.model.Aluno;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AlunoRepository {

    Aluno cadastrarAluno (Aluno aluno) throws SQLException;

    List<Aluno> buscarTodosOsAlunos () throws SQLException;

    Optional<Aluno> buscarAlunoPorId (Long id) throws SQLException;

    boolean existenciaDoAluno (Long id) throws SQLException;

    void atualizarAluno (Aluno aluno) throws SQLException;

    void deletarAluno (Long id) throws SQLException;

}
