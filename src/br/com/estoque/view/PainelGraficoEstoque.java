package br.com.estoque.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

public class PainelGraficoEstoque extends JPanel {

	private static final long serialVersionUID = 1L;

	public PainelGraficoEstoque() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.getProdutos();
		setLayout(new BorderLayout(10, 10)); // espaçamento entre regiões

		// Ordena e limita os 5 produtos
		lista.sort((p1, p2) -> Integer.compare(p2.getQuantidade(), p1.getQuantidade()));
		List<Produto> top5 = lista.stream().limit(5).toList();

		// Cria os datasets
		DefaultPieDataset datasetPizza = new DefaultPieDataset();
		DefaultCategoryDataset datasetBarra = new DefaultCategoryDataset();

		for (Produto p : top5) {
			datasetPizza.setValue(p.getNome(), p.getQuantidade());
			datasetBarra.addValue(p.getQuantidade(), "Quantidade", p.getNome());
		}

		// Cria os gráficos
		JFreeChart graficoPizza = ChartFactory.createPieChart("Estoque por Produto", datasetPizza, true, true, false);
		JFreeChart graficoBarra = ChartFactory.createBarChart("Estoque - Barras", "Produto", "Quantidade", datasetBarra);

		ChartPanel painelPizza = new ChartPanel(graficoPizza);
		ChartPanel painelBarra = new ChartPanel(graficoBarra);

		painelPizza.setPreferredSize(new Dimension(300, 200));
		painelBarra.setPreferredSize(new Dimension(300, 200));

		// ---------------- PAINEL DE CARDS ----------------
		JPanel painelCards = new JPanel(new GridLayout(1, 3, 10, 10));

		// Card 1 (Quantidade em estoque geral)
		int estoqueTotal = dao.cardKPIEstoqueTotal();
		JPanel card1 = criarCard("Estoque Total", "" + estoqueTotal);

		// Card 2 (Quantidade de vendas)
		String MenorEstoque = dao.cardKPIMenorEmEstoque();
		JPanel card2 = criarCard("Menor quantidade em estoque", "" + MenorEstoque);

		// Card 3 (Produto em menor quantidade em estoque)
		JPanel card3 = criarCard("Numéro total de vendas", "Mais um valor");

		painelCards.add(card1);
		painelCards.add(card2);
		painelCards.add(card3);

		// ---------------- PAINEL DE GRÁFICOS ----------------
		JPanel painelGraficos = new JPanel(new GridLayout(1, 2, 10, 10));
		painelGraficos.add(painelPizza);
		painelGraficos.add(painelBarra);

		// ---------------- ADICIONANDO AOS REGIÕES ----------------
		add(painelCards, BorderLayout.NORTH);
		add(painelGraficos, BorderLayout.CENTER);

		// Margens externas 
		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
	}

	private JPanel criarCard(String titulo, String valor) {
		JPanel card = new JPanel();
		card.setBackground(new Color(230, 230, 250));
		card.setBorder(BorderFactory.createTitledBorder(titulo));
		card.setLayout(new BorderLayout());

		JLabel label = new JLabel(valor);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 16));

		card.add(label, BorderLayout.CENTER);
		return card;
	}
}
