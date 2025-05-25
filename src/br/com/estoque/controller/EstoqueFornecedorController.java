package br.com.estoque.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.estoque.fornecedor.dao.FornecedorDAO;
import br.com.estoque.fornecedor.model.Fornecedor;

public class EstoqueFornecedorController {
	String[] colunas = { "ID", "Nome", "CNPJ" };
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	JTable tabela = new JTable(modelo);
	public JScrollPane scroll = new JScrollPane(tabela);

	Fornecedor f = new Fornecedor();
	Fornecedor FA = new Fornecedor();
	FornecedorDAO fdao = new FornecedorDAO();

	public void atualizarTable() throws SQLException {
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> lista = dao.getFornecedores();
		modelo.setRowCount(0);

		for (Fornecedor f : lista) {
			Object[] row = { f.getId(), f.getNome(), f.getCnpj() };
			modelo.addRow(row);
		}
	}

	public void gerenciarFornecedor() {
		// criação da janela secundária de cadastro de produtos
		JFrame frameCadastroFornecedor = new JFrame();
		frameCadastroFornecedor.setSize(700, 500);
		frameCadastroFornecedor.setLocationRelativeTo(null);
		frameCadastroFornecedor.setTitle("Gerenciamento de fornecedor");
		frameCadastroFornecedor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// ----------- Painel dos botões (direita) -----------
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
		painelBotoes.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20)); // padding

		// ---------- Painel do titulo -------------
		JPanel painelTitulo = new JPanel();
		painelTitulo.setBackground(new Color(45, 69, 143));
		JLabel titulo = new JLabel("Gerenciamento de Fornecedores");
		titulo.setFont(new Font("Arial", Font.BOLD, 19));
		titulo.setForeground(Color.WHITE);
		painelTitulo.add(titulo);
		painelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Criação e estilização dos botões de ação
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(39, 174, 96));
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFocusPainted(false);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBackground(new Color(5, 50, 84));
		btnAtualizar.setForeground(Color.WHITE);
		btnAtualizar.setFocusable(false);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(177, 1, 4));
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setFocusable(false);

		painelBotoes.add(btnCadastrar);
		painelBotoes.add(Box.createVerticalStrut(20));
		painelBotoes.add(btnAtualizar);
		painelBotoes.add(Box.createVerticalStrut(20));
		painelBotoes.add(btnExcluir);
		painelBotoes.add(Box.createVerticalStrut(20));

		Dimension tamanhoButtons = new Dimension(190, 60);
		btnCadastrar.setPreferredSize(tamanhoButtons);
		btnAtualizar.setPreferredSize(tamanhoButtons);
		btnExcluir.setPreferredSize(tamanhoButtons);

		frameCadastroFornecedor.setLayout(new BorderLayout());
		frameCadastroFornecedor.add(scroll, BorderLayout.CENTER);
		frameCadastroFornecedor.add(painelBotoes, BorderLayout.EAST);

		try {
			atualizarTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// ----------- Funcionamento dos botões CRUD de Produtos -----------

		btnCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame btnC = new JFrame();
				btnC.setSize(500, 300);
				btnC.setTitle("Cadastrar Fornecedor");
				btnC.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				btnC.setLocationRelativeTo(null);

				// Painel principal, onde receberá outros paineis
				JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
				painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

				// campos e labels
				JLabel labelNome = new JLabel("Nome");
				JTextField campoNome = new JTextField(20);

				MaskFormatter mascaraCNPJ = null;

				try {
					mascaraCNPJ = new MaskFormatter("##.###.###/####-##");
					mascaraCNPJ.setPlaceholderCharacter('_');
					;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				JLabel labelCnpj = new JLabel("CNPJ");
				JFormattedTextField campoCNPJ = new JFormattedTextField(mascaraCNPJ);

				// painel de campos
				JPanel painelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
				painelCampos.add(labelCnpj);
				painelCampos.add(campoCNPJ);
				painelCampos.add(labelNome);
				painelCampos.add(campoNome);

				// Painel para os botões
				JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JButton enviarFornecedor = new JButton("Enviar");
				JButton cancelarFornecedor = new JButton("Cancelar");

				cancelarFornecedor.addActionListener(_ -> btnC.dispose());

				painelBotoes.add(cancelarFornecedor);
				painelBotoes.add(enviarFornecedor);

				enviarFornecedor.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String nomeBD = campoNome.getText();
						String cnpjBD = campoCNPJ.getText();
						f.setNome(nomeBD);
						f.setCnpj(cnpjBD);

						try {
							fdao.CadastrarFornecedor(f);
							atualizarTable();
							btnC.dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnC, "Erro ao cadastrar: " + e1.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						JOptionPane.showMessageDialog(null, "Fornecedor Cadastrada!");
					}
				});

				painelPrincipal.add(painelCampos, BorderLayout.CENTER);
				painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

				btnC.add(painelPrincipal);
				btnC.setVisible(true);
			}
		});

		btnAtualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame btnA = new JFrame();
				btnA.setSize(500, 300);
				btnA.setTitle("Atualizar Fornecedor");
				btnA.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				btnA.setLocationRelativeTo(null);

				// Painel principal, onde receberá outros paineis
				JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
				painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

				// campos e labels
				JLabel labelNome = new JLabel("Nome");
				JTextField campoNome = new JTextField(20);

				MaskFormatter mascaraCNPJ = null;

				try {
					mascaraCNPJ = new MaskFormatter("##.###.###/####-##");
					mascaraCNPJ.setPlaceholderCharacter('_');
					;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				JLabel labelCnpj = new JLabel("CNPJ");
				JFormattedTextField campoCNPJ = new JFormattedTextField(mascaraCNPJ);

				JLabel labelId = new JLabel("ID");
				JTextField campoId = new JTextField(10);

				// painel de campos
				JPanel painelCampos = new JPanel(new GridLayout(3, 2, 5, 5));
				painelCampos.add(labelCnpj);
				painelCampos.add(campoCNPJ);
				painelCampos.add(labelNome);
				painelCampos.add(campoNome);
				painelCampos.add(labelId);
				painelCampos.add(campoId);

				// Painel para os botões
				JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JButton enviarFornecedor = new JButton("Enviar");
				JButton cancelarFornecedor = new JButton("Cancelar");

				cancelarFornecedor.addActionListener(_ -> btnA.dispose());

				painelBotoes.add(cancelarFornecedor);
				painelBotoes.add(enviarFornecedor);

				enviarFornecedor.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String newNomeBD = campoNome.getText();
						String newCNPJBD = campoCNPJ.getText();
						String idUp = campoId.getText();
						int idUpInt = Integer.parseInt(idUp);

						FA.setNome(newNomeBD);
						FA.setCnpj(newCNPJBD);
						FA.setId(idUpInt);

						try {
							fdao.atualizarFornecedor(FA);
							atualizarTable();
							btnA.dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnA, "Erro ao atualizar: " + e1.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						JOptionPane.showMessageDialog(null, "Fornecedor Atualizado!");
					}
				});
				painelPrincipal.add(painelCampos, BorderLayout.CENTER);
				painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
				btnA.add(painelPrincipal);

				btnA.setVisible(true);
			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame btnE = new JFrame();
				btnE.setTitle("Excluir Fornecedor");
				btnE.setSize(400, 200);
				btnE.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				btnE.setLocationRelativeTo(null);

				JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
				painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

				// Configurando campos e labels
				JPanel painelCampos = new JPanel(new GridLayout(2, 1, 5, 5));
				JLabel labelId = new JLabel("ID do Fornecedor");
				JTextField campoId = new JTextField(10);

				painelCampos.add(labelId);
				painelCampos.add(campoId);

				// Painel para os botões
				JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JButton deletarFornecedor = new JButton("Deletar");
				JButton cancelarFornecedor = new JButton("Cancelar");

				// Adicionando os botões ao painel de botões
				painelBotoes.add(deletarFornecedor);
				painelBotoes.add(cancelarFornecedor);

				cancelarFornecedor.addActionListener(_ -> btnE.dispose());

				deletarFornecedor.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String idBD = campoId.getText();
						int idBDInt = Integer.parseInt(idBD);

						try {
							fdao.deletarFornecedor(idBDInt);
							atualizarTable();
							btnE.dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnE, "Erro ao deletar: " + e1.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "Fornecedor deletado com sucesso!");
					}
				});
				painelPrincipal.add(painelCampos, BorderLayout.CENTER);
				painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
				btnE.add(painelPrincipal);

				btnE.setVisible(true);
			}
		});
		// ----------- Finalização dos Funcionamentos dos botões CRUD -----------
		
		tabela.setRowHeight(25);
		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		tabela.getTableHeader().setBackground(new Color(52, 152, 219));
		tabela.getTableHeader().setForeground(Color.WHITE);
		tabela.getTableHeader().setFocusable(false);
		
		frameCadastroFornecedor.add(painelTitulo, BorderLayout.NORTH);
		frameCadastroFornecedor.setVisible(true);
	}
}
