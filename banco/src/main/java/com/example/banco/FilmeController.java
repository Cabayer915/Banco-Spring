package com.example.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeRepository repository;
    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filme) {
        Filme filmeSalvo = this.repository.save(filme);

        return ResponseEntity.status(201).body(filmeSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        List<Filme> filmes = this.repository.findAll();

        if (filmes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Integer id){

        Optional<Filme> filmeOpt = repository.findById(id);

        if (filmeOpt.isPresent()){
            Filme filmeEncontrado = filmeOpt.get();
            return ResponseEntity.status(200).body(filmeEncontrado);
        }
        return ResponseEntity.status(404).build();
    }
}
