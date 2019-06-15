package br.ufjf.dcc193.trab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trab2.model.Avaliador;


/**
 * AvaliadorRepository
 * 
 * 
 */
@Repository
public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {

    Avaliador findOneByEmailAndCodigoAcesso(String email, Long codigoAcesso);

    
}