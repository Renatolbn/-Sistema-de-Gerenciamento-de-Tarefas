package com.treina.recife.sgp.service.impl;
import com.treina.recife.sgp.model.Projeto;
import com.treina.recife.sgp.model.Usuario;
import com.treina.recife.sgp.repository.ProjetoRepository;
import com.treina.recife.sgp.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired
     ProjetoRepository projetoRepository;

     @Override
     public Page<Projeto> getProjetos(Pageable pageable) {
         return projetoRepository.findAll(pageable);
     }
 
     @Override
     public Optional<Projeto> getProjectById(long projectId) {
         return projetoRepository.findById(projectId);
     }
 
     @Override
     public Projeto createProjeto(Projeto projeto) {
         return projetoRepository.save(projeto);
     }
 
     @Override
     public Projeto updateProjeto(Projeto projeto) {
         return projetoRepository.save(projeto);
     }
 
     @Override
     public void deleteProjeto(long projectId)  {
         projetoRepository.deleteById(projectId);
     }
 
     @Override
     public Optional<Usuario> getResponsavel(long projectId) {
         return projetoRepository.findResponsavelByProjectId(projectId);
     }
     /*
 
     public List<ProjetoDTO> buscarProjetoPeloResponsavelId(Long id) {
         List<Projeto> projetos = projetoRepository.findByResponsavel_Id(id);
         return projetos.stream().map(p -> _toDTO(p)).toList();
     }
 
     public Projeto atualizarProjeto(Long id, Projeto dadosProjeto) {
         Optional<Projeto> projetoOpt = projetoRepository.findById(id);
 
         if (projetoOpt.isPresent()) {
             Projeto projeto = projetoOpt.get();
 
             projeto.setNome(dadosProjeto.getNome());
             projeto.setDescricao(dadosProjeto.getDescricao());
             projeto.setResponsavel(dadosProjeto.getResponsavel());
 
             return projetoRepository.save(projeto);
         }
 
         return null;
     }
 
     private ProjetoDTO _toDTO(Projeto projeto) {
         ProjetoDTO dto = new ProjetoDTO();
 
         dto.setId(projeto.getId());
         dto.setNome(projeto.getNome());
         dto.setDescricao(projeto.getDescricao());
         dto.setResponsavel(projeto.getResponsavel());
 
         List<Tarefa> tarefas = tarefaRepository.findByProjeto_Id(projeto.getId());
         dto.setTarefas(tarefas);
 
         List<Tarefa> pendentes = tarefas.stream()
                 .filter(tarefa -> StatusTarefa.PENDENTE.equals(tarefa.getStatus()))
                 .collect(Collectors.toList());
 
         List<Tarefa> emAndamento = tarefas.stream()
                 .filter(tarefa -> StatusTarefa.FAZENDO.equals(tarefa.getStatus()))
                 .collect(Collectors.toList());
 
         List<Tarefa> finalizadas = tarefas.stream()
                 .filter(tarefa -> StatusTarefa.FINALIZADA.equals(tarefa.getStatus()))
                 .collect(Collectors.toList());
 
         dto.setQtdTarefasPendentes(pendentes.size());
         dto.setQtdTarefasEmAndamento(emAndamento.size());
         dto.setQtdTarefasFinalizadas(finalizadas.size());
 
         return dto;
     }*/
 
 }
    

