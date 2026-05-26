package com.weg.school_management_api.repository.aulaRepository;

import com.weg.school_management_api.infrastructure.ConnectionFactory;
import com.weg.school_management_api.model.Aula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AulaRepositoryImpl implements AulaRepository {



    public Aula cadastrarAula(Aula aula) throws SQLException {
        String sql = """
                INSERT INTO aula (turma_id, data_hora, assunto)
                VALUES (?, ?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, aula.getTurmaId());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                aula.setId(rs.getLong(1));

                return aula;
            }
        }
        throw new RuntimeException("Erro ao cadastar aula!");
    }

    public List<Aula> buscarTodasAsAulas() throws SQLException {
        List<Aula> aulas = new ArrayList<>();
        String query = """
                SELECT   id
                        ,turma_id
                        ,data_hora
                        ,assunto
                FROM aula;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var aula = new Aula (
                        rs.getLong("id"),
                        rs.getLong("turma_id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getString("assunto")
                );

                aulas.add(aula);
            }
            return aulas;
        }
    }

    public Optional<Aula> buscarAulaPorId(Long id) throws SQLException {
        String query = """
                SELECT   turma_id
                        ,data_hora
                        ,assunto
                FROM aula
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var aula = new Aula (
                        rs.getLong("turma_id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getString("assunto")
                );
                aula.setId(id);
                return Optional.of(aula);
            }
        }
        return Optional.empty();
    }

    public boolean existenciaAula(Long id) throws SQLException {
        String query = """
                SELECT COUNT(0) AS resultado
                FROM aula
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int resultado = (rs.getInt("resultado"));
                if (resultado == 1){
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new RuntimeException("Erro ao bucar existencia da aula!");
    }

    public void atualizarAula(Aula aula) throws SQLException {
        String sql = """
                UPDATE aula
                SET turma_id = ?, data_hora = ?, assunto = ?
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, aula.getTurmaId());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());
            stmt.setLong(4, aula.getId());

            stmt.executeUpdate();
        }
    }

    public void deletarAula(Long id) throws SQLException {
        String sql = """
                DELETE FROM aula
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}
