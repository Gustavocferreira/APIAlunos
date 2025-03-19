package com.example.APIAlunos.services;

import com.example.APIAlunos.models.Aluno;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private static final String FILE_PATH = "src/main/resources/alunos.json";
    private List<Aluno> alunos;
    private ObjectMapper objectMapper;

    public AlunoService() throws IOException {
        this.objectMapper = new ObjectMapper();
        this.alunos = carregarAlunos();
    }

    public ResponseEntity<List<Aluno>> getAlunos() {
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    public ResponseEntity<Aluno> createAluno(Aluno aluno) throws IOException {
        aluno.setId((long) (alunos.size() + 1)); // Gerando ID simples
        alunos.add(aluno);
        salvarAlunos();
        return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }

    public ResponseEntity<Aluno> updateAluno(long id, Aluno aluno) throws IOException {
        Optional<Aluno> optionalAluno = alunos.stream().filter(a -> a.getId() == id).findFirst();

        if (optionalAluno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Aluno alunoAtualizar = optionalAluno.get();
        alunoAtualizar.setNome(aluno.getNome());
        alunoAtualizar.setEmail(aluno.getEmail());
        alunoAtualizar.setDataNascimento(aluno.getDataNascimento());

        salvarAlunos();
        return ResponseEntity.ok(alunoAtualizar);
    }

    public ResponseEntity<Aluno> removeAluno(long id) throws IOException {
        Optional<Aluno> optionalAluno = alunos.stream().filter(a -> a.getId() == id).findFirst();

        if (optionalAluno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        alunos.remove(optionalAluno.get());
        salvarAlunos();
        return ResponseEntity.ok(optionalAluno.get());
    }

    private List<Aluno> carregarAlunos() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Aluno.class));
        } else {
            return new ArrayList<>();
        }
    }

    private void salvarAlunos() throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), alunos);
    }
}