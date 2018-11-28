package sp.com.senac.pi.servicos.base;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.localizacao.CidadeDao;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;

@Path("/cidade")
public class ServicoCidade {


	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> getCidade() {
		
		return new CidadeDao().getCidades();
	}
	
	
	/*
	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> getApenasCidade() {
		
		return new CidadeDao().getApenasCidades();
	}
	*/
}
