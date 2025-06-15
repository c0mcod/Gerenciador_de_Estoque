package br.com.estoque.vendas.controller;

import java.time.LocalDateTime;
import java.util.List;

public class Venda {
	private List<ItemVenda> itens;
	private double valorTotal;
	private LocalDateTime dataHora;
	
	public Venda(List<ItemVenda> itens, double valorTotal)  {
		this.itens = itens;
		this.valorTotal = valorTotal;
		this.dataHora = LocalDateTime.now();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
}
