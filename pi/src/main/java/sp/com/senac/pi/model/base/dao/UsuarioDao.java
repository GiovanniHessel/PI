package sp.com.senac.pi.model.base.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.base.Pessoa;
import sp.com.senac.pi.model.base.Usuario;
import sp.com.senac.pi.model.contato.dao.ContatoDao;
import sp.com.senac.pi.model.localizacao.Cidade;
import sp.com.senac.pi.model.localizacao.Estado;
import sp.com.senac.pi.model.localizacao.Pais;
import sp.com.senac.pi.seguranca.control.Seguranca;

public class UsuarioDao {
	private DbConnection connection;

	public UsuarioDao() {
		this.connection = ConnectionSingleton.getConnection();
	}

	public Usuario insert(Usuario usuario) {

		usuario.setId(0);
		
		usuario.setPessoa(new PessoaDao().insert(usuario.getPessoa()));
		
		this.connection.open();
		try {

			PreparedStatement stmt = this.carregaParametros(usuario);
		
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return usuario;
		}
		connection.close();
		return usuario;
	}
	
	public Usuario update(Usuario usuario) {
		
		usuario.setPessoa(new PessoaDao().update(usuario.getPessoa()));
		
		this.connection.open();
		try {

			PreparedStatement stmt = this.carregaParametros(usuario);
		
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return usuario;
		}
		connection.close();
		return usuario;
	}
	
	public Usuario delete(Usuario usuario) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("SPD_USUARIO ?");
			stmt.setInt(1, usuario.getId());

			ResultSet rs = stmt.executeQuery();
	
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
			}

			rs.close();
			stmt.close();
			connection.close();
			return usuario;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public Usuario getUsuario(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwUsuario where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
	
			if (rs.next()) {
				usuario = this.carregaUsuario(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return usuario;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public Usuario getUsuario(String login, String chave) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwUsuario where login = ? and chave = ?");
			stmt.setString(1, login);
			stmt.setString(2, new Seguranca().hash(chave));

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
		
			if (rs.next()) {
				usuario = this.carregaUsuario(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return usuario;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	private Usuario carregaUsuario(ResultSet rs) {
		Usuario usuario = new Usuario();
		Pessoa pessoa = new Pessoa();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
    	Pais pais = new Pais();
    	
		try {
			usuario.setId(rs.getInt("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setChave(rs.getString("chave"));
			usuario.setInativo(rs.getInt("inativo"));
			
			pessoa.setId(rs.getInt("idPessoa"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setSobrenome(rs.getString("sobreNome"));
			pessoa.setCPF(rs.getString("CPF"));
			pessoa.setDataDeNascimento(rs.getString("dataDeNascimento"));
			pessoa.setSexo(rs.getString("sexo"));
			pessoa.setLogradouro(rs.getString("logradouro"));
			pessoa.setNumero(rs.getString("numero"));
			pessoa.setComplemento(rs.getString("complemento"));
			pessoa.setBairro(rs.getString("bairro"));
			pessoa.setContatos(new ContatoDao().getContatos(pessoa));
			
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
        pessoa.setCidade(cidade);
        usuario.setPessoa(pessoa);
        
        return usuario;
	}
	
	private PreparedStatement carregaParametros(Usuario usuario) throws SQLException {
		String sql = "exec SPIU_USUARIO ?,?,?,?,?";
		
		PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
		
		stmt.setInt(1, usuario.getId());
		stmt.setInt(2, usuario.getPessoa().getId());
		stmt.setString(3, usuario.getLogin());
		stmt.setString(4, new Seguranca().hash(usuario.getChave()));
		stmt.setInt(5, usuario.getInativo());
		
		
		return stmt;
	}
}
