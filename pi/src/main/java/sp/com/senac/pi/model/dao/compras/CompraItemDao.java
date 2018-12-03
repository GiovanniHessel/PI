package sp.com.senac.pi.model.dao.compras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.dao.producao.ProdutoDao;
import sp.com.senac.pi.model.pojo.compras.Compra;
import sp.com.senac.pi.model.pojo.compras.CompraItem;

public class CompraItemDao {
	private DbConnection connection;

	public CompraItemDao() {
		this.connection = ConnectionSingleton.getNewConnection();
	}
	
	public CompraItem insert(CompraItem compraItem) {
		compraItem.setId(0);
		
		return this.sendDb(compraItem);
	}
	
	public CompraItem update(CompraItem compraItem) {
		return this.sendDb(compraItem);
	}
	
	public CompraItem delete(CompraItem compraItem) {
   	 this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("exec Compras.SPD_COMPRAITEM ?");
            stmt.setInt(1, compraItem.getId());

            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	compraItem.setId(rs.getInt("id"));
            }

            rs.close();
            stmt.close();
            connection.close();
            return compraItem;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
   }
	
	public List<CompraItem> alterarCompraItem(Compra compra){
    	for (int i = 0; i < compra.getCompraItem().size(); i++) {
    		compra.getCompraItem().get(i).setIdCompra(compra.getId());
        }
    	
    	this.alterarCompraItem(this.getCompraItem(compra), compra.getCompraItem());
    	return this.getCompraItem(compra);
    }
    
    private void alterarCompraItem(List<CompraItem> compraItemAtuais, List<CompraItem> compraItemAlterados){
    	
    	//Excluindo e Alterando
		for (CompraItem compraItemAtual : compraItemAtuais) {
			int flag = 0;
			
			for (CompraItem compraItemAlterado : compraItemAlterados) {
				flag++;
				
				if (compraItemAtual.getId() == compraItemAlterado.getId()) {
					this.update(compraItemAlterado); 
					break;
				}
				
				if (flag == compraItemAlterados.size()) {
					this.delete(compraItemAtual);
				}	
			}
		}
		
		//Inserindo
		for (CompraItem compraItem : compraItemAlterados) {
			if (compraItem.getId() == 0) {
				this.insert(compraItem);
			}
		}
    }
	
	public CompraItem getCompraItem(int id) {
		this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Compras.CompraItem where id = ?");
            stmt.setInt(1, id);
            
            CompraItem compraItem = new CompraItem();
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	compraItem = this.carregaCompraItem(rs);
            }
            
            rs.close();
            stmt.close();
            connection.close();
            return compraItem;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
	}
	
	public List<CompraItem> getCompraItem(Compra compra) {
		this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Compras.CompraItem where idCompra = ?");
            stmt.setInt(1, compra.getId());
            
            ResultSet rs = stmt.executeQuery();
            List<CompraItem> compraItens = new ArrayList<CompraItem>();
            while (rs.next()) {
            	CompraItem compraItem = this.carregaCompraItem(rs);
            	compraItens.add(compraItem);
            }
            rs.close();
            stmt.close();
            connection.close();
            return compraItens;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
	}
	
	public List<CompraItem> getCompraItem() {
		this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Compras.CompraItem");

            ResultSet rs = stmt.executeQuery();
            List<CompraItem> compraItens = new ArrayList<CompraItem>();
            while (rs.next()) {
            	CompraItem compraItem = this.carregaCompraItem(rs);
            	compraItens.add(compraItem);
            }
            rs.close();
            stmt.close();
            connection.close();
            return compraItens;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
	}
	
	private CompraItem carregaCompraItem(ResultSet rs) {
		CompraItem compraItem = new CompraItem();
    	
        try {
	        
        	compraItem.setId(rs.getInt("id"));
        	compraItem.setIdCompra(rs.getInt("idCompra"));
        	compraItem.setProduto( new ProdutoDao().getProduto(rs.getInt("idProduto")));
        	compraItem.setQuantidadeItem(rs.getFloat("quantidadeItem"));
        	compraItem.setCustoItem(rs.getFloat("custoItem"));
        	compraItem.setTotalItem(rs.getFloat("totalItem"));

        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return compraItem;
    }
	
	public CompraItem sendDb(CompraItem compraItem) {
		String sql = "exec Compras.SPIU_COMPRAITEM ?,?,?,?,?";
		
		this.connection.open();
		try {
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, compraItem.getId());
			stmt.setInt(2, compraItem.getIdCompra());
			stmt.setInt(3, compraItem.getProduto().getId());
			stmt.setFloat(4, compraItem.getQuantidadeItem());
			stmt.setFloat(5, compraItem.getCustoItem());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				compraItem.setId(rs.getInt("id"));
				compraItem.setTotalItem(rs.getFloat("totalItem"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return compraItem;
		}
		connection.close();
		return compraItem;
	}
}
