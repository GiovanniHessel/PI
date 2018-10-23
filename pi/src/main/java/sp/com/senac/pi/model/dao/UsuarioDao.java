package sp.com.senac.pi.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.cadastro.Cidade;
import sp.com.senac.pi.model.cadastro.Estado;
import sp.com.senac.pi.model.cadastro.Pais;
import sp.com.senac.pi.model.cadastro.Pessoa;
import sp.com.senac.pi.model.cadastro.Usuario;

public class UsuarioDao {
	private DbConnection connection;

	public UsuarioDao() {
		this.connection = ConnectionSingleton.getConnection();
	}

	public Usuario insert(Usuario usuario) {

		usuario.setId(0);

		String sql = "insert into Usuarios" 
				+ "(login, chave, inativo, idPessoa)" 
				+ "Output Inserted.id as id"
				+ " values (?,?,?,?)";
		
		this.connection.open();
		try {
			// prepared statement para inserção

			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

			// seta os valores
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getChave());
			stmt.setInt(3, usuario.getInativo());
			stmt.setInt(4, usuario.getPessoa().getId());
			// executa
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

	public Usuario getUsuario(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwUsuario where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
	
			if (rs.next()) {
				this.carregaUsuario(rs);
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
			stmt.setString(2, chave);

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			// criando o objeto Contato
			if (rs.next()) {
				this.carregaUsuario(rs);
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
        cidade.setEstados(estado);
        pessoa.setCidade(cidade);
        usuario.setPessoa(pessoa);
        
        return usuario;
	}
}
