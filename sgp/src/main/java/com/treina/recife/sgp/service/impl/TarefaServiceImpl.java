package com.treina.recife.sgp.service.impl;
import com.treina.recife.sgp.model.Projeto;
import com.treina.recife.sgp.model.Tarefa;
import com.treina.recife.sgp.repository.TarefaRepository;
import com.treina.recife.sgp.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Override
    public Page<Tarefa> getTarefas(Pageable pageable) {
        return tarefaRepository.findAll(pageable);
    }

    @Override
    public Optional<Tarefa> getTarefaById(long tarefaId) {
       return tarefaRepository.findById(tarefaId);
    }

    @Override
    public Tarefa createTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @Override
    public void deleteTarefaById(long tarefaId) {
       tarefaRepository.deleteById(tarefaId);
    }

    @Override
    public Tarefa updateTarefa(Tarefa tarefa) {
       return tarefaRepository.save(tarefa);
    }

    @Override
    public List<Tarefa> findByProjetoProjectId(Projeto projectId) {
       return tarefaRepository.findByProjetoProjectId(projectId);
    }

   

    
   
}