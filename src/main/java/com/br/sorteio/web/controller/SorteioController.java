package com.br.sorteio.web.controller;

import com.br.sorteio.service.SorteioService;
import com.br.sorteio.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/sorteio")
public class SorteioController {

    @Autowired
    private SorteioService sorteioService;

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping("/sorteio")
    public String sorteio(){

        return "sorteio/sorteio";
    }

    @PostMapping("/sortear")
    public String sortear(ModelMap model){
        model.addAttribute("participanteSorteado", List.of(sorteioService.realizarSorteio()));
        return "sorteio/sorteio";
    }

    private static Long getParticipanteSorteado(List<Long> participantesCadastrados, Long numeroSorteado) {
        return participantesCadastrados.stream().filter(numeroSorteado::equals).findAny().orElse(null);
    }

    private Long sortearNumero(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }
}
