package ar.com.mutant.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.mutant.dto.Mutant;
import ar.com.mutant.dto.Stats;
import ar.com.mutant.service.MutantService;

/**
 * @author Carlos
 *
 */
@Component
@Path("/")
public class MagnetoRestController {

	@Autowired
	private MutantService mutantService;
	
	@POST
	@Path("/mutant")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validarMutante(Mutant mutant) {
		if (mutantService.isMutant(mutant.getDna().toArray(new String[0]))) {
			return Response.status(Response.Status.OK).build();	
		}
		return Response.status(Response.Status.FORBIDDEN).entity("").build();
	}

	@GET
	@Path("/stats")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerEstadisticas() {
		Stats stats = mutantService.getStas();
		return Response.status(Response.Status.OK).entity(stats).build();
	}

}