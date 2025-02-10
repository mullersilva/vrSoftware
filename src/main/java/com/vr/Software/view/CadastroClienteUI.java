package com.vr.Software.view;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CadastroClienteUI extends JPanel {
    private final JTextField tfNome;
    private final JTextField tfLimiteCompra;
    private final JTextField tfDiaFechamento;

    public CadastroClienteUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Dimension labelSize = new Dimension(170, 20);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setPreferredSize(labelSize);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblNome, gbc);

        tfNome = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfNome, gbc);

        JLabel lblLimiteCompra = new JLabel("Limite de Compra:");
        lblLimiteCompra.setPreferredSize(labelSize);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblLimiteCompra, gbc);

        tfLimiteCompra = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfLimiteCompra, gbc);

        JLabel lblDiaFechamento = new JLabel("Dia de Fechamento da Fatura:");
        lblDiaFechamento.setPreferredSize(labelSize);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblDiaFechamento, gbc);

        tfDiaFechamento = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfDiaFechamento, gbc);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarCliente());

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSalvar, gbc);
    }

    private void salvarCliente() {
        String nome = tfNome.getText();
        String limiteCompraStr = tfLimiteCompra.getText();
        String diaFechamentoStr = tfDiaFechamento.getText();

        if (nome.isEmpty() || limiteCompraStr.isEmpty() || diaFechamentoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double limiteCompra = Double.parseDouble(limiteCompraStr);
            int diaFechamento = Integer.parseInt(diaFechamentoStr);

            JSONObject cliente = new JSONObject();
            cliente.put("nome", nome);
            cliente.put("limiteCompra", limiteCompra);
            cliente.put("diaFechamentoFatura", diaFechamento);

            enviarParaAPI(cliente);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para Limite de Compra e Dia de Fechamento!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void enviarParaAPI(JSONObject cliente) {
        try {
            String urlString = "http://localhost:8080/vr-api/client/persist-client";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = cliente.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 201) {
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            conn.disconnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao enviar dados para a API: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        tfNome.setText("");
        tfLimiteCompra.setText("");
        tfDiaFechamento.setText("");
    }
}