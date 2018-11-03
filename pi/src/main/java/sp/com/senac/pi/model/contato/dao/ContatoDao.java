package sp.com.senac.pi.model.contato.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.base.Empresa;
import sp.com.senac.pi.model.base.Pessoa;
import sp.com.senac.pi.model.contato.Contato;

public class ContatoDao {
	private DbConnection connection;

    public ContatoDao() {
        this.connection = ConnectionSingleton.getConnection();
    }
    
    public Contato insert(Contato contato) {
        String sql = "exec SPIU_CONTATO (?,?,?,?,?,?,?,?)";
        connection.open();
        try {

            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

            stmt.setInt(1, contato.getId());
            stmt.setString(2, contato.getDdd());
            stmt.setString(3, contato.getNumero());
            stmt.setString(4, contato.getEmail());
            stmt.setString(5, contato.getSite());
            stmt.setInt(6, contato.getIdPessoa());
            stmt.setInt(7, contato.getIdEmpresa());
            stmt.setInt(8, contato.getInativo());
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				contato.setId(rs.getInt("id"));
			}
			
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.close();
            return contato;
        }
        connection.close();
        return contato;
    }
    
    public List<Contato> insert(List<Contato> contatos) {
        String sql = "exec SPIU_CONTATO (?,?,?,?,?,?,?,?)";
        connection.open();
        try {
        	
        	for (Contato contato : contatos) {
        	
	            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	            
	            stmt.setInt(1, contato.getId());
	            stmt.setString(2, contato.getDdd());
	            stmt.setString(3, contato.getNumero());
	            stmt.setString(4, contato.getEmail());
	            stmt.setString(5, contato.getSite());
	            stmt.setInt(6, contato.getIdPessoa());
	            stmt.setInt(7, contato.getIdEmpresa());
	            stmt.setInt(8, contato.getInativo());
	            
	            stmt.execute();
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.close();
            return contatos;
        }
        connection.close();
        return contatos;
    }

 
    public Contato getContato(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Contato Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Contato contato = new Contato();
            
            if (rs.next()) {
            	contato = this.carregaContato(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return contato;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getContatos() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from contato");

            ResultSet rs = stmt.executeQuery();
            List<Contato> contatos = new ArrayList<Contato>();
            while (rs.next()) {
            	Contato contato = this.carregaContato(rs);
                contatos.add(contato);
            }
            rs.close();
            stmt.close();
            connection.close();
            return contatos;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getContatos(Pessoa pessoa) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from contato where idPessoa = ?");
            stmt.setInt(1, pessoa.getId());
            
            ResultSet rs = stmt.executeQuery();
            List<Contato> contatos = new ArrayList<Contato>();
            while (rs.next()) {
            	Contato contato = this.carregaContato(rs);
                contatos.add(contato);
            }
            rs.close();
            stmt.close();
            connection.close();
            return contatos;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getContatos(Empresa empresa) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from contato where idEmpresa = ?");
            stmt.setInt(1, empresa.getId());
            
            ResultSet rs = stmt.executeQuery();
            List<Contato> contatos = new ArrayList<Contato>();
            while (rs.next()) {
            	Contato contato = this.carregaContato(rs);
                contatos.add(contato);
            }
            
            rs.close();
            stmt.close();
            connection.close();
            return contatos;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    private Contato carregaContato(ResultSet rs) {
    	Contato contato = new Contato();
    	
        try {
	        
	        contato.setId(rs.getInt("id"));
	        contato.setDdd(rs.getString("ddd"));
	        contato.setNumero(rs.getString("numero"));
	        contato.setEmail(rs.getString("email"));
	        contato.setSite(rs.getString("site"));
	        contato.setIdPessoa(rs.getInt("idPessoa"));
	        contato.setIdEmpresa(rs.getInt("idEmpresa"));
	        contato.setInativo(rs.getInt("inativo"));
        
        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return contato;
        		
    }
}
