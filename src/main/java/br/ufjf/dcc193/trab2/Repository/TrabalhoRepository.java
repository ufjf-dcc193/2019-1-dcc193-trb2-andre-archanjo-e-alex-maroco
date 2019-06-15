package br.ufjf.dcc193.trab2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trab2.Model.Trabalho;

/**
 * TrabalhoRepository
 */
public interface TrabalhoRepository extends JpaRepository<Long, Trabalho>{

    
}