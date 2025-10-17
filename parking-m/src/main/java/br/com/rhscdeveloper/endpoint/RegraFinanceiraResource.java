package br.com.rhscdeveloper.endpoint;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.rhscdeveloper.dto.RegraFinanceiraDTO;
import br.com.rhscdeveloper.service.RegraFinanceiraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/regra-financeira")
public class RegraFinanceiraResource {

	@Inject
	private RegraFinanceiraService service;
	
	@POST
	@Path("/cadastrar")
	public Response cadastrarRegraFinanceira(@RequestBody RegraFinanceiraDTO RegraFinanceira) {
		return Response.status(Status.CREATED).entity(service.cadastrarRegraFinanceira(RegraFinanceira)).build();
	}

	@GET
	@Path("/{id}")
	public Response obterRegraFinanceira(@PathParam("id") Integer id) {
		return Response.status(Status.OK).entity(service.obterRegraFinanceiraById(id)).build();
	}

	@GET
	@Path("/regras")
	public Response obterRegraFinanceiras() {
		return Response.status(Status.OK).entity(service.obterRegrasFinanceiras()).build();
	}

	@GET
	public Response obterRegraFinanceiras(RegraFinanceiraDTO filtro) {
		return Response.status(Status.OK).entity(service.obterRegraFinanceiraFiltro(filtro)).build();
	}

	@PUT
	@Path("/atualizar")
	public Response atualizarRegraFinanceira(@RequestBody RegraFinanceiraDTO filtro) {
		return Response.status(Status.OK).entity(service.atualizarRegraFinanceira(filtro)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletarRegraFinanceira(@PathParam("id") Integer id) {
		return Response.status(Status.OK).entity(service.deletarRegraFinanceira(id)).build();
	}
}
