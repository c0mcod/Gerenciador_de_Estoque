package br.com.estoque.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

public class PainelGraficoEstoque extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PainelGraficoEstoque() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.getProdutos();
		setLayout(new GridLayout(1, 2));

		// Ordenando a lista dos 5 primeiros produtos em ordem crescente
		lista.sort((p1, p2) -> Integer.compare(p2.getQuantidade(), p1.getQuantidade()));

		// Limita aos 5 primeiros
		List<Produto> top5 = lista.stream().limit(5).toList();

		// Cria os dois datasets
		DefaultPieDataset datasetPizza = new DefaultPieDataset();
		DefaultCategoryDataset datasetBarra = new DefaultCategoryDataset();

		// Preenche os dados do gráfico de pizza
		for (Produto p : top5) {
			datasetPizza.setValue(p.getNome(), p.getQuantidade());
			datasetBarra.addValue(p.getQuantidade(), "Quantidade", p.getNome());
		}

		// Cria os gráficos

		// ---------- GRAFICO PIZZA ----------
		JFreeChart graficoPizza = ChartFactory.createPieChart("Estoque por Produto", datasetPizza, true, true, false);
		JFreeChart chartPizza = ChartFactory.createPieChart("Estoque - Pizza", datasetPizza);
		ChartPanel panelPizza = new ChartPanel(chartPizza);
		add(panelPizza);
		panelPizza.setPreferredSize(new Dimension(100, 200));

		// ---------- GRAFICO BARRA ----------
		JFreeChart graficoBarra = ChartFactory.createBarChart("Gráfico de Barras - Estoque", "Produto", "Quantidade",
				datasetBarra);
		JFreeChart chartBar = ChartFactory.createBarChart("Estoque - Barras", "Produto", "Qtd", datasetBarra);
		ChartPanel panelBar = new ChartPanel(chartBar);
		add(panelBar);
		panelBar.setPreferredSize(new Dimension(100, 200));

		// Cria os painéis para cada gráfico
		ChartPanel painelPizza = new ChartPanel(graficoPizza);
		ChartPanel painelBarra = new ChartPanel(graficoBarra);

		// Painel principal com GridLayout 1 linha, 2 colunas
		JPanel painelGraficos = new JPanel(new GridLayout(1, 2));
		painelGraficos.add(painelPizza);
		painelGraficos.add(painelBarra);
	}
}
