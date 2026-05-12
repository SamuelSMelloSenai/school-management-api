package com.weg.school_management_api.controller;

import com.weg.school_management_api.dto.professorDTO.ProfessorRequisicaoDTO;
import com.weg.school_management_api.dto.professorDTO.ProfessorRespostaDTO;
import com.weg.school_management_api.model.Professor;
import com.weg.school_management_api.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorRespostaDTO cadastrarProfessor (@RequestBody ProfessorRequisicaoDTO professorRequisicaoDTO) {
        try {
            return professorService.cadastrarProfessor(professorRequisicaoDTO);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<ProfessorRespostaDTO> buscarTodosOsProfessores () {
        try {
            return professorService.buscarTodosOsProfessors();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ProfessorRespostaDTO buscarProfessorPorId (@PathVariable Long id) {
        try {
            return professorService.buscarProfessorPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ProfessorRespostaDTO atualizarProfessor (@PathVariable Long id, @RequestBody Professor professor) {
        try {
            return professorService.atualizarProfessor(id, professor);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarProfessor (@PathVariable Long id) {
        try {
            professorService.deletarProfessor(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
