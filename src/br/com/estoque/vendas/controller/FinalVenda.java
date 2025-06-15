package br.com.estoque.vendas.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class FinalVenda {
	public FinalVenda() {

		// Cores do sistema
		Color azulPrincipal = new Color(41, 98, 149);
		@SuppressWarnings("unused")
		Color azulSecundario = new Color(51, 122, 183);
		Color cinzaClaro = new Color(240, 240, 240);
		
		JFrame janelaPrincipal = new JFrame();
		
		janelaPrincipal.setTitle("Concluir venda");
		janelaPrincipal.setSize(400, 300);
		janelaPrincipal.setLocationRelativeTo(null);
		janelaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel header = new JPanel();
		header.setBackground(azulPrincipal);
		header.setPreferredSize(new Dimension(0, 60));
		JLabel titulo = new JLabel("METODO DE PAGAMENTO");
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setForeground(Color.WHITE);
		header.add(titulo);
		janelaPrincipal.add(header, BorderLayout.NORTH);

		// Painel principal
		JPanel painelPrincipal = new JPanel(new BorderLayout());
		painelPrincipal.setBackground(cinzaClaro);
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		janelaPrincipal.setVisible(true);
	}
}
