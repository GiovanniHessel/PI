package sp.com.senac.pi.servicos.compras;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.compras.CompraDao;
import sp.com.senac.pi.model.pojo.compras.Compra;
import sp.com.senac.pi.model.pojo.compras.Comprador;
import sp.com.senac.pi.model.pojo.compras.Fornecedor;

@Path("/compra")
public class ServicoCompra {
	
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Compra insert(Compra compra) {
		
		return new CompraDao().insert(compra);
	}
	
	@PUT
	@Path("alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Compra update(Compra compra) {
		
		return new CompraDao().update(compra);
	}
	
	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compra> getCompras() {
		
		return new CompraDao().getCompras();
	}
	
	@GET
	@Path("recebidas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compra> getComprasRecebidas() {
		
		return new CompraDao().getComprasRecebidas();
	}
	
	@GET
	@Path("naorecebidas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compra> getComprasNaoRecebidas() {
		
		return new CompraDao().getComprasNaoRecebidas();
	}
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Compra getCompraId(Compra compra) {
		
		return new CompraDao().getCompra(compra.getId());
	}
	
	@POST
	@Path("fornecedor")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Compra> getCompras(Fornecedor fornecedor) {
		
		return new CompraDao().getCompras(fornecedor);
	}
	
	@POST
	@Path("comprador")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Compra> getCompras(Comprador comprador) {
		
		return new CompraDao().getCompras(comprador);
	}
	
	

}
