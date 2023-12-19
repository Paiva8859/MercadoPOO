package com.projeto.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.projeto.Connection.ClientesDAO;
import com.projeto.Controller.ClientesControl;
import com.projeto.Model.Cliente;


public class JanelaCadastra extends JFrame {
    
    private JButton cadastrar;
    private JTextField clienteNomeField, clienteCpfField, clienteTelefoneField, clienteDataNascimentoField;
    private List<Cliente> clientes;

    
    public JanelaCadastra() {
        super();

        
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nome"));
        clienteNomeField = new JTextField(20);
        inputPanel.add(clienteNomeField);
        inputPanel.add(new JLabel("CPF"));
        clienteCpfField = new JTextField(20);
        inputPanel.add(clienteCpfField);
        inputPanel.add(new JLabel("Data de nascimento (dd/mm/aaaa)"));
        clienteDataNascimentoField = new JTextField(20);
        inputPanel.add(clienteDataNascimentoField);
        inputPanel.add(new JLabel("Telefone"));
        clienteTelefoneField = new JTextField(20);
        inputPanel.add(clienteTelefoneField);

        JPanel botoes = new JPanel();
        cadastrar = new JButton("Cadastrar");
        botoes.add(cadastrar);

        
        setLayout(new BorderLayout(8, 8));
        add(inputPanel, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);

        new ClientesDAO().criaTabela();

        

        
        ClientesControl operacoesClientes = new ClientesControl(clientes, null, null);

        
        cadastrar.addActionListener(e -> {
            
            operacoesClientes.cadastrar(
                    clienteNomeField.getText(),
                    clienteCpfField.getText(),
                    clienteTelefoneField.getText(),
                    clienteDataNascimentoField.getText());
            
            cadastroVip();
        });
    }

     
    public void cadastroVip() {
        
        dispose();
        
        JFrame janela = new JFrame();
        janela.setSize(500, 500);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(2);
        janela.setLocationRelativeTo(null);
         
        janela.add(new JanelaProdutos());
        
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso", "Mercado POO",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
