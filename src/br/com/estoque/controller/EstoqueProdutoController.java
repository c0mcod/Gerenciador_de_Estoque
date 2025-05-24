package br.com.estoque.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

public class EstoqueProdutoController {
	static String[] colunas = { "ID", "Nome", "Preço", "Quantidade", "ID Fornecedor" };
	static DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	static JTable tabela = new JTable(modelo);
	public static JScrollPane scroll = new JScrollPane(tabela);
	ProdutoDAO daoP = new ProdutoDAO();
	Produto p = new Produto();
	Produto pA = new Produto();

	public void atualizarTable() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.getProdutos();
		modelo.setRowCount(0);

		for (Produto p : lista) {
			Object[] row = { p.getId(), p.getNome(), p.getPreco(), p.getQuantidade(), p.getId_fornecedor() };
			modelo.addRow(row);
		}
	}

	public void gerenciarProduto() {
		// criação da janela secundária de cadastro de produtos
		JFrame frameCadastroProduto = new JFrame();
		frameCadastroProduto.setSize(700, 500);
		frameCadastroProduto.setTitle("Cadastro de produto");
		frameCadastroProduto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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

		// BOTÃO CADASTRAR
		btnCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame btnC = new JFrame();
				btnC.setSize(500, 300);
				btnC.setTitle("Cadastrar Produto");
				btnC.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				btnC.setLocationRelativeTo(null);

				JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
				painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

				// Configurando campos e labels
				JTextField campoNome = new JTextField(10);
				JTextField campoPreco = new JTextField(10);
				JTextField campoQuantidade = new JTextField(10);

				JLabel labelIdFornecedor = new JLabel("ID Fornecedor");
				JTextField campoIdFornecedor = new JTextField(10);

				JPanel painelCampos = new JPanel(new GridLayout(4, 3, 5, 5));
				painelCampos.add(new JLabel("Nome:"));
				painelCampos.add(campoNome);
				painelCampos.add(new JLabel("Preço:"));
				painelCampos.add(campoPreco);
				painelCampos.add(new JLabel("Quantidade:"));
				painelCampos.add(campoQuantidade);
				painelCampos.add(labelIdFornecedor);
				painelCampos.add(campoIdFornecedor);
				// Painel para os botões
				JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JButton enviarProduto = new JButton("Enviar");
				JButton cancelarProduto = new JButton("Cancelar");

				cancelarProduto.addActionListener(_ -> btnC.dispose());

				painelBotoes.add(cancelarProduto);
				painelBotoes.add(enviarProduto);

				enviarProduto.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String nomeBD = campoNome.getText();
						String precoBD = campoPreco.getText();
						String quantidadeBD = campoQuantidade.getText();
						int quantidadeBDInt = Integer.parseInt(quantidadeBD);
						String idFornecedor = campoIdFornecedor.getText();
						int idFornecedorInt = Integer.parseInt(idFornecedor);

						p.setNome(nomeBD);
						p.setPreco(precoBD);
						p.setQuantidade(quantidadeBDInt);
						p.setId_fornecedor(idFornecedorInt);

						try {
							daoP.CadastrarProduto(p);
							atualizarTable();
							btnC.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnC, "Erro ao cadastrar: " + e1.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						JOptionPane.showMessageDialog(null, "Produto Cadastrada!");
					}
				});

				// Montando a janela
				painelPrincipal.add(painelCampos, BorderLayout.CENTER);
				painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

				btnC.add(painelPrincipal);

				btnC.setVisible(true);

			}
		});

		// BOTÃO ATUALIZAR
		btnAtualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame btnA = new JFrame();
				btnA.setTitle("Atualizar produto");
				btnA.setSize(500, 300);
				btnA.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				btnA.setLocationRelativeTo(null);

				JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
				painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

				// Configurando campos e labels
				JTextField campoNome = new JTextField(10);
				JTextField campoPreco = new JTextField(10);
				JTextField campoQuantidade = new JTextField(10);
				JTextField campoId = new JTextField(10);

				JLabel labelIdFornecedor = new JLabel("ID Fornecedor");
				JTextField campoIdFornecedor = new JTextField(10);

				JPanel painelCampos = new JPanel(new GridLayout(5, 4, 5, 5));
				painelCampos.add(new JLabel("Nome:"));
				painelCampos.add(campoNome);
				painelCampos.add(new JLabel("Preço:"));
				painelCampos.add(campoPreco);
				painelCampos.add(new JLabel("Quantidade:"));
				painelCampos.add(campoQuantidade);
				painelCampos.add(labelIdFornecedor);
				painelCampos.add(campoIdFornecedor);
				painelCampos.add(new JLabel("ID do Produto"));
				painelCampos.add(campoId);

				// Painel para os botões
				JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JButton atualizarProduto = new JButton("Atualizar");
				JButton cancelarProduto = new JButton("Cancelar");

				cancelarProduto.addActionListener(_ -> btnA.dispose());

				painelBotoes.add(cancelarProduto);
				painelBotoes.add(atualizarProduto);

				atualizarProduto.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String nomeBD = campoNome.getText();
						String precoBD = campoPreco.getText();
						String quantidadeBD = campoQuantidade.getText();
						int quantidadeBDInt = Integer.parseInt(quantidadeBD);
						String idFornecedor = campoIdFornecedor.getText();
						int idFornecedorInt = Integer.parseInt(idFornecedor);
						String idbd = campoId.getText();
						int idBdInt = Integer.parseInt(idbd);

						pA.setNome(nomeBD);
						pA.setPreco(precoBD);
						pA.setQuantidade(quantidadeBDInt);
						pA.setId_fornecedor(idFornecedorInt);

						pA.setId(idBdInt);
						try {
							daoP.atualizarProduto(pA);
							atualizarTable();
							btnA.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnA, "Erro ao atualizar: " + e1.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						JOptionPane.showMessageDialog(null, "Produto Atualizado!");
					}
				});

				// Montando a janela
				painelPrincipal.add(painelCampos, BorderLayout.CENTER);
				painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
				btnA.add(painelPrincipal);

				btnA.setVisible(true);

			}
		});
		

		// BOTÃO DELETAR
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame btnE = new JFrame();
				btnE.setTitle("Excluir produto");
				btnE.setSize(400, 200);
				btnE.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				btnE.setLocationRelativeTo(null);

				JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
				painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

				// Configurando campos e labels
				JPanel painelCampos = new JPanel(new GridLayout(2, 1, 5, 5));
				JLabel labelId = new JLabel("ID do produto");
				JTextField campoId = new JTextField(10);

				painelCampos.add(labelId);
				painelCampos.add(campoId);

				// Painel para os botões
				JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JButton deletarProduto = new JButton("Deletar");
				JButton cancelarProduto = new JButton("Cancelar");

				// Adicionando os botões ao painel de botões
				painelBotoes.add(deletarProduto);
				painelBotoes.add(cancelarProduto);

				cancelarProduto.addActionListener(_ -> btnE.dispose());

				deletarProduto.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String idDelete = campoId.getText();
						int idDeleteInt = Integer.parseInt(idDelete);

						try {
							daoP.deletarProduto(idDeleteInt);
							atualizarTable();
							btnE.dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnE, "Erro ao deletar: " + e1.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
					}
				});

				// Adicionando ao painel principal os paineis de campos e botões
				painelPrincipal.add(painelCampos, BorderLayout.CENTER);
				painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
				btnE.add(painelPrincipal);

				btnE.setVisible(true);
			}
		});
		

		// ----------- Finalização dos Funcionamentos dos botões CRUD -----------

		frameCadastroProduto.add(splitPane);
		frameCadastroProduto.setVisible(true);
	}
}
