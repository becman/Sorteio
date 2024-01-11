package com.br.sorteio.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Table(name = "Sorteio")
public class Sorteio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "")
    @Column(name= "data_hora", nullable = false, columnDefinition = "DATE")
    private LocalDateTime dataHora;

    @Valid
    @ManyToOne
    @JoinColumn(name = "participante_id_fk")
    private Participante participante;

    public Sorteio() {  }
    public Sorteio(LocalDateTime dataHora, Participante participante) {
        this.dataHora = dataHora;
        this.participante = participante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
