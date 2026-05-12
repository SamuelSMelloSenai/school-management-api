package com.weg.school_management_api.controller;

import com.weg.school_management_api.dto.alunoDTO.AlunoRequisicaoDTO;
import com.weg.school_management_api.dto.alunoDTO.AlunoRespostaDTO;
import com.weg.school_management_api.model.Aluno;
import com.weg.school_management_api.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoRespostaDTO cadastrarAluno (@RequestBody AlunoRequisicaoDTO alunoRequisicaoDTO) {
        try {
            return alunoService.cadastrarAluno(alunoRequisicaoDTO);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<AlunoRespostaDTO> buscarTodosOsAlunos () {
        try {
            return alunoService.buscarTodosOsAlunos();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AlunoRespostaDTO buscarAlunoPorId (@PathVariable Long id) {
        try {
            return alunoService.buscarAlunoPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public AlunoRespostaDTO atualizarAluno (@PathVariable Long id, @RequestBody Aluno aluno) {
        try {
            return alunoService.atualizarAluno(id, aluno);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarAluno (@PathVariable Long id) {
        try {
            alunoService.deletarAluno(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
