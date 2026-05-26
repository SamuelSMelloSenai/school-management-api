package com.weg.school_management_api.repository.notaRepository;

import com.weg.school_management_api.infrastructure.ConnectionFactory;
import com.weg.school_management_api.model.Nota;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NotaRepositoryImpl implements NotaRepository {

    public Nota cadastrarNota (Nota nota) throws SQLException {
        String sql = """
                INSERT INTO nota (aluno_id, aula_id, valor)
                VALUES (?, ?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, nota.getAlunoId());
            stmt.setLong(2, nota.getAulaId());
            stmt.setFloat(3, nota.getValor());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                nota.setId(rs.getLong(1));

                return nota;
            }
        }
        throw new RuntimeException("Erro ao cadastrar nota!");
    }

    public List<Nota> buscarTodasAsNotas() throws SQLException {
        List<Nota> notas = new ArrayList<>();
        String query = """
                SELECT   id
                        ,aluno_id
                        ,aula_id
                        ,valor
                FROM nota;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var nota = new Nota (
                        rs.getLong("id"),
                        rs.getLong("aluno_id"),
                        rs.getLong("aula_id"),
                        rs.getFloat("valor")
                );

                notas.add(nota);
            }
            return notas;
        }
    }

    public Optional<Nota> buscarNotaPorId (Long id) throws SQLException {
        String query = """
                SELECT   aluno_id
                        ,aula_id
                        ,valor
                FROM nota
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var nota = new Nota(
                        rs.getLong("aluno_id"),
                        rs.getLong("aula_id"),
                        rs.getFloat("valor")
                );
                nota.setId(id);

                return Optional.of(nota);
            }
        }
        return Optional.empty();
    }

    public boolean existenciaNota(Long id) throws SQLException {
        String query = """
                SELECT COUNT(0) AS resultado
                FROM nota
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int resultado = (rs.getInt("resultado"));
                if (resultado == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new RuntimeException("Erro ao bucar existencia da nota!");
    }

    public void atualizarNota(Nota nota) throws SQLException {
        String sql = """
                UPDATE nota
                SET aluno_id = ?, aula_id = ?, valor = ?
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, nota.getAlunoId());
            stmt.setLong(2, nota.getAulaId());
            stmt.setFloat(3, nota.getValor());
            stmt.setLong(4, nota.getId());

            stmt.executeUpdate();
        }
    }

    public void deletarNota(Long id) throws SQLException {
        String sql = """
                DELETE FROM nota
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}
