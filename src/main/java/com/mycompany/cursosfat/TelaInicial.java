/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cursosfat;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author nunnes
 */
public class TelaInicial extends JFrame {

    public TelaInicial() {
        super("Sistema Cursos FAT");
        setLayout(new BorderLayout());

        // LOGO (você pode usar um JLabel com imagem se quiser)
        JLabel logo = new JLabel("CURSOS FAT", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 32));
        logo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(logo, BorderLayout.NORTH);

        // Botões principais
        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton botaoCadastro = new JButton("Cadastrar Aluno");
        JButton botaoConsultar = new JButton("Consultar Alunos");
        JButton botaoDeletar = new JButton("Deletar Aluno");
        JButton botaoSair = new JButton("Fechar Sistema");

        painelBotoes.add(botaoCadastro);
        painelBotoes.add(botaoConsultar);
        painelBotoes.add(botaoDeletar);
        painelBotoes.add(botaoSair);

        add(painelBotoes, BorderLayout.CENTER);

        // Ações dos botões
        botaoCadastro.addActionListener(e -> new TelaCadastro());
        botaoConsultar.addActionListener(e -> new TelaConsulta());
        botaoDeletar.addActionListener(e -> new TelaDeletar());
        botaoSair.addActionListener(e -> System.exit(0));

        // Configurações da janela
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial());
    }
}

