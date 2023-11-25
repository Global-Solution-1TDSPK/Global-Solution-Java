package br.com.fiap.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.LoginBo;
import br.com.fiap.entity.Login;

@Path("/login")
public class LoginResource {

    private LoginBo loginBo;

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Login login, @Context UriInfo uriInfo) {
    	System.out.println(System.getProperty("catalina.base"));
        loginBo = new LoginBo();
        loginBo.cadastrar(login);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        return Response.created(builder.build()).build();
    }

    // Verificar Usuário
    @GET
    @Path("/verificarLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarLogin(@QueryParam("senha") String senha,
                                   @QueryParam("email") String email) {
        loginBo = new LoginBo();
        boolean loginExiste = loginBo.verificarLogin(senha, email);

        System.out.println("Verificando login");

        if (loginExiste) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    // Verificar Administrador
    @GET
    @Path("/verificarLoginAdm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarLoginAdm(@QueryParam("usuario") String usuario,
                                      @QueryParam("senha") String senha) {
        loginBo = new LoginBo();
        boolean loginAdmExiste = loginBo.verificarLoginAdm(usuario, senha);

        System.out.println("Verificando login de administrador");

        if (loginAdmExiste) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}

