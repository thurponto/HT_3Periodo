package org.example.view;

import org.example.dao.IdosoDAO;
import org.example.models.Idoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastroIdoso extends JFrame {
    private JTextField nomeField;
    private JButton salvarButton;

    public CadastroIdoso() {
        setTitle("Cadastro de Idoso");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245));

        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        nomeField = createStyledTextField();
        salvarButton = createStyledButton("Salvar");

        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(salvarButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();

                Idoso idoso = new Idoso();
                idoso.setNome(nome);

                IdosoDAO idosoDAO = new IdosoDAO();
                try {
                    idosoDAO.salvar(idoso);
                    JOptionPane.showMessageDialog(null, "Idoso cadastrado com sucesso!");
                    nomeField.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar idoso: " + ex.getMessage());
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        textField.setBackground(new Color(255, 255, 255));
        textField.setForeground(new Color(50, 50, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;

    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(15, 30, 15, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setUI(new BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setOpaque(false);
                button.setBorder(new EmptyBorder(10, 20, 10, 20));
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
                super.paint(g, c);
            }

            private void paintBackground(Graphics g, JComponent c, int yOffset) {
                Dimension size = c.getSize();
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground().darker());
                g2.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 20, 20);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 2, 20, 20);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            CadastroIdoso cadastroIdoso = new CadastroIdoso();
            cadastroIdoso.setVisible(true);
        });
    }
}
