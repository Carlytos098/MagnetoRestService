package ar.com.mutant.validador;

import ar.com.mutant.service.MutantService;

/**
 * @author Carlos
 *
 */
public class MatrizDNAValidator {

	/**
	 * Se valida que la matriz sea de NxN, con ADN valido
	 * 
	 * @param args
	 * @return
	 */
	public static boolean validarMatrizDeDNA(String[] args) {
		boolean result = false;
		String subCadenaDNA = null;
		if (args != null && args.length >= MutantService.LENGTH_SEC_MUTANTE) {
			int contador = 1;
			boolean flag = true;
			while (contador < args.length && flag) {
				subCadenaDNA = args[contador];
				if (subCadenaDNA == null || subCadenaDNA.length() != args.length
						|| !DNAValidator.validate(subCadenaDNA)) {
					flag = false;
				}
				contador++;
			}
			result = flag;
		}
		return result;
	}
}
