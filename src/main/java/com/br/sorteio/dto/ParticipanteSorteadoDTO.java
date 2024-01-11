package com.br.sorteio.dto;

import java.time.LocalDateTime;

public class ParticipanteSorteadoDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataSorteio;
    private String numeroSeguro;

    public ParticipanteSorteadoDTO(Long id, String nome, LocalDateTime dataSorteio, String numeroSeguro) {
        this.id = id;
        this.nome = nome;
        this.dataSorteio = dataSorteio;
        this.numeroSeguro = numeroSeguro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(LocalDateTime dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }
}
