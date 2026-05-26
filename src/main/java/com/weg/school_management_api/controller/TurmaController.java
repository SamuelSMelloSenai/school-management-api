package com.weg.school_management_api.controller;

import com.weg.school_management_api.dto.turma.TurmaRequisicaoDTO;
import com.weg.school_management_api.dto.turma.TurmaRespostaDTO;
import com.weg.school_management_api.model.Turma;
import com.weg.school_management_api.service.TurmaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public TurmaRespostaDTO cadastrarTurma (@RequestBody TurmaRequisicaoDTO turmaRequisicaoDTO) {
        try {
            return turmaService.cadastrarTurma(turmaRequisicaoDTO);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<TurmaRespostaDTO> buscarTodasAsTurmas (){
        try {
            return turmaService.buscarTodasAsTurmas();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public TurmaRespostaDTO buscarTurmaPorId (@PathVariable Long id) {
        try {
            return turmaService.buscarTurmaPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public TurmaRespostaDTO atualizarTurma (@PathVariable Long id, @RequestBody Turma turma) {
        try {
            return turmaService.atualizarTurma(id, turma);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarTurma (@PathVariable Long id) {
        try {
            turmaService.deletarTurma(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
