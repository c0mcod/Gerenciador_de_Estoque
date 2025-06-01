package br.com.estoque.controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.estoque.produto.dao.ProdutoDAO;
import br.com.estoque.produto.model.Produto;

public class EstoqueVendaController {
	// criar janela de venda
	public void realizarVenda() {
		ProdutoDAO dao = new ProdutoDAO();
		
		JFrame frameVenda = new JFrame();
		frameVenda.setSize(800,600);
		frameVenda.setTitle("Painel de Venda");
		frameVenda.setDefaultCloseOperation(frameVenda.DISPOSE_ON_CLOSE);
		frameVenda.setLocationRelativeTo(null);
		
		//1° Painel: Painel de pesquisa de item
		JPanel pesquisarItem = new JPanel();
		JTextField pesquisarId = new JTextField();
		JButton buscar = new JButton("Buscar");
		JLabel resultId = new JLabel();
		
		buscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String idbd = pesquisarId.getText();
				int idBdInt = Integer.parseInt(idbd);
				
				Produto result = dao.buscarUmPorId(idBdInt);
				
				if (result != null) {
				    JOptionPane.showConfirmDialog(resultId, "Produto: "+ result.getNome() + "\nDeseja adicionar a compra?");
				    //Prosseguir com o metodo de adicionar produto por busca
				} else {
				    JOptionPane.showMessageDialog(null, "Produto com ID " + idBdInt + " não encontrado.");
				}

				
			}
		});

		
		pesquisarItem.add(pesquisarId);
		pesquisarItem.add(buscar);
		frameVenda.add(pesquisarItem);
		frameVenda.setLayout(new FlowLayout());
		
		
		frameVenda.setVisible(true);
	}

}
