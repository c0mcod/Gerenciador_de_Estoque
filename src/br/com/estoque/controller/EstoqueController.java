package br.com.estoque.controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

public class EstoqueController {
	static String[] colunas = { "ID", "Nome", "Preço", "Quantidade", "ID Fornecedor" };
	static DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	static JTable tabela = new JTable(modelo);
	public static JScrollPane scroll = new JScrollPane(tabela);

	public void atualizarTable() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.getProdutos();
		modelo.setRowCount(0);

		for (Produto p : lista) {
			Object[] row = { p.getId(), p.getNome(), p.getPreco(), p.getQuantidade(), p.getId_fornecedor() };
			modelo.addRow(row);
		}
	}
}
