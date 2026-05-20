package com.weg.school_management_api.repository.cursoRepository;

import com.weg.school_management_api.infrastructure.ConnectionFactory;
import com.weg.school_management_api.model.Curso;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    public Curso cadastrarCurso(Curso curso) throws SQLException {
        String sql = """
                INSERT INTO curso (nome, codigo)
                VALUES (?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                curso.setId(rs.getLong(1));

                return curso;
            }
        }
        throw new RuntimeException("Erro ao cadastrar curso!");
    }

    public void associarProfessorACurso(Long cursoId, Long professorId) throws SQLException {
        String sql = """
                INSERT INTO curso_professor (curso_id, professor_id)
                VALUES (?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cursoId);
            stmt.setLong(2, professorId);

            stmt.executeUpdate();
        }
    }

    public List<Curso> buscarTodosOsCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String query = """
                SELECT   id
                        ,nome
                        ,codigo
                FROM curso;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var curso = new Curso(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("codigo")
                );

                cursos.add(curso);
            }

            return cursos;
        }
    }

    public Optional<Curso> buscarCursoPorId(Long id) throws SQLException {
        String query = """
                SELECT   id
                        ,nome
                        ,codigo
                FROM curso
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var curso = new Curso(
                        rs.getString("nome"),
                        rs.getString("codigo")
                );

                curso.setId(id);
                return Optional.of(curso);
            }
        }
        return Optional.empty();
    }

    public boolean existenciaDoCurso(Long id) throws SQLException {
        String query = """
                SELECT COUNT(0) AS resultado
                FROM curso
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int resultado = rs.getInt("resultado");
                if (resultado == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new RuntimeException("Erro ao buscar existência do curso!");
    }

    public void atualizarCurso(Curso curso) throws SQLException {
        String sql = """
                UPDATE curso
                SET  nome = ?
                    ,codigo = ?
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setLong(3, curso.getId());

            stmt.executeUpdate();
        }
    }

    public void deletarCurso(Long id) throws SQLException {
        String sql = """
                DELETE FROM curso
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}
