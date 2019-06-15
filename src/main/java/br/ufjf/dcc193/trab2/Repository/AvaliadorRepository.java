package br.ufjf.dcc193.trab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trab2.model.Avaliador;


/**
 * AvaliadorRepository
 * 
 * 
 */
public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {

    Avaliador findByEmailAndCodigoAcesso(String email, int codigoAcesso);

    
}