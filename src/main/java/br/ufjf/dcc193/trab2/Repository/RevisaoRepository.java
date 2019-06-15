package br.ufjf.dcc193.trab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.trab2.model.Revisao;

/**
 * RevisaoRepository
 */
@Repository
public interface RevisaoRepository extends JpaRepository<Revisao, Long>{

    
}