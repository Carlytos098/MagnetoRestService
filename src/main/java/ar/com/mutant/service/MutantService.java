package ar.com.mutant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.mutant.dto.Stats;
import ar.com.mutant.entity.Persona;
import ar.com.mutant.entity.PersonaCount;
import ar.com.mutant.repo.PersonaDao;
import ar.com.mutant.repo.PersonaRepo;
import ar.com.mutant.validador.DNAMutantHelper;
import ar.com.mutant.validador.MatrizDNAHelper;
import ar.com.mutant.validador.MatrizDNAValidator;

/**
 * @author Carlos
 *
 *         Servicio para que valida, registra y obtiene estadisticas mutantes y
 *         humanos
 */
@Service
public class MutantService {

	private static final Integer CANT_MINIMO_SECUENCIAS_MUTANTE = 1;
	public static final Integer LENGTH_SEC_MUTANTE = 4;

	@Autowired
	private PersonaRepo personaRepo;
	@Autowired
	private PersonaDao personaDao;

	/**
	 * Devuelve si el DNA es de un mutante o no
	 * 
	 * @param dna
	 * @return
	 */
	public boolean isMutant(String[] dna) {
		boolean result = false;
		if (MatrizDNAValidator.validarMatrizDeDNA(dna)) {
			result = validarDNAMutante(dna);
			personaRepo.save(new Persona(dna, result));
		}
		return result;
	}

	/**
	 * Metodo para obtener las estadisticas de las Personas
	 * 
	 * @return
	 */
	public Stats getStas() {
		List<PersonaCount> personaCount = personaDao.countMutantHuman();
		Stats stats = new Stats();
		for (PersonaCount count : personaCount) {
			if (count.isId()) {
				stats.setCount_mutant_dna(count.getTotal());
			} else {
				stats.setCount_human_dna(count.getTotal());
			}
		}
		stats.setRatio(1.0 * stats.getCount_mutant_dna() / stats.getCount_human_dna());
		return stats;
	}

	/**
	 * Recorre la matriz DNA buscando secuencias mutantes devolviendo si es mutante
	 * o no, recoriendo horizontal, vertical y en las diagonales de la matriz de DNA
	 * que se ingresa y validando si encuentra la secuencia mutante mas de dos veces
	 * no siga recorriendo la matriz.
	 * 
	 * @param dna
	 * @return
	 */
	private boolean validarDNAMutante(String[] dna) {
		int contadorDNAMutante = 0;

		try {
			contadorDNAMutante = recorridoHorizontal(dna, contadorDNAMutante);

			if (contadorDNAMutante <= CANT_MINIMO_SECUENCIAS_MUTANTE) {
				contadorDNAMutante = recorridoVertical(dna, contadorDNAMutante);

				if (contadorDNAMutante <= CANT_MINIMO_SECUENCIAS_MUTANTE) {
					contadorDNAMutante = recorridoDiagonalPositivo(dna, contadorDNAMutante);

					if (contadorDNAMutante <= CANT_MINIMO_SECUENCIAS_MUTANTE) {
						contadorDNAMutante = recorridoDiagonalNegativo(dna, contadorDNAMutante);
						return (contadorDNAMutante > CANT_MINIMO_SECUENCIAS_MUTANTE);
					}
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * Busca DNA horizontalmente
	 * 
	 * @param matrizDna
	 * @param contadorDNAMutante
	 * @return
	 * @throws Exception
	 */
	private int recorridoHorizontal(String[] matrizDna, int contadorDNAMutante) throws Exception {
		int fila = 1;
		String cadenaDNA = null;
		int contador = contadorDNAMutante;
		while (contador <= CANT_MINIMO_SECUENCIAS_MUTANTE && fila <= matrizDna.length) {
			cadenaDNA = MatrizDNAHelper.getFila(matrizDna, fila);
			contador = contador + DNAMutantHelper.countDNAMutante(cadenaDNA);
			fila++;
		}
		return contador;
	}

	/**
	 * Busca DNA verticalmente
	 * 
	 * @param matrizDna
	 * @param contadorDNAMutante
	 * @return
	 * @throws Exception
	 */
	private int recorridoVertical(String[] matrizDna, int contadorDNAMutante) throws Exception {
		int columna = 1;
		String cadenaDNA = null;
		int contador = contadorDNAMutante;
		while (contador <= CANT_MINIMO_SECUENCIAS_MUTANTE && columna <= matrizDna.length) {
			cadenaDNA = MatrizDNAHelper.getColumna(matrizDna, columna);
			contador = contador + DNAMutantHelper.countDNAMutante(cadenaDNA);
			columna++;
		}
		return contador;
	}

	/**
	 * Busca DNA diagonalmente(positiva)
	 * 
	 * @param matrizDna
	 * @param contadorDNAMutante
	 * @return
	 * @throws Exception
	 */
	private int recorridoDiagonalPositivo(String[] matrizDna, int contadorDNAMutante) throws Exception {
		int diagonal = LENGTH_SEC_MUTANTE;
		String cadenaDNA = null;
		int contador = contadorDNAMutante;
		while ((contador <= CANT_MINIMO_SECUENCIAS_MUTANTE)
				&& MatrizDNAHelper.getLengthDiagonal(matrizDna, diagonal) >= LENGTH_SEC_MUTANTE) {
			cadenaDNA = MatrizDNAHelper.getDiagonalPositiva(diagonal, matrizDna);
			contador = contador + DNAMutantHelper.countDNAMutante(cadenaDNA);
			diagonal++;
		}
		return contador;
	}

	/**
	 * Busca DNA diagonalmente (negativa)
	 * 
	 * @param matrizDna
	 * @param contadorDNAMutante
	 * @return
	 * @throws Exception
	 */
	private int recorridoDiagonalNegativo(String[] matrizDna, int contadorDNAMutante) throws Exception {
		int diagonal = LENGTH_SEC_MUTANTE;
		String cadenaDNA = null;
		int contador = contadorDNAMutante;
		while ((contador <= CANT_MINIMO_SECUENCIAS_MUTANTE)
				&& MatrizDNAHelper.getLengthDiagonal(matrizDna, diagonal) >= LENGTH_SEC_MUTANTE) {
			cadenaDNA = MatrizDNAHelper.getDiagonalNegativa(diagonal, matrizDna);
			contador = contador + DNAMutantHelper.countDNAMutante(cadenaDNA);
			diagonal++;
		}
		return contador;
	}

}