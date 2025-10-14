package br.com.rhscdeveloper.endpoint;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.rhscdeveloper.dto.VeiculoDTO;
import br.com.rhscdeveloper.service.VeiculoService;
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
@Path("/api/veiculo")
public class VeiculoResource {

	@Inject
	private VeiculoService service;
	
	@POST
	@Path("/cadastrar")
	public Response cadastrarVeiculo(@RequestBody VeiculoDTO veiculo) {
		return Response.status(Status.CREATED).entity(service.cadastrarVeiculo(veiculo)).build();
	}

	@GET
	@Path("/{id}")
	public Response obterVeiculo(@PathParam("id") Integer id) {
		return Response.status(Status.OK).entity(service.obterVeiculoById(id)).build();
	}

	@GET
	@Path("/veiculos")
	public Response obterVeiculos() {
		return Response.status(Status.OK).entity(service.obterVeiculos()).build();
	}

	@GET
	public Response obterVeiculos(VeiculoDTO filtro) {
		return Response.status(Status.OK).entity(service.obterVeiculosFiltro(filtro)).build();
	}

	@PUT
	@Path("/atualizar")
	public Response atualizarVeiculo(@RequestBody VeiculoDTO filtro) {
		return Response.status(Status.OK).entity(service.atualizarVeiculo(filtro)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletarVeiculo(@PathParam("id") Integer id) {
		return Response.status(Status.OK).entity(service.deletarVeiculo(id)).build();
	}
}
