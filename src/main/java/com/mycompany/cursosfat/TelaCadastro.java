package com.mycompany.cursosfat;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastro extends JFrame {
    private JTextField campoNome;
    private JTextField campoSobrenome;
    private JButton botaoSalvar;

    public TelaCadastro() {
        super("Cadastrar Aluno");
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelCampos.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelCampos.add(campoNome);

        painelCampos.add(new JLabel("Sobrenome:"));
        campoSobrenome = new JTextField();
        painelCampos.add(campoSobrenome);

        botaoSalvar = new JButton("Salvar");
        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoSalvar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);

        botaoSalvar.addActionListener(e -> salvarAluno());

        setSize(300, 180);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void salvarAluno() {
        String nome = campoNome.getText();
        String sobrenome = campoSobrenome.getText();

        if (nome.isEmpty() || sobrenome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try (Connection conn = Conexao.getConexao()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Erro: não foi possível conectar ao banco de dados.");
                return;
            }

            String sql = "INSERT INTO Alunos (nome, sobrenome) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
            campoNome.setText("");
            campoSobrenome.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
}