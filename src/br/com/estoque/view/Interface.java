package br.com.estoque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.estoque.controller.EstoqueController;
import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

/*
 *Olá! Esse é um sistema de gerenciamento de estoque simples, mas como se trata
 *de uma ferramenta nova para mim(Java Swing) irei tentar, com esse projeto, implementar
 *funções bem estruturadas, codigo limpo e compreensivo para todos!
 *
 * Cada método terá um comentário para mostrar o seu funcionamento, além de me ajudar
 * a exercitar boas práticas.
 */

public class Interface extends EstoqueController{

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		EstoqueController EC = new EstoqueController();
		ProdutoDAO daoP = new ProdutoDAO();
		Produto p = new Produto();
		// Criação da janela principal
		JFrame frameJanela = new JFrame();
		frameJanela.setSize(700, 500);
		frameJanela.setTitle("Gerenciador de Estoque");
		frameJanela.setDefaultCloseOperation(frameJanela.EXIT_ON_CLOSE);

		// painel onde fica o titulo
		JPanel painelTitulo = new JPanel();
		painelTitulo.setBackground(new Color(230, 230, 250));
		JLabel titulo = new JLabel("Sistema de Gerenciamento de Estoque");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		painelTitulo.add(titulo);
		painelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));

		// botões de ação
		JButton btnC = new JButton("Adicionar produto");
		JButton btnU = new JButton("Atualizar Produto");
		JButton btnD = new JButton("Deletar Produto");
		JButton btnV = new JButton("Vender");

		// padronização dos botões
		Dimension tamanhoBotao = new Dimension(160, 30);
		btnC.setPreferredSize(tamanhoBotao);
		btnU.setPreferredSize(tamanhoBotao);
		btnD.setPreferredSize(tamanhoBotao);
		btnV.setPreferredSize(tamanhoBotao);

		// ----------------- DEFINIÇÃO DAS AÇÕES -----------------
		btnC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// criação da janela secundária de cadastro de produtos
				JFrame frameCadastroProduto = new JFrame();
				frameCadastroProduto.setSize(700, 500);
				frameCadastroProduto.setTitle("Cadastro de produto");
				frameCadastroProduto.setDefaultCloseOperation(frameCadastroProduto.DISPOSE_ON_CLOSE);

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
				
				Dimension tamanhoButtons = new Dimension(190,60);
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
					EC.atualizarTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				//----------- Funcionamento dos botões CRUD de Produtos -----------
				
				//BOTÃO CADASTRAR
				btnCadastrar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame btnC = new JFrame();
						btnC.setSize(500,300);
						btnC.setTitle("Cadastrar Produto");
						btnC.setDefaultCloseOperation(btnC.DISPOSE_ON_CLOSE);
						
						btnC.setLocationRelativeTo(null); 
					    
					    JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
					    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
					    
					    // Configurando campos e labels
					    JLabel labelNome = new JLabel("Nome:");
					    JTextField campoNome = new JTextField(10);
					    
					    JLabel labelPreco = new JLabel("Preço:");
					    JTextField campoPreco = new JTextField(10);
					    
					    JLabel labelQuantidade = new JLabel("Quantidade:");
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
					                EC.atualizarTable();
					                btnC.dispose();
					            } catch (SQLException e1) {
					                e1.printStackTrace();
					                JOptionPane.showMessageDialog(btnC, "Erro ao cadastrar: " + e1.getMessage(), 
					                        "Erro", JOptionPane.ERROR_MESSAGE);
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
				//FIM DO BOTÃO CADASTRAR
				//----------- Finalização dos Funcionamentos dos botões CRUD -----------

				frameCadastroProduto.add(splitPane);
				frameCadastroProduto.setVisible(true);
			}
		});

		// ----------------- FINALIZAÇÃO DAS AÇÕES -----------------

		// Criação do painel inferior e organização dos botões
		JPanel painelInferior = new JPanel();

		painelInferior.add(btnC);
		painelInferior.add(btnU);
		painelInferior.add(btnD);
		painelInferior.add(btnV);

		painelInferior.setBorder(new EmptyBorder(10, 0, 10, 0));

		// Definição do layout e inserção dos componentes na janela principal
		frameJanela.setLayout(new BorderLayout());
		frameJanela.add(painelTitulo, BorderLayout.NORTH);
		frameJanela.add(painelInferior, BorderLayout.SOUTH);

		frameJanela.setVisible(true);

	}

}
