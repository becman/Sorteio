package com.br.sorteio.service;

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

        var numeroSorteado = sortearNumero(idsCadastrados.stream().mapToLong(n -> n).max().getAsLong());

        Long idSorteado = getParticipanteSorteado(idsCadastrados, numeroSorteado);

        while (ObjectUtils.isEmpty(idSorteado)){
            numeroSorteado = sortearNumero(idsCadastrados.stream().mapToLong(n -> n).max().getAsLong());
            idSorteado = getParticipanteSorteado(idsCadastrados, numeroSorteado);
        }

        var participanteSorteado = participanteService.findById(idSorteado);

        Sorteio sorteio = sorteioRepository.salvar(new Sorteio(LocalDateTime.now(), participanteSorteado));

        var ganhador = new ParticipanteSorteadoDTO(participanteSorteado.getId(),
                participanteSorteado.getNome(),
                sorteio.getDataHora(),
                participanteSorteado.getNumeroSeguroSocial());

        return ganhador;

    }

    private Long sortearNumero(Long max) {
        Random random = new Random(max++);
        return random.nextLong();
    }

    private Long getParticipanteSorteado(List<Long> participantesCadastrados, Long numeroSorteado) {
        return participantesCadastrados.stream().filter(numeroSorteado::equals).findAny().orElse(null);
    }
}
