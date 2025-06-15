package br.com.estoque.vendas.controller;

import java.io.FileWriter;
import java.io.PrintWriter;

public class SalvarVendasCSV {
	public void salvarVendaCSV(Venda venda) {
	    try (PrintWriter writer = new PrintWriter(new FileWriter("vendas.csv", true))) {
	        writer.println("Hora,Venda Total");

	        for (ItemVenda item : venda.getItens()) {
	            writer.printf("%s,%d,%s,%d,%.2f,%.2f%n",
	                venda.getDataHora().toString(),
	                item.getCodigo(),
	                item.getNomeProduto(),
	                item.getQuantidade(),
	                item.getPrecoUnitario(),
	                item.getTotal()
	            );
	        }

	        writer.println("TOTAL GERAL: " + venda.getValorTotal());
	        writer.println("----------------------------------------");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
