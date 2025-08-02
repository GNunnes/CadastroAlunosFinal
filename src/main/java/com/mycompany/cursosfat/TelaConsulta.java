package com.mycompany.cursosfat;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TelaConsulta extends JFrame {
    private JTextArea areaTexto;

    public TelaConsulta() {
        super("Consultar Alunos");
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        add(scroll, BorderLayout.CENTER);

        listarAlunos();

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void listarAlunos() {
        try (Connection conexao = Conexao.getConexao()) {
            String sql = "SELECT * FROM Alunos";
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String linha = rs.getInt("id") + " - " + rs.getString("nome") + " " + rs.getString("sobrenome");
                areaTexto.append(linha + "\n");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar alunos: " + e.getMessage());
        }
    }
}
