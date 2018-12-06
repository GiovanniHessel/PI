package sp.com.senac.pi.servicos.compras;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.compras.CompradorDao;
import sp.com.senac.pi.model.dao.compras.FornecedorDao;
import sp.com.senac.pi.model.pojo.compras.Comprador;
import sp.com.senac.pi.model.pojo.compras.Fornecedor;

@Path("/comprador")
public class ServicoComprador {
	
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comprador insert(Comprador comprador) {
		
		return new CompradorDao().insert(comprador);
	}
	
	@PUT
	@Path("alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comprador update(Comprador comprador) {
		
		return new CompradorDao().update(comprador);
	}
	
	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor delete(Fornecedor fornecedor) {
		
		return new FornecedorDao().delete(fornecedor);
	}
	
	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comprador> getCompradores() {
		
		return new CompradorDao().getCompradores();
	}
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comprador getCompradorId(Comprador comprador) {
		
		return new CompradorDao().getComprador(comprador.getId());
	}
	
	@POST
	@Path("nome")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Comprador> getCompradorNome(Comprador comprador) {
		
		return new CompradorDao().getCompradorNome(comprador.getNome());
	}
	
	@PUT
	@Path("ativoInativo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comprador ativoInativo(Comprador comprador) {
		
		return new CompradorDao().ativoInativo(comprador);
	}
	

}
