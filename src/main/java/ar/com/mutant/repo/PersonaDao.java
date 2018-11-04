package ar.com.mutant.repo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import ar.com.mutant.entity.Persona;
import ar.com.mutant.entity.PersonaCount;

/**
 * @author Carlos
 *
 */
@Service
public class PersonaDao {

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * Hago la cuenta de cuantos Mutantes y Humanos se registraron
	 * 
	 * @return List<PersonaCount>
	 */
	public List<PersonaCount> countMutantHuman() {
		Aggregation agg = newAggregation(group("mutant").count().as("total"));

		AggregationResults<PersonaCount> groupResults = mongoTemplate.aggregate(agg, Persona.class, PersonaCount.class);
		List<PersonaCount> result = groupResults.getMappedResults();

		return result;
	}

}