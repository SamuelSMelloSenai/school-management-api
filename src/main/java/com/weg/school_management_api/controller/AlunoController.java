package com.weg.school_management_api.controller;

import com.weg.school_management_api.dto.alunoDTO.AlunoRequisicaoDTO;
import com.weg.school_management_api.dto.alunoDTO.AlunoRespostaDTO;
import com.weg.school_management_api.service.AlunoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
}
