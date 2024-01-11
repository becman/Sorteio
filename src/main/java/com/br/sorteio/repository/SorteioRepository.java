package com.br.sorteio.repository;

import com.br.sorteio.domain.Sorteio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SorteioRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public Sorteio salvar(Sorteio sorteio){
        entityManager.persist(sorteio);
        return sorteio;
    }
}
