package com.projeto.Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.projeto.Connection.*;
import com.projeto.Model.*;

public class VendasControl {
    private List<Venda> vendas;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor da classe
    public VendasControl(List<Venda> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela com os dados das vendas
    public void atualizarTabela() {
        tableModel.setRowCount(0);
        vendas = new VendasDAO().listarTodos();
        for (Venda venda : vendas) {
            tableModel.addRow(new Object[]{venda.getData(), venda.getCliente(), venda.getQuantidadeDeProdutos(),
                    venda.getPagamento(), venda.getValor()});
        }
    }

    // Método para realizar uma venda
    public void realizarVenda(int id, String cliente, String quantidadeDeProdutos, String valor, String data,
            String pagamento) {
        try {
            // Cria um objeto Venda com os dados fornecidos
            Venda venda = new Venda(id, cliente.trim().toUpperCase(), valor.trim(), data.trim(),
                    quantidadeDeProdutos.trim(), pagamento.trim());

            // Adiciona a venda à lista de vendas
            vendas.add(venda);

            // Chama o método cadastrar do VendasDAO para armazenar a venda no banco de dados
            new VendasDAO().cadastrar(cliente.trim().toUpperCase(), quantidadeDeProdutos.trim(), valor.trim(),
                    data.trim(), pagamento.trim());

            // Atualiza a tabela com os dados das vendas
            atualizarTabela();

            // Exibe uma mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!");
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Verifique se os dados escritos estão corretos e tente novamente", "ERRO!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
