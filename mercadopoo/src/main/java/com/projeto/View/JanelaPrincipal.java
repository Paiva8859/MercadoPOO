package com.projeto.View;

import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        super("Mercado POO");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(650, 650);
        
        
        JTabbedPane abas = new JTabbedPane();
        abas.add("Caixa", new Caixa());
        abas.add("Estoque", new JanelaEstoque()); 
        abas.add("Vendas", new JanelaVendas()); 
        abas.add("Clientes VIP", new JanelaCadastroCliente());
        abas.add("Funcionários", new JanelaCadastroFuncionario()); 
        add(abas);
        

        
        addWindowListener(new WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?",
                        "Mercado POO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(2);
                }
            } 

        });
    }
}