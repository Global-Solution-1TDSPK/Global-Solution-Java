package br.com.fiap.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


import br.com.fiap.bo.TriagemBo;
import br.com.fiap.entity.Triagem;

@Path("/triagem")
public class TriagemResource {

    private TriagemBo triagemBo;

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Triagem triagem, @Context UriInfo uriInfo) {
        triagemBo = new TriagemBo();
        triagemBo.cadastrar(triagem);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        return Response.created(builder.build()).build();
    }

    // Verificar Usuário
    @GET
    @Path("/buscarTriagem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarTriagem() {
        TriagemBo triagemBo = new TriagemBo();
        List<Triagem> triagens = triagemBo.verificarTriagem();

        return Response.ok(triagens).build();
    }
}
