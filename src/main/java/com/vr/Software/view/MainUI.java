package com.vr.Software.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {

    private static JPanel cards;
    private static CardLayout cardLayout;

    public static void startGUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tela Inicial");
            frame.setSize(1200, 1000);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JMenuBar menuBar = new JMenuBar();

            JMenu menuNavegacao = new JMenu("Menu");
            menuNavegacao.setFont(new Font("Arial", Font.BOLD, 20));
            menuBar.add(menuNavegacao);

            JMenuItem clientesMenuItem = new JMenuItem("Clientes");
            clientesMenuItem.setFont(new Font("Arial", Font.BOLD, 15));

            JMenuItem produtosMenuItem = new JMenuItem("Produtos");
            produtosMenuItem.setFont(new Font("Arial", Font.BOLD, 15));

            menuNavegacao.add(clientesMenuItem);
            menuNavegacao.add(produtosMenuItem);

            cardLayout = new CardLayout();
            cards = new JPanel(cardLayout);

            JPanel initialPanel = new JPanel();
            initialPanel.add(new JLabel("Bem-vindo à VR Software", SwingConstants.CENTER));

            JPanel clientesPanel = new ClientesUI();

            JPanel produtosPanel = new ProdutosUI();

            cards.add(initialPanel, "Home");
            cards.add(clientesPanel, "Clientes");
            cards.add(produtosPanel, "Produtos");

            clientesMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cards, "Clientes");
                }
            });
            produtosMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cards, "Produtos");
                }
            });

            frame.setJMenuBar(menuBar);

            frame.add(cards, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
