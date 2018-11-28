package sp.com.senac.pi.servicos.base;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.base.PessoaDao;
import sp.com.senac.pi.model.pojo.base.Pessoa;


@Path("/pessoa")
public class ServicoPessoa {
	
	@POST
	@Path("cpf")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Pessoa getPessoa(Pessoa pessoa) {
		
		return new PessoaDao().getPessoa(pessoa.getCpf());
	}
}
