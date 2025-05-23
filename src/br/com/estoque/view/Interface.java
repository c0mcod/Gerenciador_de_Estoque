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

import br.com.estoque.controller.EstoqueController;

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
		JButton btnP = new JButton("Gerencia de Produtos");
		JButton btnF = new JButton("Gerencia de Fornecedores");
		JButton btnV = new JButton("Vender");

		// padronização dos botões
		Dimension tamanhoBotao = new Dimension(160, 30);
		btnP.setPreferredSize(tamanhoBotao);
		btnF.setPreferredSize(tamanhoBotao);
		btnV.setPreferredSize(tamanhoBotao);

		// ----------------- DEFINIÇÃO DAS AÇÕES -----------------
		btnP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EC.cadastrarProduto();
			}
		});

		btnF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EC.cadastrarProduto();
				
			}
		});
		
	
		// ----------------- FINALIZAÇÃO DAS AÇÕES -----------------

		// Criação do painel inferior e organização dos botões
		JPanel painelInferior = new JPanel();

		painelInferior.add(btnP);
		painelInferior.add(btnF);
		painelInferior.add(btnV);

		painelInferior.setBorder(new EmptyBorder(10, 0, 10, 0));

		// Definição do layout e inserção dos componentes na janela principal
		frameJanela.setLayout(new BorderLayout());
		frameJanela.add(painelTitulo, BorderLayout.NORTH);
		frameJanela.add(painelInferior, BorderLayout.SOUTH);

		frameJanela.setVisible(true);

	}

}
