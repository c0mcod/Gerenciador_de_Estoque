package br.com.estoque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import br.com.estoque.controller.EstoqueFornecedorController;
import br.com.estoque.controller.EstoqueProdutoController;

/*
 *Olá! Esse é um sistema de gerenciamento de estoque simples, mas como se trata
 *de uma ferramenta nova para mim(Java Swing) irei tentar, com esse projeto, implementar
 *funções bem estruturadas, codigo limpo e compreensivo para todos!
 *
 * Cada método terá um comentário para mostrar o seu funcionamento, além de me ajudar
 * a exercitar boas práticas.
 */

public class Interface {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			FlatIntelliJLaf.setup(); // Aplica o tema "IntelliJ"
		} catch (Exception ex) {
			System.err.println("Falha ao iniciar FlatLaf");
		}

		javax.swing.SwingUtilities.invokeLater(() -> {
			EstoqueProdutoController PC = new EstoqueProdutoController();
			EstoqueFornecedorController FC = new EstoqueFornecedorController();

			// Criação da janela principal
			JFrame frameJanela = new JFrame();
			frameJanela.setSize(850, 600);
			frameJanela.setTitle("Gerenciador de Estoque");
			frameJanela.setDefaultCloseOperation(frameJanela.EXIT_ON_CLOSE);
			frameJanela.setLocationRelativeTo(null);

			// painel onde fica o titulo
			JPanel painelTitulo = new JPanel();
			painelTitulo.setBackground(new Color(45, 69, 143));
			JLabel titulo = new JLabel("Sistema de Gerenciamento de Estoque");
			titulo.setFont(new Font("Arial", Font.BOLD, 18));
			titulo.setForeground(Color.WHITE);
			painelTitulo.add(titulo);
			painelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));

			// botões de ação
			JButton btnP = new JButton("Gerencia de Produtos");
			btnP.setBackground(new Color(2, 38, 74));
			btnP.setForeground(Color.WHITE);
			btnP.setFocusable(false);

			JButton btnF = new JButton("Gerencia de Fornecedores");
			btnF.setBackground(new Color(2, 38, 74));
			btnF.setForeground(Color.WHITE);
			btnF.setFocusable(false);

			JButton btnV = new JButton("Vender");
			btnV.setBackground(new Color(2, 38, 74));
			btnV.setForeground(Color.WHITE);
			btnV.setFocusable(false);

			// padronização dos botões
			Dimension tamanhoBotao = new Dimension(200, 30);
			btnP.setPreferredSize(tamanhoBotao);
			btnF.setPreferredSize(tamanhoBotao);
			btnV.setPreferredSize(tamanhoBotao);

			// ----------------- DEFINIÇÃO DAS AÇÕES -----------------
			btnP.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PC.gerenciarProduto();
				}
			});

			btnF.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					FC.gerenciarFornecedor();
				}
			});

			btnV.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});

			// ----------------- FINALIZAÇÃO DAS AÇÕES -----------------

			// Criação do painel inferior e organização dos botões
			JPanel painelInferior = new JPanel();

			painelInferior.add(btnP);
			painelInferior.add(btnF);
			painelInferior.add(btnV);

			painelInferior.setBackground(new Color(221, 221, 221));

			painelInferior.setBorder(new EmptyBorder(10, 0, 10, 0));

			// Definição do layout e inserção dos componentes na janela principal
			frameJanela.setLayout(new BorderLayout());
			frameJanela.add(painelTitulo, BorderLayout.NORTH);
			frameJanela.add(painelInferior, BorderLayout.SOUTH);

			frameJanela.setVisible(true);
		});
	}

}
