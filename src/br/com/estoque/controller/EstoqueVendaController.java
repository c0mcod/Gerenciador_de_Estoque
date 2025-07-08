package br.com.estoque.controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import br.com.estoque.vendas.controller.ItemVenda;
import br.com.estoque.vendas.controller.Venda;

import java.util.List;

public class EstoqueVendaController {

	String[] colunas = { "Codigo", "Produto", "Quantidade", "Preço", "Total" };
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	JTable tabela = new JTable(modelo);

	JScrollPane scroll = new JScrollPane(tabela);
	ProdutoDAO daoP = new ProdutoDAO();
	Produto p = new Produto();

	List<ItemVenda> itensDaVenda = new ArrayList<>();
	
	@SuppressWarnings("serial")
	public void realizarVenda() {
		JFrame janelaPrincipal = new JFrame();
		janelaPrincipal.setTitle("CAIXA ABERTO");
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPrincipal.setSize(1200, 800);
		janelaPrincipal.setLocationRelativeTo(null);

		// Cores do sistema
		Color azulPrincipal = new Color(41, 98, 149);
		Color azulSecundario = new Color(51, 122, 183);
		Color cinzaClaro = new Color(240, 240, 240);

		// Layout principal
		janelaPrincipal.setLayout(new BorderLayout());

		// Header
		JPanel header = new JPanel();
		header.setBackground(azulPrincipal);
		header.setPreferredSize(new Dimension(0, 60));
		JLabel titulo = new JLabel("CAIXA ABERTO");
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setForeground(Color.WHITE);
		header.add(titulo);
		janelaPrincipal.add(header, BorderLayout.NORTH);

		// Painel principal
		JPanel painelPrincipal = new JPanel(new BorderLayout());
		painelPrincipal.setBackground(cinzaClaro);
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// === LADO ESQUERDO ===
		JPanel painelEsquerdo = new JPanel();
		painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
		painelEsquerdo.setPreferredSize(new Dimension(400, 0));
		painelEsquerdo.setBackground(Color.WHITE);
		painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Campo código de barras
		JPanel quantidade = new JPanel(new BorderLayout());
		quantidade.setBorder(BorderFactory.createTitledBorder("QUANTIDADE"));
		quantidade.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		JTextField campoQtd = new JTextField();
		campoQtd.setFont(new Font("Arial", Font.PLAIN, 16));
		quantidade.add(campoQtd);
		painelEsquerdo.add(quantidade);

		JPanel painelCodBarras = new JPanel(new BorderLayout());
		painelCodBarras.setBorder(BorderFactory.createTitledBorder("CÓDIGO DE BARRAS"));
		painelCodBarras.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		JTextField campoCodigo = new JTextField();
		campoCodigo.setFont(new Font("Arial", Font.PLAIN, 16));
		painelCodBarras.add(campoCodigo);
		painelEsquerdo.add(painelCodBarras);

		// Painel para o botão adicionar
		JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // 10px de espaço horizontal
		painelBotao.setBackground(Color.WHITE);

		JButton adc = new JButton("ADICIONAR PRODUTO");
		adc.setBackground(new Color(40, 167, 69)); // Verde mais moderno
		adc.setForeground(Color.WHITE);
		adc.setFocusable(false);
		adc.setFont(new Font("Arial", Font.BOLD, 14));
		adc.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		adc.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Efeito hover para botão adicionar
		adc.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				adc.setBackground(new Color(34, 139, 58)); // Verde mais escuro no hover
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				adc.setBackground(new Color(40, 167, 69)); // Volta para a cor original
			}
		});

		// Botão FINALIZAR VENDA
		JButton finalizarVenda = new JButton("FINALIZAR VENDA");
		finalizarVenda.setBackground(new Color(220, 53, 69));
		finalizarVenda.setForeground(Color.WHITE);
		finalizarVenda.setFocusable(false);
		finalizarVenda.setFont(new Font("Arial", Font.BOLD, 14));
		finalizarVenda.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		finalizarVenda.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Efeito hover para botão finalizar venda
		finalizarVenda.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				finalizarVenda.setBackground(new Color(200, 35, 51));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				finalizarVenda.setBackground(new Color(220, 53, 69));
			}
		});

		// Ativar botão adicionar com tecla "Enter"
		campoCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					adc.doClick();
					campoCodigo.setText("");
					campoQtd.setText("");
					campoQtd.requestFocus();
				}
			}
		});

		KeyStroke f9KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0);
		finalizarVenda.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f9KeyStroke, "finalizarVenda");
		finalizarVenda.getActionMap().put("finalizarVenda", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finalizarVenda.doClick();
			}
		});

		// Definir tamanhos preferidos
		Dimension tamanhoBotao = new Dimension(160, 40);
		adc.setPreferredSize(tamanhoBotao);
		finalizarVenda.setPreferredSize(tamanhoBotao);

		painelBotao.add(adc);
		painelBotao.add(Box.createVerticalStrut(10));
		painelBotao.add(finalizarVenda);
		painelEsquerdo.add(painelBotao);

		// Adicionar um espaçamento após o botão
		painelEsquerdo.add(Box.createVerticalStrut(15));

		painelEsquerdo.add(Box.createVerticalStrut(20));

		// Valor unitário
		JPanel painelValorUnit = new JPanel(new BorderLayout());
		painelValorUnit.setBorder(BorderFactory.createTitledBorder("VALOR UNITÁRIO"));
		painelValorUnit.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		JLabel valorUnit = new JLabel(" ", SwingConstants.CENTER);
		valorUnit.setFont(new Font("Arial", Font.BOLD, 18));
		painelValorUnit.add(valorUnit);
		painelEsquerdo.add(painelValorUnit);

		painelEsquerdo.add(Box.createVerticalStrut(10));

		// Total do item
		JPanel painelTotalItem = new JPanel(new BorderLayout());
		painelTotalItem.setBorder(BorderFactory.createTitledBorder("TOTAL DO ITEM"));
		painelTotalItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		JLabel totalItem = new JLabel(" ", SwingConstants.CENTER);
		totalItem.setFont(new Font("Arial", Font.BOLD, 18));
		painelTotalItem.add(totalItem);
		painelEsquerdo.add(painelTotalItem);

		painelEsquerdo.add(Box.createVerticalStrut(20));

		// Nome do item
		JPanel painelNomeItem = new JPanel(new BorderLayout());
		painelNomeItem.setBorder(BorderFactory.createTitledBorder("NOME DO ITEM"));
		painelNomeItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		JLabel nomeItem = new JLabel(" ", SwingConstants.CENTER);
		nomeItem.setFont(new Font("Arial", Font.BOLD, 18));
		painelNomeItem.add(nomeItem);
		painelEsquerdo.add(painelNomeItem);

		painelEsquerdo.add(Box.createVerticalStrut(20));

		// Código
		JPanel painelCodigo = new JPanel(new BorderLayout());
		painelCodigo.setBorder(BorderFactory.createTitledBorder("CÓDIGO"));
		painelCodigo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		JLabel codigo = new JLabel(" ", SwingConstants.CENTER);
		codigo.setFont(new Font("Arial", Font.BOLD, 24));
		painelCodigo.add(codigo);
		painelEsquerdo.add(painelCodigo);

		painelEsquerdo.add(Box.createVerticalStrut(20));

		// Painel de funções
		JPanel painelFuncoes = new JPanel(new GridLayout(3, 3, 5, 5));
		painelFuncoes.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
		painelFuncoes.setBorder(BorderFactory.createTitledBorder("FUNÇÕES"));

		String[] funcoes = { "F2 - Código", "F7 - Pesquisar", "F9 - Alterar", "F3 - Excluir", "F8 - Produto",
				"F10 - Finalizar", "F5 - Nova Venda", "P - Imprimir", "Esc - Sair" };

		for (String funcao : funcoes) {
			JLabel label = new JLabel("<html><center>" + funcao + "</center></html>");
			label.setBorder(BorderFactory.createLineBorder(azulSecundario));
			label.setOpaque(true);
			label.setBackground(new Color(240, 248, 255));
			label.setFont(new Font("Arial", Font.PLAIN, 10));
			painelFuncoes.add(label);
		}

		painelEsquerdo.add(painelFuncoes);
		painelEsquerdo.add(Box.createVerticalGlue());

		painelPrincipal.add(painelEsquerdo, BorderLayout.WEST);

		// === LADO DIREITO - LISTA DE PRODUTOS ===
		JPanel painelDireito = new JPanel(new BorderLayout());
		painelDireito.setBackground(Color.WHITE);
		painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		// Header da lista
		JPanel headerLista = new JPanel();
		headerLista.setBackground(azulPrincipal);
		headerLista.setPreferredSize(new Dimension(0, 40));
		JLabel tituloLista = new JLabel("LISTA DE PRODUTOS");
		tituloLista.setFont(new Font("Arial", Font.BOLD, 16));
		tituloLista.setForeground(Color.WHITE);
		headerLista.add(tituloLista);
		painelDireito.add(headerLista, BorderLayout.NORTH);

		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		tabela.setRowHeight(25);
		tabela.getTableHeader().setBackground(azulPrincipal);
		tabela.getTableHeader().setForeground(Color.WHITE);
		tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Selecionar última linha
		tabela.setSelectionBackground(azulSecundario);
		tabela.setSelectionForeground(Color.WHITE);
		if (tabela.getRowCount() > 0) {
			tabela.setRowSelectionInterval(tabela.getRowCount() - 1, tabela.getRowCount() - 1);
		}

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBorder(BorderFactory.createLineBorder(azulPrincipal));
		painelDireito.add(scrollPane, BorderLayout.CENTER);

		painelPrincipal.add(painelDireito, BorderLayout.CENTER);

		// === PAINEL INFERIOR - TOTAIS ===
		JPanel painelInferior = new JPanel(new GridLayout(1, 3, 20, 0));
		painelInferior.setBackground(Color.WHITE);
		painelInferior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		painelInferior.setPreferredSize(new Dimension(0, 120));

		// Subtotal
		JPanel painelSubtotal = new JPanel(new BorderLayout());
		painelSubtotal.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azulPrincipal, 2), "SUBTOTAL"));
		JLabel subtotalLabel = new JLabel("", SwingConstants.CENTER);
		subtotalLabel.setFont(new Font("Arial", Font.BOLD, 32));
		subtotalLabel.setForeground(azulPrincipal);
		painelSubtotal.add(subtotalLabel);
		painelInferior.add(painelSubtotal);

		// Total recebido
		JPanel painelTotalRecebido = new JPanel(new BorderLayout());
		painelTotalRecebido.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azulPrincipal, 2), "TOTAL RECEBIDO"));
		JLabel totalRecebido = new JLabel("", SwingConstants.CENTER);
		totalRecebido.setFont(new Font("Arial", Font.BOLD, 24));
		totalRecebido.setForeground(azulPrincipal);
		painelTotalRecebido.add(totalRecebido);
		painelInferior.add(painelTotalRecebido);

		// Troco
		JPanel painelTroco = new JPanel(new BorderLayout());
		painelTroco
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azulPrincipal, 2), "TROCO"));
		JLabel troco = new JLabel("R$ 0,00", SwingConstants.CENTER);
		troco.setFont(new Font("Arial", Font.BOLD, 24));
		troco.setForeground(azulPrincipal);
		painelTroco.add(troco);
		painelInferior.add(painelTroco);

		// -------------------- OPERAÇÕES DE VENDA --------------------
		// Inicialmente um teste, quero ver se manualmente um produto consegue ser
		// adiconado pelo id
		adc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String campoQuantidade = campoQtd.getText();
				String campoCodigoBd = campoCodigo.getText();
				int campoCodigoBdInt = Integer.parseInt(campoCodigoBd);
				int campoQuantidadeInt = Integer.parseInt(campoQuantidade);

				try {
					double total = daoP.calcularValorVenda(campoCodigoBdInt, campoQuantidadeInt);
					double precoUnitLabel = total / campoQuantidadeInt;

					String nomeProduto = daoP.exibirNome(campoCodigoBdInt);
					valorUnit.setText(String.format("R$ %.2f", precoUnitLabel));

					Object[] row = { campoCodigoBdInt, nomeProduto, campoQuantidadeInt,
							String.format("R$ %.2f", precoUnitLabel), String.format("R$ %.2f", total) };

					modelo.addRow(row);

					totalItem.setText(String.format("R$ %.2f", total));
					codigo.setText(campoCodigoBd);

					// Calcular subtotal
					double subtotal = 0.0;
					for (int i = 0; i < modelo.getRowCount(); i++) {
						String valorStr = modelo.getValueAt(i, 4).toString();
						valorStr = valorStr.replace("R$ ", "").replace(",", ".");
						double valor = Double.parseDouble(valorStr);
						subtotal += valor;
					}

					// Atualiza label
					subtotalLabel.setText(String.format("R$ %.2f", subtotal));

					nomeItem.setText(nomeProduto);
					
					finalizarVenda.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String valorTexto = subtotalLabel.getText().replace("R$ ", "").replace(",", ".");
							double subtotal = Double.parseDouble(valorTexto);

							abrirTelaPagamento(subtotal);
							ItemVenda item = new ItemVenda(campoCodigoBdInt, nomeProduto, campoQuantidadeInt, precoUnitLabel,
									total);
							itensDaVenda.add(item);

							double valorTotal = 0.0;
							for (ItemVenda item1 : itensDaVenda) {
								valorTotal += item1.getTotal();
							}

							Venda venda = new Venda(itensDaVenda, valorTotal);


						}
					});

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		
		// -------------------- FIM --------------------

		painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

		janelaPrincipal.add(painelPrincipal, BorderLayout.CENTER);

		janelaPrincipal.setVisible(true);

	}

	public void abrirTelaPagamento(double subtotal) {

		Color azulPrincipal = new Color(41, 98, 149);

		JFrame telaPagamento = new JFrame();
		telaPagamento.setTitle("Metodo de pagamento");
		telaPagamento.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		telaPagamento.setSize(400, 300);
		telaPagamento.setLocationRelativeTo(null);

		// Header
		JPanel header = new JPanel();
		header.setBackground(azulPrincipal);
		header.setPreferredSize(new Dimension(0, 60));
		JLabel titulo = new JLabel("Metodo de Pagamento");
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setForeground(Color.WHITE);
		header.add(titulo);
		telaPagamento.add(header, BorderLayout.NORTH);

		// Painel principal
		JPanel painelPagamento = new JPanel(new GridBagLayout());
		painelPagamento.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		// Valor
		gbc.gridx = 0;
		gbc.gridy = 0;
		painelPagamento.add(new JLabel("Valor:"), gbc);

		JTextField txtValor = new JTextField(String.format("R$ %.2f", subtotal), 15);
		txtValor.setEditable(false);
		gbc.gridx = 1;
		painelPagamento.add(txtValor, gbc);

		// Método de pagamento
		gbc.gridx = 0;
		gbc.gridy = 1;
		painelPagamento.add(new JLabel("Método:"), gbc);

		JComboBox<String> cbMetodoPagamento = new JComboBox<>(
				new String[] { "Cartão de Crédito", "Cartão de Débito", "PIX", "Boleto" });
		gbc.gridx = 1;
		painelPagamento.add(cbMetodoPagamento, gbc);

		JLabel lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		painelPagamento.add(lblStatus, gbc);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBackground(new Color(40, 167, 69));
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setFont(new Font("Arial", Font.BOLD, 14));
		btnPagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});

		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		painelPagamento.add(btnPagar, gbc);

		telaPagamento.add(painelPagamento, BorderLayout.CENTER);
		telaPagamento.setVisible(true);
	}

}