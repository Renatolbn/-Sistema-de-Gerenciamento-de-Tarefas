package com.treina.recife.sgp.controller;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treina.recife.sgp.constants.StatusUsuario;
import com.treina.recife.sgp.dto.UsuarioDto;
import com.treina.recife.sgp.model.Usuario;
import com.treina.recife.sgp.service.UsuarioService;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    Logger logger = LogManager.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> getUsuarios(@PageableDefault(sort = "userId", direction  = Sort.Direction.ASC) Pageable pageable) {

        Page<Usuario> usuarios = usuarioService.getUsuarios(pageable);

        if (usuarios.isEmpty()) {
            logger.info("Ainda não há usuarios cadastrados.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Page.empty());
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }
    }
     @GetMapping("/{usuarioId}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable(value = "usuarioId") long usuarioId) {

        Optional<Usuario> usuario = usuarioService.getUsuarioById(usuarioId);

        if (usuario.isEmpty()) {
            logger.warn("Usuário não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }else{
            logger.info(usuario.get().toString());
            return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        boolean emailAlredyTaken = usuarioService.isEmailAlreadyTaken(usuarioDto.getEmail());

        if (emailAlredyTaken) {
            logger.error("{} já está em uso", usuarioDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(usuarioDto.getEmail() + "já está em uso");
        }else {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(usuarioDto.getNome());
            novoUsuario.setCpf(usuarioDto.getCpf());
            novoUsuario.setEmail(usuarioDto.getEmail());
            novoUsuario.setSenha(usuarioDto.getSenha());
            novoUsuario.setDataNascimento(usuarioDto.getDataNascimento());
            novoUsuario.setStatus(StatusUsuario.ATIVO);

            usuarioService.createUsuario(novoUsuario);

            logger.info("Usuário {} criado com sucesso", novoUsuario.getUserId());

            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        }
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "userId") long userId) {

            Optional<Usuario> usuario = usuarioService.getUsuarioById(userId);

            if (usuario.isEmpty()) {
                logger.warn("Usuário não encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }else{
                usuarioService.deleteUsuario(userId);
                logger.info("Usuário {} deletado com sucesso", userId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso");
            }
        }

        @PutMapping("/{userId}")
         public ResponseEntity<Object> updateUsuario(@PathVariable(value = "userId") long userId, @RequestBody UsuarioDto usuarioDto) {

            Optional<Usuario> usuario = usuarioService.getUsuarioById(userId);

            if (usuario.isEmpty()) {
                logger.error("Usuário não encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }else { 
                Usuario usuarioAtualizado = usuario.get();
                usuarioAtualizado.setNome(usuarioDto.getNome());
                usuarioAtualizado.setCpf(usuarioDto.getCpf());
                usuarioAtualizado.setEmail(usuarioDto.getEmail());
                usuarioAtualizado.setDataNascimento(usuarioDto.getDataNascimento());
                usuarioAtualizado.setStatus(usuarioDto.getStatus());

                usuarioService.updateUsuario(usuarioAtualizado);

                logger.info("Usuário de ID {} atualizado com sucesso", usuarioAtualizado.getUserId());
                return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
            }
        }

        @PatchMapping("/{userId}/status")
        public ResponseEntity<Object> atualizarStatus(@PathVariable(value = "userId") long userId, @RequestBody Map<String, String> body) {
            Optional<Usuario> usuario = usuarioService.getUsuarioById(userId);

            if (usuario.isEmpty()) {
                logger.error("Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
            }

            String statusBody = body.get("status");

            if (statusBody == null) {
                return ResponseEntity.badRequest().body("Status é obrigaório.");
            }

            StatusUsuario novoStatus;

            try {
                novoStatus = StatusUsuario.valueOf(statusBody.toUpperCase());
            } catch (IllegalArgumentException e) {
               return ResponseEntity.badRequest().body("Status inválido. Valores permitidos: ATIVO, INATIVO");
            }

            Usuario usuarioAtualizado = usuario.get();
            usuarioAtualizado.setStatus(novoStatus);

            usuarioService.updateUsuario(usuarioAtualizado);
            logger.info("Status do usuário atualizado com sucesso.");

            return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
        }
    }
            


                

            
                



            

            
        

    



