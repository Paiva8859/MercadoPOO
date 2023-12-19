package com.projeto.Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.projeto.Model.*;
import com.projeto.Connection.*;

public class ClientesControl {

    private List<Cliente> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor da classe
    public ClientesControl(List<Cliente> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela com os dados dos clientes
    private void atualizarTabela() {
        if (tableModel != null && table != null) {
            // Limpa as linhas existentes na tabela
            tableModel.setRowCount(0);

            // Obtém a lista atualizada de clientes do banco de dados
            clientes = new ClientesDAO().listarTodos();

            // Preenche a tabela com os dados dos clientes
            for (Cliente cliente : clientes) {
                tableModel.addRow(new Object[]{cliente.getNome(), cliente.getCpf(), cliente.getdataNascimento(),
                        cliente.getTelefone()});
            }
        } else {
            System.out.println("Erro ao atualizar tabela");
        }
    }

    // Método para cadastrar um novo cliente
    public void cadastrar(String nome, String cpf, String telefone, String dataNascimento) {
        // Chama o método cadastrar do ClienteDAO para inserir um novo cliente no banco de dados
        new ClientesDAO().cadastrar(nome, cpf, telefone, dataNascimento);

        // Atualiza a tabela após cadastrar um novo cliente
        atualizarTabela();
    }

    // Método para atualizar os dados de um cliente existente
    public void atualizar(String nome, String cpf, String telefone, String dataNascimento) {
        // Chama o método atualizar do ClienteDAO para modificar os dados de um cliente no banco de dados
        new ClientesDAO().atualizar(nome, cpf, telefone, dataNascimento);

        // Atualiza a tabela após realizar a atualização dos dados do cliente
        atualizarTabela();
    }

    // Método para apagar um cliente pelo CPF
    public void apagar(String cpf) {
        // Chama o método apagar do ClienteDAO para remover um cliente do banco de dados
        new ClientesDAO().apagar(cpf);

        // Atualiza a tabela após apagar um cliente
        atualizarTabela();
    }

    // Método para verificar se um CPF já existe no banco de dados
    public boolean verificarCPF(String cpf) {
        ClientesDAO clientesDAO = new ClientesDAO();
        if (clientesDAO.verificarCPFExistente(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF encontrado");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "CPF não encontrado");
            return false;
        }
    }
}
