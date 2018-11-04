package ar.com.mutant;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ar.com.mutant.controller.MagnetoRestController;
import ar.com.mutant.dto.Mutant;

/**
 * @author Carlos
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagnetoRestServiceApplication.class)
@SpringBootTest
public class MagnetoRestServiceApplicationTests {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private MagnetoRestController magnetoRestController;

	@Test
	public void testMutante() {
		/*List<String> test = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			test.add(getDnaSeq(6));
		}*/
		
		String[] test = { "{\"dna\":[\"AAAAAA\",\"TACGTA\",\"GTACGT\",\"ACGTAC\",\"GTACGT\",\"ACGTAC\"]}",
				"{\"dna\":[\"AGTACG\",\"TACGTA\",\"GCAACG\",\"GAGATA\",\"CTCTAC\",\"CAGGTA\"]}",
				"{\"dna\":[\"ACGTAC\",\"TACGGA\",\"GTAGGT\",\"ACGAAC\",\"GGACGT\",\"ACGTAC\"]}",
				"{\"dna\":[\"ACGTAC\",\"TACGTA\",\"GTACGC\",\"ACGAAC\",\"GTACGC\",\"ACGTAC\"]}",
				"{\"dna\":[\"ACAAAA\",\"TACGTA\",\"GTACGC\",\"CAAAAC\",\"GTACGG\",\"ACGTAC\"]}",
				"{\"dna\":[\"GCGTAC\",\"TCCGTA\",\"GTACGC\",\"ACTAAC\",\"GTATAC\",\"ACGTAA\"]}", };

		for (String dna : test) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(dna, headers);
			ResponseEntity<String> response = null;
			try {
				response = restTemplate().postForEntity("http://localhost:8080/mutant", entity, String.class);
				System.out.println(response.getStatusCode() + " -> mutant = " + dna);
				assertEquals(HttpStatus.OK, response.getStatusCode());
			} catch (HttpClientErrorException e) {
				System.out.println(e.getRawStatusCode() + " -> human = " + dna);
				assertEquals(HttpStatus.FORBIDDEN, HttpStatus.valueOf(e.getRawStatusCode()));
			}

		}
	}

	@Test
	public void testIsMutant() {
		Mutant mutant = new Mutant();
		mutant.setDna(getDnaSeqList(6));
		magnetoRestController.validarMutante(mutant);
	}

	@Test
	public void testgetStas() {
		magnetoRestController.obtenerEstadisticas();
	}

	@Test
	public void testObtenerEstadisticas() {
		ResponseEntity<String> response = restTemplate().getForEntity("http://localhost:8080/stats", String.class);
		System.out.println(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	private List<String> getDnaSeqList(int longitudSecuencia) {
		Random rnd = new Random();
		List<String> dna = new ArrayList<String>();
		for (int j = 1; j <= longitudSecuencia; j++) {
			String secuencia = "";
			for (int i = 1; i <= longitudSecuencia;) {
				char character = (char) (rnd.nextInt(26) + 65);
				if (("" + character).matches("[ATCG]{1}")) {
					secuencia += character;
					i++;
				}
			}

			dna.add(secuencia);
		}

		return dna;
	}

	/*private String getDnaSeq(int longitudSecuencia) {
		Random rnd = new Random();
		String dna = "{\"dna\":[\"";
		for (int j = 1; j <= longitudSecuencia; j++) {
			String secuencia = "";
			for (int i = 1; i <= longitudSecuencia;) {
				char character = (char) (rnd.nextInt(26) + 65);
				if (("" + character).matches("[ATCG]{1}")) {
					secuencia += character;
					i++;
				}
			}

			dna += secuencia;
			if (j <= longitudSecuencia - 1) {
				dna += "\",\"";
			} else {
				dna += "\"]}";
			}
		}

		return dna;
	}*/
}