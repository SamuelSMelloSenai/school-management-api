package com.weg.school_management_api.repository.aulaRepository;

import com.weg.school_management_api.model.Aula;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AulaRepository {

    Aula cadastrarAula (Aula aula) throws SQLException;

    List<Aula> buscarTodasAsAulas () throws SQLException;

    Optional<Aula> buscarAulaPorId (Long id) throws SQLException;

    boolean existenciaAula (Long id) throws SQLException;

    void atualizarAula (Aula aula) throws SQLException;

    void deletarAula (Long id) throws SQLException;
}
