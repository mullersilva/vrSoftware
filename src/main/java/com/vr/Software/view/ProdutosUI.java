package com.vr.Software.view;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ProdutosUI extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;

    public ProdutosUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gerenciar Produtos", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setPreferredSize(new Dimension(0, 50));
        topPanel.add(titulo, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnBuscar = new JButton("Buscar");
        JButton btnCadastrar = new JButton("Cadastrar");
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnCadastrar);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Código", "Descrição", "Preço"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.decode("#f48f5a"));
        tableHeader.setForeground(Color.BLACK);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscarProdutos());
        btnCadastrar.addActionListener(e -> abrirCadastroProduto());
    }

    private void buscarProdutos() {
        try {
            URL url = new URL("http://localhost:8080/vr-api/product/get-all-products");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar produtos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            String jsonResponse = scanner.useDelimiter("\\A").next();
            scanner.close();
            conn.disconnect();

            atualizarTabela(jsonResponse);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro de conexão: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela(String jsonResponse) throws JSONException {
        tableModel.setRowCount(0);

        JSONArray jsonArray = new JSONArray(jsonResponse);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject produto = jsonArray.getJSONObject(i);
            int codigo = produto.getInt("codigo");
            String descricao = produto.getString("descricao");
            double preco = produto.getDouble("preco");

            tableModel.addRow(new Object[]{codigo, descricao, preco});
        }
    }

    private void abrirCadastroProduto() {
        CadastroProdutoUI cadastroProdutoUI = new CadastroProdutoUI();
        JFrame cadastroFrame = new JFrame("Cadastro de Produto");
        cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadastroFrame.setSize(400, 300);
        cadastroFrame.setLocationRelativeTo(this);
        cadastroFrame.add(cadastroProdutoUI);
        cadastroFrame.setVisible(true);
    }
}
