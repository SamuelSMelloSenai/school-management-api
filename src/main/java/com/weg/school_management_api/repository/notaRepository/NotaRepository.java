package com.weg.school_management_api.repository.notaRepository;

import com.weg.school_management_api.model.Nota;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface NotaRepository {

    Nota cadastrarNota (Nota nota) throws SQLException;

    List<Nota> buscarTodasAsNotas () throws SQLException;

    Optional<Nota> buscarNotaPorId (Long id) throws SQLException;

    boolean existenciaNota (Long id) throws SQLException;

    void atualizarNota (Nota nota) throws SQLException;

    void deletarNota (Long id) throws SQLException;

}
