package br.com.estoque.controller;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.com.estoque.fornecedor.dao.FornecedorDAO;
import br.com.estoque.fornecedor.model.Fornecedor;

public class EstoqueFornecedorController {
	static String[] colunas = { "ID", "Nome", "CNPJ" };
	static DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	static JTable tabela = new JTable(modelo);
	public static JScrollPane scroll = new JScrollPane(tabela);


	public void atualizarTable() throws SQLException {
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> lista = dao.getFornecedores();
		modelo.setRowCount(0);

		for (Fornecedor f : lista) {
			Object[] row = { f.getId(), f.getNome(), f.getCnpj()};
			modelo.addRow(row);
		}
	}
	
	public void gerenciarFornecedor() {
		// criação da janela secundária de cadastro de produtos
				JFrame frameCadastroFornecedor = new JFrame();
				frameCadastroFornecedor.setSize(700, 500);
				frameCadastroFornecedor.setTitle("Cadastro de produto");
				frameCadastroFornecedor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				// ----------- Painel dos botões (direita) -----------
				JPanel painelBotoes = new JPanel();
				painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
				painelBotoes.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20)); // padding

				JButton btnCadastrar = new JButton("Cadastrar");
				JButton btnAtualizar = new JButton("Atualizar");
				JButton btnExcluir = new JButton("Excluir");
				JButton btnBuscar = new JButton("Buscar");

				painelBotoes.add(btnCadastrar);
				painelBotoes.add(Box.createVerticalStrut(20));
				painelBotoes.add(btnAtualizar);
				painelBotoes.add(Box.createVerticalStrut(20));
				painelBotoes.add(btnExcluir);
				painelBotoes.add(Box.createVerticalStrut(20));
				painelBotoes.add(btnBuscar);

				Dimension tamanhoButtons = new Dimension(190, 60);
				btnCadastrar.setPreferredSize(tamanhoButtons);
				btnAtualizar.setPreferredSize(tamanhoButtons);
				btnExcluir.setPreferredSize(tamanhoButtons);
				btnBuscar.setPreferredSize(tamanhoButtons);

				// ----------- Divisão com JSplitPane -----------
				JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, painelBotoes);
				splitPane.setDividerLocation(550);
				splitPane.setEnabled(false);
				splitPane.setDividerSize(2);

				try {
					atualizarTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				// ----------- Funcionamento dos botões CRUD de Produtos -----------
				
				// ----------- Finalização dos Funcionamentos dos botões CRUD -----------
				
				frameCadastroFornecedor.add(splitPane);
				frameCadastroFornecedor.setVisible(true);
	}
}
