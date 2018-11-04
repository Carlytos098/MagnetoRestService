package ar.com.mutant.validador;

/**
 * @author Carlos
 *
 */
public class MatrizDNAHelper {

	/**
	 * Retorna una fila determinada
	 * 
	 * @param matrizDna
	 * @param fila
	 * @return
	 * @throws Exception
	 */
	public static String getFila(String[] matrizDna, int fila) throws Exception {
		if (fila > 0 && fila <= matrizDna.length) {
			return matrizDna[fila - 1];
		}
		throw new Exception();
	}

	/**
	 * Retorna una columna determinada
	 * 
	 * @param matrizDna
	 * @param columna
	 * @return
	 * @throws Exception
	 */
	public static String getColumna(String[] matrizDna, int columna) throws Exception {
		if (columna > 0 && columna <= matrizDna.length) {
			String cadena = "";
			for (int f = 0; f < matrizDna.length; f++) {
				cadena = cadena.concat(matrizDna[f].substring(columna - 1, columna));
			}
			return cadena;
		}
		throw new Exception();
	}

	/**
	 * Retorna el total de diagonales que posee una matriz
	 * 
	 * @param matrizDna
	 * @return
	 */
	public static int totDiagonales(String[] matrizDna) {
		return (matrizDna.length * 2) - 1;
	}

	/**
	 * Retorna la longitud de una diagonal determinada
	 * 
	 * @param matrizDna
	 * @param diagonal
	 * @return
	 * @throws Exception
	 */
	public static int getLengthDiagonal(String[] matrizDna, int diagonal) throws Exception {
		if (diagonal > 0 && diagonal <= totDiagonales(matrizDna)) {
			if (diagonal > 0 && diagonal <= matrizDna.length) {
				return diagonal;
			} else {
				return (matrizDna.length * 2) - diagonal;
			}
		}
		throw new Exception();
	}

	/**
	 * Retorna un elemento de la matriz dada una fila y una columna
	 * 
	 * @param matrizDna
	 * @param f
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static String getElement(String[] matrizDna, int f, int c) throws Exception {
		if (f > 0 && c > 0 && f <= matrizDna.length && c <= matrizDna.length) {
			String fila = getFila(matrizDna, f);
			return fila.substring(c - 1, c);
		}
		throw new Exception();
	}

	/**
	 * Retorna una diagonal (con pendiente positiva) determinada
	 * 
	 * @param diagonal
	 * @param matrizDna
	 * @return
	 * @throws Exception
	 */
	public static String getDiagonalPositiva(int diagonal, String[] matrizDna) throws Exception {
		String cadena = "";
		int fila = getFilaInicioDiagonalPositiva(diagonal, matrizDna);
		int columna = getColumnaInicioDiagonalPositiva(diagonal, matrizDna);
		int lengthDiagonal = getLengthDiagonal(matrizDna, diagonal);
		for (int i = 1; i <= lengthDiagonal; i++) {
			cadena = cadena.concat(getElement(matrizDna, fila, columna));
			fila--;
			columna++;
		}
		return cadena;
	}

	/**
	 * Retorna la fila de inicio de una diagonal con pendiente positiva (Recorrido
	 * L)
	 * 
	 * @param diagonal
	 * @param matrizDna
	 * @return
	 * @throws Exception
	 */
	private static int getFilaInicioDiagonalPositiva(int diagonal, String[] matrizDna) throws Exception {
		if (diagonal > 0 && diagonal <= totDiagonales(matrizDna)) {
			int fila;
			if (diagonal <= matrizDna.length) {
				fila = diagonal;
			} else {
				fila = matrizDna.length;
			}
			return fila;
		}
		throw new Exception();
	}

	/**
	 * Retorna la columna de inicio de una diagonal con pendiente positiva
	 * (Recorrido L)
	 * 
	 * @param diagonal
	 * @param matrizDna
	 * @return
	 * @throws Exception
	 */
	private static int getColumnaInicioDiagonalPositiva(int diagonal, String[] matrizDna) throws Exception {
		if (diagonal > 0 && diagonal <= totDiagonales(matrizDna)) {
			int columna;
			if (diagonal <= matrizDna.length) {
				columna = 1;
			} else {
				columna = diagonal - matrizDna.length + 1;
			}
			return columna;
		}
		throw new Exception();
	}

	/**
	 * Retorna una diagonal (con pendiente negativa) determinada
	 * 
	 * @param diagonal
	 * @param matrizDna
	 * @return
	 * @throws Exception
	 */
	public static String getDiagonalNegativa(int diagonal, String[] matrizDna) throws Exception {
		String cadena = "";
		int fila = getFilaInicioDiagonalNegativa(diagonal, matrizDna);
		int columna = getColumnaInicioDiagonalNegativa(diagonal, matrizDna);
		;
		int lengthDiagonal = getLengthDiagonal(matrizDna, diagonal);
		for (int i = 1; i <= lengthDiagonal; i++) {
			cadena = cadena.concat(getElement(matrizDna, fila, columna));
			fila--;
			columna--;
		}
		return cadena;
	}

	/**
	 * Retorna la fila de inicio de una diagonal con pendiente negativa (Recorrido L
	 * invertido)
	 * 
	 * @param diagonal
	 * @param matrizDna
	 * @return
	 * @throws Exception
	 */
	private static int getFilaInicioDiagonalNegativa(int diagonal, String[] matrizDna) throws Exception {
		if (diagonal > 0 && diagonal <= totDiagonales(matrizDna)) {
			int fila;
			if (diagonal <= matrizDna.length) {
				fila = diagonal;
			} else {
				fila = matrizDna.length;
			}
			return fila;
		}
		throw new Exception();
	}

	/**
	 * Retorna la columna de inicio de una diagonal con pendiente negativa
	 * (Recorrido L invertido)
	 * 
	 * @param diagonal
	 * @param matrizDna
	 * @return
	 * @throws Exception
	 */
	private static int getColumnaInicioDiagonalNegativa(int diagonal, String[] matrizDna) throws Exception {
		if (diagonal > 0 && diagonal <= totDiagonales(matrizDna)) {
			int columna;
			if (diagonal <= matrizDna.length) {
				columna = matrizDna.length;
			} else {
				columna = ((matrizDna.length * 2) - diagonal);
			}
			return columna;
		}
		throw new Exception();
	}

}
