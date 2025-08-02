package com.mycompany.cursosfat;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaDeletar extends JFrame {
    private JTextField campoId;
    private JButton botaoExcluir;

    public TelaDeletar() {
        super("Deletar Aluno");
        setLayout(new BorderLayout());

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        painel.add(new JLabel("ID do Aluno:"));
        campoId = new JTextField();
        painel.add(campoId);

        botaoExcluir = new JButton("Excluir");
        painel.add(new JLabel()); // espaço vazio
        painel.add(botaoExcluir);

        add(painel, BorderLayout.CENTER);

        botaoExcluir.addActionListener(e -> excluirAluno());

        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void excluirAluno() {
        String idTexto = campoId.getText();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID do aluno.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            try (Connection conn = Conexao.getConexao()) {
                String sql = "DELETE FROM Alunos WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(this, "Aluno deletado com sucesso!");
                    campoId.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar aluno: " + e.getMessage());
        }
    }
}
