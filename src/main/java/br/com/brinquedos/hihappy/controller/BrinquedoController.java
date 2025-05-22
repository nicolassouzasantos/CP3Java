package br.com.brinquedos.hihappy.controller;

import br.com.brinquedos.hihappy.entity.Brinquedo;
import br.com.brinquedos.hihappy.repository.BrinquedoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoRepository repository;

    @GetMapping
    public List<Brinquedo> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brinquedo> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(brinquedo -> ResponseEntity.ok(brinquedo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Brinquedo> criar(@Valid @RequestBody Brinquedo brinquedo) {
        return ResponseEntity.ok(repository.save(brinquedo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizar(@PathVariable Long id, @Valid @RequestBody Brinquedo brinquedo) {
        return repository.findById(id)
                .map(b -> {
                    brinquedo.setId(id);
                    return ResponseEntity.ok(repository.save(brinquedo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(b -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

