package ar.com.mutant.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.com.mutant.entity.Persona;

/**
 * @author Carlos
 *
 */
public interface PersonaRepo extends MongoRepository<Persona, Long> {

}