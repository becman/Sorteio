package com.br.sorteio.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.br.sorteio.domain.Participante;
import com.br.sorteio.domain.Sorteio;
import com.br.sorteio.dto.ParticipanteSorteadoDTO;
import com.br.sorteio.repository.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class SorteioService {

    @Autowired
    private  SorteioRepository sorteioRepository;
    @Autowired
    private ParticipanteService participanteService;


    @Transactional(rollbackFor =  Exception.class)
    public Sorteio salvarSorteio(Sorteio sorteio){
        return sorteioRepository.salvar(sorteio);
    }
    @Transactional(rollbackFor =  Exception.class)
    public ParticipanteSorteadoDTO realizarSorteio(){
        List<Long> idsCadastrados = participanteService.buscarTodosIds();

        var numeroSorteado = sortearNumero(idsCadastrados.size());
        Long idSorteado = getParticipanteSorteado((long) numeroSorteado);

        var participanteSorteado = participanteService.findById(idSorteado);

        Sorteio sorteio = sorteioRepository.salvar(new Sorteio(LocalDateTime.now(), participanteSorteado));

        var ganhador = new ParticipanteSorteadoDTO(participanteSorteado.getId(),
                participanteSorteado.getNome(),
                sorteio.getDataHora(),
                participanteSorteado.getNumeroSeguroSocial());

        return ganhador;

    }

    private int sortearNumero(int max) {
        Random random = new Random();
        return random.nextInt(max)+1;
    }

    private Long getParticipanteSorteado(List<Long> participantesCadastrados, Long numeroSorteado) {
        return participantesCadastrados.stream().filter(numeroSorteado::equals).findAny().orElse(null);
    }

    private Long getParticipanteSorteado(Long numeroSorteado) {
        Participante p =  participanteService.findById(numeroSorteado);
        return p.getId();
    }
}
