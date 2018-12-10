package sp.com.senac.pi.model.dao.compras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.dao.base.PessoaDao;
import sp.com.senac.pi.model.dao.contato.ContatoDao;
import sp.com.senac.pi.model.pojo.base.Pessoa;
import sp.com.senac.pi.model.pojo.compras.Comprador;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;
import sp.com.senac.pi.model.pojo.localizacao.Estado;
import sp.com.senac.pi.model.pojo.localizacao.Pais;
import sp.com.senac.pi.util.control.Util;

public class CompradorDao {
	private DbConnection connection;

	public CompradorDao() {
		this.connection = ConnectionSingleton.getNewConnection();
	}
	
	public Comprador insert(Comprador comprador) {
		comprador.setId(0);
		
		if (comprador.getIdPessoa() == 0) {
			Pessoa pessoa = new PessoaDao().insert(comprador);
			comprador.setIdPessoa(pessoa.getIdPessoa());
		}
		
		if (comprador.getIdPessoa() == 0) {
			return comprador;
		}
		
		return this.sendDB(comprador);
	}
	
	public Comprador update(Comprador comprador) {
		
		Pessoa pessoa = new  PessoaDao().update(comprador);
		comprador.setIdPessoa(pessoa.getIdPessoa());
		
		if (comprador.getIdPessoa() == 0) {
			comprador.setId(0);
			return comprador;
		}
		
		return this.sendDB(comprador);
	}
	
	public Comprador delete(Comprador comprador) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("Compras.SPD_COMPRADOR ?");
			stmt.setInt(1, comprador.getId());

			ResultSet rs = stmt.executeQuery();
	
			if (rs.next()) {
				comprador.setId(rs.getInt("id"));
			}

			rs.close();
			stmt.close();
			connection.close();
			return comprador;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public Comprador ativoInativo(Comprador comprador) {

		this.connection.open();
		try {
	
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("update Compras.Comprador set inativo = ? OUTPUT DELETED.id  where id = ?");
			stmt.setInt(1, comprador.getInativo());
			stmt.setInt(2, comprador.getId());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				comprador.setId(rs.getInt("id"));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return comprador;
		}
		connection.close();
		
		return comprador;
	}
	
	public Comprador getComprador(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from Compras.vwComprador where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Comprador comprador = new Comprador();
	
			if (rs.next()) {
				comprador = this.carregaComprador(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return comprador;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public List<Comprador> getCompradorNome(String nome) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Compras.vwComprador where nome like '" + nome +"'");

			ResultSet rs = stmt.executeQuery();
			List<Comprador> compradores = new ArrayList<Comprador>();
		
			if (rs.next()) {
				compradores.add(this.carregaComprador(rs));
				
			}

			rs.close();
			stmt.close();
			connection.close();
			return compradores;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public Comprador getCompradorCpf(String cpf) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Compras.vwComprador where cpf = ?");
			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery();
			Comprador comprador = new Comprador();
		
			if (rs.next()) {
				comprador = this.carregaComprador(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return comprador;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public List<Comprador> getCompradores() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Compras.vwComprador");
			List<Comprador> compradores = new ArrayList<Comprador>();
			ResultSet rs = stmt.executeQuery();
			
		
			while (rs.next()) {
				
				Comprador comprador = this.carregaComprador(rs);
				compradores.add(comprador);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compradores;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	private Comprador carregaComprador(ResultSet rs) {
		Comprador comprador = new Comprador();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
    	Pais pais = new Pais();
    	
		try {
			comprador.setId(rs.getInt("id"));
			comprador.setLimiteDeCredito(rs.getFloat("limiteDeCredito"));
			comprador.setInativo(rs.getInt("inativo"));
			
			comprador.setIdPessoa(rs.getInt("idPessoa"));
			comprador.setNome(rs.getString("nome"));
			comprador.setSobrenome(rs.getString("sobreNome"));
			comprador.setCPF(rs.getString("CPF"));
			comprador.setDataDeNascimento(new Util().getStringDate(rs.getTimestamp("dataDeNascimento")));
			
			comprador.setSexo(rs.getString("sexo"));
			
			comprador.setCep(rs.getString("cep"));
			comprador.setLogradouro(rs.getString("logradouro"));
			comprador.setNumero(rs.getString("numero"));
			comprador.setComplemento(rs.getString("complemento"));
			comprador.setBairro(rs.getString("bairro"));
			comprador.setContatos(new ContatoDao().getContatos(comprador));
			
			cidade.setId(rs.getInt("idCidade"));
			cidade.setCidade(rs.getString("cidade"));
            cidade.setSigla(rs.getString("siglaCidade"));
            
			estado.setId(rs.getInt("idEstado"));
	        estado.setEstado(rs.getString("estado"));
	        estado.setSigla(rs.getString("siglaEstado"));
	        
	        pais.setId(rs.getInt("idPais"));
	        pais.setPais(rs.getString("pais"));
	        pais.setSigla(rs.getString("siglaPais"));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		estado.setPais(pais);
        cidade.setEstado(estado);
        comprador.setCidade(cidade);

        return comprador;
	}
	
	private Comprador sendDB(Comprador comprador)  {
		
		String sql = "exec Compras.SPIU_COMPRADOR ?,?,?,?";
		
		this.connection.open();
		try {
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, comprador.getId());
			stmt.setFloat(2, comprador.getLimiteDeCredito());
			stmt.setInt(3, comprador.getIdPessoa());
			stmt.setInt(4, comprador.getInativo());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				comprador.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return comprador;
		}
		connection.close();
		return comprador;
	
	}
}
