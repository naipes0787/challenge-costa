package ar.com.wolox.challengecosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.wolox.challengecosta.model.AccessType;

@Repository
public interface AccessTypeRepository extends JpaRepository<AccessType, Long> {

}