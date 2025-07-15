package com.treina.recife.sgp.service;

import com.treina.recife.sgp.model.Projeto;
import com.treina.recife.sgp.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjetoService {

    Page<Projeto> getProjetos(Pageable pageable);

    Optional<Projeto> getProjectById(long projectId);

    Projeto createProjeto(Projeto projeto);

    Projeto updateProjeto(Projeto projeto);

    void deleteProjeto(long projectId);

    Optional<Usuario> getResponsavel(long projectId);
}