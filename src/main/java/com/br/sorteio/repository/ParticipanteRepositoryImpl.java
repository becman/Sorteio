package com.br.sorteio.repository;

import com.br.sorteio.util.PaginacaoUtil;
import com.br.sorteio.domain.Participante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipanteRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public PaginacaoUtil<Participante> buscaPaginada(int pagina, String direcao) {
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho;
        List<Participante> cargos = entityManager
                .createQuery("select p from Participante p order by p.nome " + direcao, Participante.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, cargos);
    }

    public long count() {
        return entityManager
                .createQuery("select count(*) from Participante", Long.class)
                .getSingleResult();
    }
}
