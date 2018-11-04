package ar.com.mutant.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Carlos
 *
 */
public class DNAValidator {

	private static final String DNA_PATTERN = "[actgACTG]+";

	private static Pattern PATTERN = Pattern.compile(DNA_PATTERN);
	private static Matcher MATCHER;

	/**
	 * Validad si la secuencia es mutante o no
	 * 
	 * @param dna
	 * @return
	 */
	public static boolean validate(final String dna) {
		boolean result = false;
		if (dna != null && !dna.isEmpty()) {
			MATCHER = PATTERN.matcher(dna);
			result = MATCHER.matches();
		}
		return result;
	}
}