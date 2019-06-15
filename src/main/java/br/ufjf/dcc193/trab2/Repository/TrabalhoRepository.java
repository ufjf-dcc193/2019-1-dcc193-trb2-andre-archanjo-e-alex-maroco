package br.ufjf.dcc193.trab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trab2.model.Trabalho;

/**
 * TrabalhoRepository
 */
@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long>{

    
}