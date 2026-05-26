package com.weg.school_management_api.controller;

import com.weg.school_management_api.dto.curso.CursoRequisicaoDTO;
import com.weg.school_management_api.dto.curso.CursoRespostaDTO;
import com.weg.school_management_api.model.Curso;
import com.weg.school_management_api.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public CursoRespostaDTO cadastrarCurso (@RequestBody CursoRequisicaoDTO cursoRequisicaoDTO) {
        try {
            return cursoService.cadastrarCurso(cursoRequisicaoDTO);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<CursoRespostaDTO> buscarTodosOsCursos () {
        try {
            return cursoService.buscarTodosOsCursos();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CursoRespostaDTO buscarCursoPorId (@PathVariable Long id) {
        try {
            return cursoService.buscarCursoPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CursoRespostaDTO atualizarCurso (@PathVariable Long id, @RequestBody Curso curso) {
        try {
            return cursoService.atualizarCurso(id, curso);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarCurso (@PathVariable Long id) {
        try {
            cursoService.deletarCurso(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
