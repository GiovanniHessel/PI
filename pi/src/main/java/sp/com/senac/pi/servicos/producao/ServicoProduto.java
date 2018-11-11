package sp.com.senac.pi.servicos.producao;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.producao.ProdutoDao;
import sp.com.senac.pi.model.pojo.producao.Produto;

@Path("/produto")
public class ServicoProduto {
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Produto getProduto(Produto produto) {
		
		return new ProdutoDao().getProduto(produto.getId());
	}
	
	@GET
	@Path("listaAtual")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getProdutosAtuais() {
		
		return new ProdutoDao().getProdutosAtuais();
	}
	
	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getProdutos() {
		
		return new ProdutoDao().getProdutos();
	}
}
