package com.treina.recife.sgp.service;

import com.treina.recife.sgp.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {

    Page<Usuario> getUsuarios(Pageable pageable);

    Optional<Usuario> getUsuarioById(long userId);

    Usuario createUsuario(Usuario usuario);

    Usuario updateUsuario(Usuario usuario);

    void deleteUsuario(long userId);

    boolean isEmailAlreadyTaken(String email);
}