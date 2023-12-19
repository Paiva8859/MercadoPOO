package com.projeto.Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.projeto.Connection.*;
import com.projeto.Model.*;
import com.projeto.View.LoginFuncionario;

public class FuncionariosControl {

    private List<Funcionario> funcionarios;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor da classe
    public FuncionariosControl(List<Funcionario> funcionarios, DefaultTableModel tableModel, JTable table) {
        this.funcionarios = funcionarios;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela com os dados dos funcionários
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        funcionarios = new FuncionariosDAO().listarTodos();
        for (Funcionario funcionario : funcionarios) {
            tableModel.addRow(new Object[]{funcionario.getNome(), funcionario.getCpf(),
                    funcionario.getdataNascimento(), funcionario.getTelefone()});
        }
    }

    // Método para cadastrar um novo funcionário
    public void cadastrar(String nome, String cpf, String telefone, String idade) {
        new FuncionariosDAO().cadastrar(nome, cpf, telefone, idade);
        atualizarTabela();
    }

    // Método para atualizar os dados de um funcionário
    public void atualizar(String cpf, String nome, String telefone, String idade) {
        new FuncionariosDAO().atualizar(cpf, nome, telefone, idade);
        atualizarTabela();
    }

    // Método para apagar um funcionário
    public void apagar(String cpf) {
        new FuncionariosDAO().apagar(cpf);
        atualizarTabela();
    }

    // Método para verificar se um CPF já existe
    public boolean verificarCPF(String cpf) {
        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
        if (funcionariosDAO.verificarCPFExistente(cpf)) {
            // Fecha a janela de login
            LoginFuncionario loginFuncionario = new LoginFuncionario();
            loginFuncionario.dispose();

            JOptionPane.showMessageDialog(null, "Login realizado");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "CPF não encontrado");
            return false;
        }
    }
}
