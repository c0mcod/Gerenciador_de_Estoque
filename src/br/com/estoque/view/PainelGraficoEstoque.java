package br.com.estoque.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PainelGraficoEstoque extends JPanel {
    private static final long serialVersionUID = 1L;

    public PainelGraficoEstoque() throws SQLException {
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> lista = dao.getProdutos();
        
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(248, 249, 250));

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

        // ============== CRIAÇÃO DOS GRÁFICOS MELHORADOS ==============
        JFreeChart graficoPizza = ChartFactory.createPieChart("Estoque por Produto", datasetPizza, true, true, false);
        JFreeChart graficoBarra = ChartFactory.createBarChart("Distribuição do Estoque", "Produto", "Quantidade", datasetBarra);

        // Melhorias no gráfico de pizza
        configurarGraficoPizza(graficoPizza, datasetPizza);
        
        // Melhorias no gráfico de barras
        configurarGraficoBarra(graficoBarra);

        // Painéis dos gráficos
        ChartPanel painelPizza = new ChartPanel(graficoPizza);
        ChartPanel painelBarra = new ChartPanel(graficoBarra);
        
        painelPizza.setPreferredSize(new Dimension(350, 250));
        painelBarra.setPreferredSize(new Dimension(350, 250));
        painelPizza.setBorder(BorderFactory.createEmptyBorder());
        painelBarra.setBorder(BorderFactory.createEmptyBorder());

        // ---------------- PAINEL DE CARDS MELHORADO ----------------
        JPanel painelCards = new JPanel(new GridLayout(1, 3, 15, 15));
        painelCards.setBackground(new Color(248, 249, 250));

        // Cards com visual moderno
        int estoqueTotal = dao.cardKPIEstoqueTotal();
        JPanel card1 = criarCardModerno("Estoque Total", String.valueOf(estoqueTotal), 
            new Color(52, 152, 219), "📦");

        String menorEstoque = dao.cardKPIMenorEmEstoque();
        JPanel card2 = criarCardModerno("Menor Estoque", menorEstoque, 
            new Color(231, 76, 60), "⚠️");

        JPanel card3 = criarCardModerno("Total de Vendas", "Mais um valor", 
            new Color(46, 204, 113), "💰");

        painelCards.add(card1);
        painelCards.add(card2);
        painelCards.add(card3);

        // ---------------- PAINEL DE GRÁFICOS COM CONTAINERS ----------------
        JPanel painelGraficos = new JPanel(new GridLayout(1, 2, 15, 15));
        painelGraficos.setBackground(new Color(248, 249, 250));

        // Containers com bordas elegantes
        JPanel containerPizza = criarContainerGrafico(painelPizza);
        JPanel containerBarra = criarContainerGrafico(painelBarra);

        painelGraficos.add(containerPizza);
        painelGraficos.add(containerBarra);

        // ---------------- MONTAGEM FINAL ----------------
        add(painelCards, BorderLayout.NORTH);
        add(painelGraficos, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    }

    @SuppressWarnings("rawtypes")
	private void configurarGraficoPizza(JFreeChart graficoPizza, DefaultPieDataset dataset) {
        PiePlot plotPizza = (PiePlot) graficoPizza.getPlot();
        plotPizza.setBackgroundPaint(Color.WHITE);
        plotPizza.setOutlineVisible(false);
        plotPizza.setShadowPaint(null);

        Color[] coresPizza = {
            new Color(52, 152, 219),   // Azul
            new Color(46, 204, 113),   // Verde
            new Color(155, 89, 182),   // Roxo
            new Color(241, 196, 15),   // Amarelo
            new Color(231, 76, 60)     // Vermelho
        };

        int i = 0;
        for (Object key : dataset.getKeys()) {
            plotPizza.setSectionPaint((Comparable) key, coresPizza[i % coresPizza.length]);
            i++;
        }

        // Título melhorado
        graficoPizza.setTitle(new TextTitle("Estoque por Produto", 
            new Font("Segoe UI", Font.BOLD, 16)));
    }

    private void configurarGraficoBarra(JFreeChart graficoBarra) {
        CategoryPlot plotBarra = graficoBarra.getCategoryPlot();
        plotBarra.setBackgroundPaint(Color.WHITE);
        plotBarra.setRangeGridlinePaint(new Color(220, 220, 220));
        plotBarra.setDomainGridlinePaint(new Color(220, 220, 220));
        plotBarra.setOutlineVisible(false);

        // Customizar as barras
        BarRenderer renderer = (BarRenderer) plotBarra.getRenderer();
        renderer.setSeriesPaint(0, new Color(52, 152, 219));
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
        renderer.setShadowVisible(false);

        // Título melhorado
        graficoBarra.setTitle(new TextTitle("Distribuição do Estoque", 
            new Font("Segoe UI", Font.BOLD, 16)));
    }

    private JPanel criarCardModerno(String titulo, String valor, Color corPrincipal, String emoji) {
        @SuppressWarnings("serial")
		JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Sombra sutil
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.fillRoundRect(2, 2, getWidth() - 2, getHeight() - 2, 15, 15);
                
                // Fundo branco do card
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 15, 15);
                
                // Barra colorida no topo
                g2d.setColor(corPrincipal);
                g2d.fillRoundRect(0, 0, getWidth() - 2, 8, 15, 15);
                g2d.fillRect(0, 4, getWidth() - 2, 4);
            }
        };
        
        card.setLayout(new BorderLayout(10, 10));
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(200, 120));
        
        // Header com emoji e título
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        headerPanel.setOpaque(false);
        
        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        
        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tituloLabel.setForeground(new Color(70, 70, 70));
        
        headerPanel.add(emojiLabel);
        headerPanel.add(tituloLabel);
        
        // Valor principal
        JLabel valorLabel = new JLabel(valor);
        valorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valorLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valorLabel.setForeground(corPrincipal);
        
        card.add(headerPanel, BorderLayout.NORTH);
        card.add(valorLabel, BorderLayout.CENTER);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        return card;
    }

    @SuppressWarnings("serial")
	private JPanel criarContainerGrafico(ChartPanel chartPanel) {
        JPanel container = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Sombra para o container
                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fillRoundRect(2, 2, getWidth() - 2, getHeight() - 2, 10, 10);
                
                // Fundo branco
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 10, 10);
            }
        };
        
        container.setOpaque(false);
        container.add(chartPanel, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        return container;
    }

    @SuppressWarnings("unused")
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