package com.weg.school_management_api.repository.alunoRepository;

import com.weg.school_management_api.model.Aluno;

import java.sql.SQLException;

public interface AlunoRepository {

    Aluno cadastrarAluno (Aluno aluno) throws SQLException;
}
