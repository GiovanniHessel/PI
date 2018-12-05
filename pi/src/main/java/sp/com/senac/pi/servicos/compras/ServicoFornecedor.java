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

import sp.com.senac.pi.model.dao.compras.FornecedorDao;
import sp.com.senac.pi.model.pojo.compras.Fornecedor;

@Path("/fornecedor")
public class ServicoFornecedor {
	
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor insert(Fornecedor fornecedor) {
		
		return new FornecedorDao().insert(fornecedor);
	}
	
	@PUT
	@Path("alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor update(Fornecedor fornecedor) {
		
		return new FornecedorDao().update(fornecedor);
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
	public List<Fornecedor> getFornecedores() {
		
		return new FornecedorDao().getFornecedores();
	}
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor getCompraId(Fornecedor fornecedor) {
		
		return new FornecedorDao().getFornecedor(fornecedor.getId());
	}
	
	@POST
	@Path("nome")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Fornecedor> getFornecedorNome(Fornecedor fornecedor) {
		
		return new FornecedorDao().getFornecedorNome(fornecedor.getNomeFantasia());
	}
	
	@PUT
	@Path("ativoInativo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor ativoInativo(Fornecedor fornecedor) {
		
		return new FornecedorDao().ativoInativo(fornecedor);
	}
	

}
