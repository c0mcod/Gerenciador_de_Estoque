package br.com.estoque.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaGraficoEstoque extends JFrame{
	
	public TelaGraficoEstoque() throws SQLException {
		new Produto();
		ProdutoDAO pdao = new ProdutoDAO();
		List<Produto> lista = pdao.getProdutos();
		
        setTitle("Estatísticas de Estoque");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Produto p1 : lista) {
        	dataset.setValue(p1.getQuantidade(), p1.getNome(), p1.getPreco());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Estoque por Categoria", 
            "Categoria", 
            "Quantidade", 
            dataset
        );
        ChartPanel painelGrafico = new ChartPanel(barChart);
        setContentPane(painelGrafico);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			try {
				new TelaGraficoEstoque().setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
}


