package com.treina.recife.sgp.repository;
import com.treina.recife.sgp.model.Projeto;
import com.treina.recife.sgp.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByProjetoProjectId(Projeto projectId);
}
