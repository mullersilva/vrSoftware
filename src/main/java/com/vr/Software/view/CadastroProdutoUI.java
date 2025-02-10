package com.vr.Software.view;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroProdutoUI extends JPanel {
    private JTextField tfDescricao;
    private JTextField tfPreco;

    public CadastroProdutoUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblDescricao = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblDescricao, gbc);

        tfDescricao = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfDescricao, gbc);

        JLabel lblPreco = new JLabel("Preço:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblPreco, gbc);

        tfPreco = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfPreco, gbc);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setPreferredSize(new Dimension(150, 40));
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.addActionListener(e -> salvarProduto());

        JPanel panelButton = new JPanel();
        panelButton.add(btnSalvar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelButton, gbc);
    }

    private void salvarProduto() {
        String descricao = tfDescricao.getText();
        String precoStr = tfPreco.getText();

        if (descricao.isEmpty() || precoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);

            JSONObject produto = new JSONObject();
            produto.put("descricao", descricao);
            produto.put("preco", preco);

            enviarParaAPI(produto);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um preço válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void enviarParaAPI(JSONObject produto) {
        try {
            String urlString = "http://localhost:8080/vr-api/product/persist-product";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = produto.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 201) {
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            conn.disconnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao enviar dados para a API: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        tfDescricao.setText("");
        tfPreco.setText("");
    }
}
