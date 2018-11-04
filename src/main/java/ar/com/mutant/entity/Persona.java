package ar.com.mutant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Carlos
 *
 */
@Document(collection = "persona")
public class Persona {

	@Id
	private String id;

	private String dna[];

	private boolean mutant;

	public Persona(String[] dna, boolean mutant) {
		this.dna = dna;
		this.mutant = mutant;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}

}