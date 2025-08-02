package com.mycompany.cursosfat;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class TelaAtualizar extends JFrame {
    private JTextField campoId;
    private JTextField campoNovoNome;
    private JTextField campoNovoSobrenome;
    private JButton botaoAtualizar;

    public TelaAtualizar() {
        super("Atualizar Aluno");
        setLayout(new BorderLayout());

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        painel.add(new JLabel("ID do Aluno:"));
        campoId = new JTextField();
        painel.add(campoId);

        painel.add(new JLabel("Novo Nome:"));
        campoNovoNome = new JTextField();
        painel.add(campoNovoNome);

        painel.add(new JLabel("Novo Sobrenome:"));
        campoNovoSobrenome = new JTextField();
        painel.add(campoNovoSobrenome);

        botaoAtualizar = new JButton("Atualizar");
        painel.add(new JLabel()); // espaço vazio
        painel.add(botaoAtualizar);

        add(painel, BorderLayout.CENTER);

        botaoAtualizar.addActionListener(e -> atualizarAluno());

        setSize(350, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void atualizarAluno() {
        String idTexto = campoId.getText();
        String novoNome = campoNovoNome.getText();
        String novoSobrenome = campoNovoSobrenome.getText();

        if (idTexto.isEmpty() || novoNome.isEmpty() || novoSobrenome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            try (Connection conn = Conexao.getConexao()) {
                String sql = "UPDATE Alunos SET nome = ?, sobrenome = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, novoNome);
                stmt.setString(2, novoSobrenome);
                stmt.setInt(3, id);
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
                    campoId.setText("");
                    campoNovoNome.setText("");
                    campoNovoSobrenome.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar aluno: " + e.getMessage());
        }
    }
}
