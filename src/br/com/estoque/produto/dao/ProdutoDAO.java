package br.com.estoque.produto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estoque.connection.ConnectionFactory;
import br.com.estoque.produto.model.Produto;

public class ProdutoDAO {
	public void CadastrarProduto(Produto produto) throws SQLException {
		String sql = "INSERT INTO produto(nome, quantidade, preco, id_fornecedor) VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, produto.getNome());
			pstm.setInt(2, produto.getQuantidade());
			pstm.setString(3, produto.getPreco());
			pstm.setInt(4, produto.getId_fornecedor());
			
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.close();
			}
			if(pstm != null) {
				pstm.close();
			}
		}
	}

	public List<Produto> getProdutos() throws SQLException {
		String sql = "SELECT * FROM produto";
		List<Produto> produtos = new ArrayList<Produto>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			while(rset.next()) {
				Produto p = new Produto();
				p.setId(rset.getInt("id"));
				p.setNome(rset.getString("nome"));
				p.setQuantidade(rset.getInt("quantidade"));
				p.setPreco(rset.getString("preco"));
				p.setId_fornecedor(rset.getInt("id_fornecedor"));
				
				produtos.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.close();
			}
			if(pstm != null) {
				pstm.close();
			}
			if(rset != null) {
				rset.close();
			}
		}
		return produtos;
	}

	public void atualizarProduto(Produto produto) throws SQLException {
		String sql = "UPDATE produto SET nome = ?, quantidade = ?, preco = ?, id_fornecedor = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null; 
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, produto.getNome());
			pstm.setInt(2, produto.getQuantidade());
			pstm.setString(3, produto.getPreco());
			pstm.setInt(4, produto.getId_fornecedor());
			
			pstm.setInt(5, produto.getId());
			
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.close();
			}
			if(pstm != null) {
				pstm.close();
			}
		}
	}

	public void deletarProduto(int id) throws SQLException {
		String sql = "DELETE FROM produto WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.close();
			}
			if(pstm != null) {
				pstm.close();
			}
		}
	}
}
