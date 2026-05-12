package com.weg.school_management_api.repository.professorRepository;

import com.weg.school_management_api.model.Professor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {

    Professor cadastrarProfessor (Professor professor) throws SQLException;

    List<Professor> buscarTodosOsProfessors () throws SQLException;

    Optional<Professor> buscarProfessorPorId (Long id) throws SQLException;

    boolean existenciaDoProfessor (Long id) throws SQLException;

    void atualizarProfessor (Professor professor) throws SQLException;

    void deletarProfessor (Long id) throws SQLException;
}
