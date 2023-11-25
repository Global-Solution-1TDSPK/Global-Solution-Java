package br.com.fiap.resource;

import br.com.fiap.bo.DadosBo;
import br.com.fiap.entity.Dados;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/dadosFormulario")
public class DadosResource {

    private DadosBo dadosBo;

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Dados dado, @Context UriInfo uriInfo) {
        dadosBo = new DadosBo();
        dadosBo.cadastrar(dado);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        return Response.created(builder.build()).build();
    }

    // Atualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Dados novosDados) {
        dadosBo = new DadosBo();
        dadosBo.alterar(novosDados, id);
        return Response.ok().build();
    }

    // Buscar Dados
    @GET
    @Path("/buscarDados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarDados() {
        DadosBo dadosBo = new DadosBo();
        List<Dados> novoDados = dadosBo.verificarDados();
        return Response.ok(novoDados).build();
    }
}

