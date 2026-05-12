package com.weg.school_management_api.repository.professorRepository;

import com.weg.school_management_api.infrastructure.ConnectionFactory;
import com.weg.school_management_api.model.Professor;
import com.weg.school_management_api.service.ProfessorService;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {

    public Professor cadastrarProfessor(Professor professor) throws SQLException {
        String sql = """
                INSERT INTO professor (nome, email, disciplina)
                VALUES (?, ?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                professor.setId(rs.getLong(1));

                return professor;
            }
        }
        throw new RuntimeException("Erro ao cadastrar professor!");
    }

    public List<Professor> buscarTodosOsProfessors() throws SQLException {
        List<Professor> professores = new ArrayList<>();
        String query = """
                SELECT  id
                        ,nome
                        ,email
                        ,disciplina
                FROM professor;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var professor = new Professor (
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("disciplina")
                );

                professores.add(professor);
            }
            return professores;
        }
    }

    public Optional<Professor> buscarProfessorPorId(Long id) throws SQLException {
        String query = """
                SELECT  id
                        ,nome
                        ,email
                        ,disciplina
                FROM professor
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var professor = new Professor (
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("disciplina")
                );
                professor.setId(id);
                return Optional.of(professor);
            }
        }
        return Optional.empty();
    }

    public boolean existenciaDoProfessor(Long id) throws SQLException {
        String query = """
                SELECT COUNT(0) AS resultado
                FROM professor
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
        throw new RuntimeException("Erro ao buscar existência do aluno!");
    }

    public void atualizarProfessor(Professor professor) throws SQLException {
        String sql = """
                UPDATE professor
                SET nome = ?
                    ,email = ?
                    ,disciplina = ?
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setLong(4, professor.getId());

            stmt.executeUpdate();
        }
    }

    public void deletarProfessor(Long id) throws SQLException {
        String sql = """
                DELETE FROM professor
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}
