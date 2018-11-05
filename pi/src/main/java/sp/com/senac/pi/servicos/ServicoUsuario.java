package sp.com.senac.pi.servicos;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.base.Usuario;
import sp.com.senac.pi.model.base.dao.UsuarioDao;


@Path("/usuario")
public class ServicoUsuario {
	
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(Usuario usuario) {
		
		return new UsuarioDao().getUsuario(usuario.getId());
	}
	

	@POST
	@Path("signin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Usuario validar(@FormParam("login") String login, @FormParam("chave") String chave) {
		
		return new UsuarioDao().getUsuario(login, chave);
	}
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario inserir(Usuario usuario) {
		
		return new UsuarioDao().insert(usuario);
	}
	
	@PUT
	@Path("alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario alterar(Usuario usuario) {
		
		return new UsuarioDao().update(usuario);
	}
	
	@DELETE
	@Path("deletar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario deletar(Usuario usuario) {
		
		return new UsuarioDao().delete(usuario);
	}
	
}
