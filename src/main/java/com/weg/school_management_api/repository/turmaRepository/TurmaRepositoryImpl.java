package com.weg.school_management_api.repository.turmaRepository;

import com.weg.school_management_api.infrastructure.ConnectionFactory;
import com.weg.school_management_api.model.Turma;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TurmaRepositoryImpl implements TurmaRepository {

    public Turma cadastrarTurma (Turma turma) throws SQLException {
        String sql = """
                INSERT INTO turma (nome, curso_id, professor_id)
                VALUES (?, ?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, turma.getNome());
            stmt.setLong(2, turma.getCursoId());
            stmt.setLong(3, turma.getProfessorId());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                turma.setId(rs.getLong(1));

                return turma;
            }
        }
        throw new RuntimeException("Erro ao cadastrar turma!");
    }

    public void associarAlunoATurma(Long turmaId, Long alunoId) throws SQLException {
        String sql = """
                INSERT INTO turma_aluno (turma_id, aluno_id)
                VALUES (?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, turmaId);
            stmt.setLong(2, alunoId);

            stmt.executeUpdate();
        }
    }

    public List<Turma> buscarTodasAsTurmas () throws SQLException {
        List<Turma> turmas = new ArrayList<>();
        String query = """
                SELECT   id
                        ,nome
                        ,curso_id
                        ,professor_id
                FROM turma;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var turma = new Turma (
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getLong("curso_id"),
                        rs.getLong("professor_id")
                );

                turmas.add(turma);
            }

            return turmas;
        }
    }

    public Optional<Turma> buscarTurmaPorId (Long id) throws SQLException {
        String query = """
                SELECT  id
                        ,nome
                        ,curso_id
                        ,professor_id
                FROM turma
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Turma turma = new Turma();
                turma.setId(rs.getLong("id"));
                turma.setNome(rs.getString("nome"));
                turma.setCursoId(rs.getLong("curso_id"));
                turma.setProfessorId(rs.getLong("professor_id"));

                return Optional.of(turma);
            }
        }
        return Optional.empty();
    }

    public boolean existenciaDaTurma (Long id) throws SQLException {
        String query = """
                SELECT COUNT(0) AS resultado
                FROM turma
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
        throw new RuntimeException("Erro ao buscar existência da turma!");
    }

    public void atualizarTurma (Turma turma) throws SQLException {
        String sql = """
                UPDATE turma
                SET nome = ?, curso_id = ?, professor_id = ?
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getNome());
            stmt.setLong(2, turma.getCursoId());
            stmt.setLong(3, turma.getProfessorId());
            stmt.setLong(4, turma.getId());

            stmt.executeUpdate();
        }
    }

    public void deletarTurma (Long id) throws SQLException {
        String sql = """
                DELETE FROM turma
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }

    

}
