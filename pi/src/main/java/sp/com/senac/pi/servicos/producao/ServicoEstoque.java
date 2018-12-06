package sp.com.senac.pi.servicos.producao;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.producao.EstoqueDao;
import sp.com.senac.pi.model.pojo.producao.Estoque;
import sp.com.senac.pi.model.pojo.producao.Produto;

@Path("/estoque")
public class ServicoEstoque {
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Estoque getEstoqueId(Estoque estoque) {
		
		return new EstoqueDao().getEstoque(estoque.getId());
	}
	
	@POST
	@Path("produto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Estoque> getEstoques(Estoque estoque) {
		
		return new EstoqueDao().getEstoques(estoque);
	}
	
	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estoque> getEstoques() {
		
		return new EstoqueDao().getEstoques();
	}
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Estoque insert(Estoque estoque) {
		
		return new EstoqueDao().insert(estoque);
	}
	
	@PUT
	@Path("alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Estoque update(Estoque estoque) {
		
		return new EstoqueDao().update(estoque);
	}
	
	@DELETE
	@Path("deletar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Estoque delete(Estoque estoque) {
		
		return new EstoqueDao().delete(estoque);
	}
	
}
