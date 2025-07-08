package com.treina.recife.sgp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.treina.recife.sgp.constants.Prioridade;
import com.treina.recife.sgp.constants.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Tarefa")

public class Tarefa {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "taskId")
    private long taskId;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataCriacao", nullable = false)
    private LocalDate dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataConclusao")
    private LocalDate dataConclusao;

    @Column(name = "PRIORIDADE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @ManyToOne
    @JoinColumn(name ="projectId", referencedColumnName = "projectId", nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name ="userId", referencedColumnName = "userId", nullable = false)
    private Usuario usuario;
}







