package com.br.sorteio.service;

import com.br.sorteio.exception.ParticipanteCadastradoException;
import com.br.sorteio.repository.ParticipanteRepositoryImpl;
import com.br.sorteio.util.PaginacaoUtil;
import com.br.sorteio.domain.Participante;
import com.br.sorteio.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParticipanteService {


    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private ParticipanteRepositoryImpl paginacaoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Participante salvar(Participante participante) {
        return participanteRepository.save(participante);
    }

    public List<Participante> buscarTodos() {
        return participanteRepository.findAll();
    }

    public Participante findById(Long id) {
        return participanteRepository.findById(id).orElseThrow();
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(Participante participante) {
        participanteRepository.save(participante);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluir(Long id) {
        participanteRepository.deleteById(id);
    }

    public List<Long> buscarTodosIds() {
        return participanteRepository.findAllIds();
    }

    @Transactional(rollbackFor = Exception.class)
    public void salvarCadastroPaciente(Participante participante) {
        participanteRepository.findByEmail(participante.getEmail(), participante.getNumeroSeguroSocial())
                .ifPresent(p -> {
                    throw new ParticipanteCadastradoException("Participante já cadastrado!");
                });

        participanteRepository.save(participante);

    }

    public PaginacaoUtil<Participante> buscarPorPagina(int pagina, String direcao) {
        return paginacaoRepository.buscaPaginada(pagina, direcao);
    }
}
