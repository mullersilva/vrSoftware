package com.vr.Software.view;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientesUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public ClientesUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gerenciar Clientes", JLabel.CENTER);
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

        String[] columnNames = {"Codigo","Nome", "Limite de Compra", "Dia Fechamento Fatura"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JTableHeader tableHeader = table.getTableHeader();
        table.getTableHeader().setBackground(Color.decode("#f48f5a"));
        tableHeader.setForeground(Color.BLACK);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscarClientes());
        btnCadastrar.addActionListener(e -> abrirCadastroCliente());
    }

    private void buscarClientes() {
        try {
            String urlString = "http://localhost:8080/vr-api/client/get-all-clients";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar clientes!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            conn.disconnect();

            JSONArray jsonArray = new JSONArray(response.toString());
            atualizarTabela(jsonArray);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void atualizarTabela(JSONArray clientes) throws JSONException {
        tableModel.setRowCount(0);

        for (int i = 0; i < clientes.length(); i++) {
            JSONObject cliente = clientes.getJSONObject(i);
            tableModel.addRow(new Object[]{
                    cliente.getInt("codigo"),
                    cliente.getString("nome"),
                    cliente.getDouble("limiteCompra"),
                    cliente.getInt("diaFechamentoFatura")
            });
        }
    }

    private void abrirCadastroCliente() {
        CadastroClienteUI cadastroClienteUI = new CadastroClienteUI();
        JFrame cadastroFrame = new JFrame("Cadastro de Cliente");
        cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadastroFrame.setSize(450, 300);
        cadastroFrame.setLocationRelativeTo(this);
        cadastroFrame.add(cadastroClienteUI);
        cadastroFrame.setVisible(true);
    }
}
