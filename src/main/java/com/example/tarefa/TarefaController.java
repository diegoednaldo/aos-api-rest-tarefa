package com.example.tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping(value = "/gerenciadorTarefas")
@CrossOrigin
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping(value = "/tarefas", produces = {"application/json", "application/xml"})
    public ResponseEntity<Collection<Tarefa>> getTarefas() {
        Collection<Tarefa> result = service.getTarefas();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/tarefa/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Tarefa> getTarefa(@PathVariable int id) {
        Tarefa result = service.getTarefa(id);
        if (result == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/tarefa",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa) {
        service.insert(tarefa);
        URI uri = URI.create("/tarefa/" + tarefa.getId());
        return ResponseEntity.created(uri).body(tarefa);
    }

    @PutMapping(value = "/tarefa/{id}", consumes = {"application/json", "application/xml"})
    public ResponseEntity modificarTarefa(@PathVariable int id, @RequestBody Tarefa tarefa) {
        if (service.getTarefa(id) == null)
            return ResponseEntity.notFound().build();
        else {
            service.update(tarefa);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/tarefa/{id}")
    public ResponseEntity excluirTarefa(@PathVariable int id) {
        if (service.getTarefa(id) == null)
            return ResponseEntity.notFound().build();
        else {
            service.delete(id);
            return ResponseEntity.ok().build();
        }
    }
}
