package sp.com.senac.pi.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.cadastro.Pais;

public class PaisDao {
	private DbConnection connection;

    public PaisDao() {
        this.connection = ConnectionSingleton.getConnection();
    }
    
    public boolean insert(Pais pais) {
        String sql = "exec spi_pais (?,?,?,?)";
        connection.open();
        try {

            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

            stmt.setInt(1, pais.getId());
            stmt.setString(2, pais.getPais());
            stmt.setString(3, pais.getSigla());
            stmt.setInt(4, pais.getInativo());
   
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

 
    public Pais getPais(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Pais Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Pais pais = new Pais();
            
            if (rs.next()) {
            	pais = this.carregaPais(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return pais;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Pais> getPaises() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from pais");

            ResultSet rs = stmt.executeQuery();
            List<Pais> paises = new ArrayList<Pais>();
            while (rs.next()) {
            	Pais pais = this.carregaPais(rs);
                paises.add(pais);
            }
            rs.close();
            stmt.close();
            connection.close();
            return paises;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    private Pais carregaPais(ResultSet rs) {
    	Pais pais = new Pais();
    	
        try {
	        
	        pais.setId(rs.getInt("id"));
	        pais.setPais(rs.getString("pais"));
	        pais.setSigla(rs.getString("siglaPais"));
	        pais.setInativo(rs.getInt("inativo"));
        
        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return pais;
        		
    }
}
