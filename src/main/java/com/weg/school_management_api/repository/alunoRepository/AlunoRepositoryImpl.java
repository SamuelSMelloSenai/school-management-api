package com.weg.school_management_api.repository.alunoRepository;

import com.weg.school_management_api.infrastructure.ConnectionFactory;
import com.weg.school_management_api.model.Aluno;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {

    public Aluno cadastrarAluno(Aluno aluno) throws SQLException {
        String sql = """
                INSERT INTO aluno (nome, email, matricula, data_nascimento)
                VALUES (?, ?, ?, ?);
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, java.sql.Date.valueOf(aluno.getDataNascimento()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                aluno.setId(rs.getLong(1));

                return aluno;
            }
        }
        throw new RuntimeException("Erro ao cadastrar aluno!");
    }

    public List<Aluno> buscarTodosOsAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String query = """
                SELECT id
                       ,nome
                       ,email
                       ,matricula
                       ,data_nascimento
               FROM aluno;
               """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Date sqlDate = rs.getDate("data_nascimento");
                LocalDate dataNascimento = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                var aluno = new Aluno (
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        dataNascimento
                );

                alunos.add(aluno);
            }
            return alunos;
        }
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) throws SQLException {
        String query = """
                SELECT  nome
                       ,email
                       ,matricula
                       ,data_nascimento
               FROM aluno
               WHERE id = ?;
               """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Date sqlDate = rs.getDate("data_nascimento");
                LocalDate dataNascimento = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                var aluno = new Aluno (
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        dataNascimento
                );
                aluno.setId(id);
                return Optional.of(aluno);
            }

        }
        return Optional.empty();
    }

    public boolean existenciaDoAluno(Long id) throws SQLException {
        String query = """
                SELECT COUNT(0) AS resultado
                FROM aluno
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

    public void atualizarAluno(Aluno aluno) throws SQLException {
        String sql = """
                UPDATE aluno
                SET nome = ?
                    ,email = ?
                    ,matricula = ?
                    ,data_nascimento = ?
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, java.sql.Date.valueOf(aluno.getDataNascimento()));
            stmt.setLong(5, aluno.getId());

            stmt.executeUpdate();
        }
    }

    public void deletarAluno(Long id) throws SQLException {
        String sql = """
                DELETE FROM aluno
                WHERE id = ?;
                """;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}
