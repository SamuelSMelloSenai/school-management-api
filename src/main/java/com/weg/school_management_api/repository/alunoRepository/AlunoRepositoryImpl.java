package com.weg.school_management_api.repository.alunoRepository;

import com.weg.school_management_api.infrastructure.ConnectionFactory;
import com.weg.school_management_api.model.Aluno;
import org.springframework.stereotype.Repository;

import java.sql.*;

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
}
