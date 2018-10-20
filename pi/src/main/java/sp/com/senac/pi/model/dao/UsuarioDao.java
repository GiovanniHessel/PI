package sp.com.senac.pi.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
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
					.prepareStatement("select id, login, chave, u.idPessoa, inativo " + 
										" from Usuarios " + 
										" where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
	
			// criando o objeto Contato
			if (rs.next()) {

				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setChave(rs.getString("chave"));
				usuario.getPessoa().setId(rs.getInt("idPessoa"));
				usuario.setInativo(rs.getInt("inativo"));
				// adicionando o objeto à lista
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
			PreparedStatement stmt = this.connection.getConnection().prepareStatement(
					"select id, login, chave, inativo, idPessoa from Usuarios where login = ? and chave = ?");
			stmt.setString(1, login);
			stmt.setString(2, chave);

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			// criando o objeto Contato
			if (rs.next()) {

				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setChave(rs.getString("chave"));
				usuario.setInativo(rs.getInt("inativo"));
				usuario.getPessoa().setId(rs.getInt("idPessoa"));
				// adicionando o objeto à lista
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
}
