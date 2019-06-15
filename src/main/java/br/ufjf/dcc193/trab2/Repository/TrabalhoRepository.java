package br.ufjf.dcc193.trab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trab2.model.Trabalho;

/**
 * TrabalhoRepository
 */
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long>{

    
}