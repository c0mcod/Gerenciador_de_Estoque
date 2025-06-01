package br.com.estoque.produto.model;

public class Produto {
	private int id;
	private int id_fornecedor;
	private String nome;
	private String preco;
	private int quantidade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(int id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String toString() {
        return "Produto id= " + id + "\nnome=" + nome + "\nquantidade=" + quantidade + "\npreco=" + preco + "\nid_fornecedor=" + id_fornecedor;
    }

}
