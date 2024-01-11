package com.br.sorteio.repository;

import com.br.sorteio.domain.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    @Query("select p from Participante p where p.email like :email and p.numeroSeguroSocial = :numeroSeguroSocial")
    Optional<Participante> findByEmail(@Param("email") String email, @Param("numeroSeguroSocial") String numeroSeguroSocial);

    @Query(" select p.id from Participante p order by p.id asc")
    List<Long> findAllIds();



}
