package br.com.estoque.vendas.controller;

public class ItemVenda {
	private int codigo;
	private String nomeProduto;
	private int quantidade;
	private double precoUnitario;
	private double total;
	
	public ItemVenda(int codigo, String nomeProduto, int quantidade, double precoUnitario, double total) {
		this.codigo = codigo;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.total = total;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
