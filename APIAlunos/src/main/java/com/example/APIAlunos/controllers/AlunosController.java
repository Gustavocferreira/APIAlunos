package com.example.APIAlunos.controllers;

import com.example.APIAlunos.models.Aluno;
import com.example.APIAlunos.services.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/alunos") // Apenas "/Alunos", pois o /api já vem do context-path
public class AlunosController {

    private final AlunoService alunoService;

    public AlunosController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return alunoService.getAlunos();
    }

    @GetMapping("/testes")
    public ResponseEntity<String> getTeste() {
        return ResponseEntity.ok("Rota funcionando!");
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) throws IOException {
        return alunoService.createAluno(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable long id, @RequestBody Aluno aluno) throws IOException {
        return alunoService.updateAluno(id, aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> removerAluno(@PathVariable long id) throws IOException {
        return alunoService.removeAluno(id);
    }
}
