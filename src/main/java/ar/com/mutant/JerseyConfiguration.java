package ar.com.mutant;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import ar.com.mutant.controller.MagnetoRestController;

/**
 * @author Carlos
 *
 */
@Configuration
@ApplicationPath("/")
public class JerseyConfiguration extends ResourceConfig {
	public JerseyConfiguration() {

	}

	@PostConstruct
	public void setUp() {
		register(MagnetoRestController.class);
	}
}