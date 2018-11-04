package ar.com.mutant.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.mutant.service.MutantService;

/**
 * @author Carlos
 *
 */
public class DNAMutantHelper {

	private static final String MUTANT_DNA_PATTERN = "((?<!a)a{" + MutantService.LENGTH_SEC_MUTANTE + "}(?!a))|"
			+ "((?<!c)c{" + MutantService.LENGTH_SEC_MUTANTE + "}(?!c))|" + "((?<!g)g{"
			+ MutantService.LENGTH_SEC_MUTANTE + "}(?!g))|" + "((?<!t)t{" + MutantService.LENGTH_SEC_MUTANTE
			+ "}(?!t))";

	private static Pattern PATTERN = Pattern.compile(MUTANT_DNA_PATTERN);
	private static Matcher MATCHER;

	/**
	 * Cuenta la cantidad de secuencias mutantes encontradas en la cadena
	 * 
	 * @param cadenaDNA
	 * @return
	 */
	public static int countDNAMutante(final String cadenaDNA) {
		int contador = 0;
		if (cadenaDNA != null && !cadenaDNA.isEmpty()) {
			String dna = cadenaDNA.toLowerCase();
			MATCHER = PATTERN.matcher(dna);
			while (MATCHER.find()) {
				contador++;
			}
		}
		return contador;
	}
}