package com.weg.school_management_api.controller;

import com.weg.school_management_api.dto.aula.AulaRequisicaoDTO;
import com.weg.school_management_api.dto.aula.AulaRespostaDTO;
import com.weg.school_management_api.model.Aula;
import com.weg.school_management_api.service.AulaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public AulaRespostaDTO cadastrarAula (@RequestBody AulaRequisicaoDTO aulaRequisicaoDTO) {
        try {
            return aulaService.cadastrarAula(aulaRequisicaoDTO);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<AulaRespostaDTO> buscarTodasAsAulas () {
        try {
            return aulaService.buscarTodasAsAulas();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AulaRespostaDTO buscarAulaPorId (@PathVariable Long id) {
        try {
            return aulaService.buscarAulaPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public AulaRespostaDTO atualizarAula (@PathVariable Long id, @RequestBody Aula aula) {
        try {
            return aulaService.atualizarAula(id, aula);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarAula (@PathVariable Long id) {
        try {
            aulaService.deletarAula(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
