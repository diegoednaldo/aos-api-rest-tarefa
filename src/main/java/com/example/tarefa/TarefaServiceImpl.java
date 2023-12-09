package com.example.tarefa;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class TarefaServiceImpl implements TarefaService {

    private static Map<Integer, Tarefa> tarefas = new HashMap<>();
    private int nextId = 1;

    {
        insert(new Tarefa("Tarefa 1", false));
        insert(new Tarefa("Tarefa 2", true));
        insert(new Tarefa("Tarefa 3", false));
    }

    @Override
    public Tarefa getTarefa(int id) {
        return tarefas.get(id);
    }

    @Override
    public Collection<Tarefa> getTarefas() {
        return tarefas.values();
    }

    @Override
    public void insert(Tarefa tarefa) {
        tarefa.setId(nextId++);
        tarefas.put(tarefa.getId(), tarefa);
    }

    @Override
    public void update(Tarefa tarefa) {
        int id = tarefa.getId();
        if (tarefas.containsKey(id)) {
            tarefas.put(id, tarefa);
        }
    }

    @Override
    public void delete(int id) {
        Tarefa tarefa = tarefas.get(id);
        if (tarefa != null) {
            tarefas.remove(id);
        }
    }
}

