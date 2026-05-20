package com.weg.school_management_api.repository.cursoRepository;

import com.weg.school_management_api.model.Curso;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CursoRepository {

    Curso cadastrarCurso (Curso curso) throws SQLException;

    void associarProfessorACurso (Long cursoId, Long professorId) throws SQLException;

    List<Curso> buscarTodosOsCursos () throws SQLException;

    Optional<Curso> buscarCursoPorId (Long id) throws SQLException;

    boolean existenciaDoCurso (Long id) throws SQLException;

    void atualizarCurso (Curso curso) throws SQLException;

    void deletarCurso (Long id) throws SQLException;
}
