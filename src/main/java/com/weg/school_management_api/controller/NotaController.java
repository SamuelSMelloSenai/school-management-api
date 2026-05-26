package com.weg.school_management_api.controller;

import com.weg.school_management_api.dto.nota.NotaRequisicaoDTO;
import com.weg.school_management_api.dto.nota.NotaRespostaDTO;
import com.weg.school_management_api.model.Nota;
import com.weg.school_management_api.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public NotaRespostaDTO cadastrarNota (@RequestBody NotaRequisicaoDTO notaRequisicaoDTO) {
        try {
            return notaService.cadastrarNota(notaRequisicaoDTO);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<NotaRespostaDTO> buscarTodasAsNotas () {
        try {
            return notaService.buscarTodasAsNotas();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public NotaRespostaDTO buscarNotaPorId (@PathVariable Long id) {
        try {
            return notaService.buscarNotaPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public NotaRespostaDTO atualizarNota (@PathVariable Long id, @RequestBody Nota nota) {
        try {
            return notaService.atualizarNota(id, nota);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarNota (@PathVariable Long id) {
        try {
            notaService.deletarNota(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
