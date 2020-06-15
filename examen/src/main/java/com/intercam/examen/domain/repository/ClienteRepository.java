package com.intercam.examen.domain.repository;

import com.intercam.examen.domain.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
