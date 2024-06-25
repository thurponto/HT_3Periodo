package org.example.view;

import org.example.dao.VacinaDAO;
import org.example.models.Vacina;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastroVacina extends JFrame {
    private JTextField nomeField;
    private JButton cadastrarButton;

    public CadastroVacina() {
        setTitle("Cadastro de Vacina");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245));

        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        nomeField = createStyledTextField();
        cadastrarButton = createStyledButton("Cadastrar");

        formPanel.add(new JLabel("Nome da Vacina:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel(""));
        formPanel.add(cadastrarButton);

        add(formPanel, BorderLayout.CENTER);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarVacina();
            }
        });
    }

    private void cadastrarVacina() {
        String nome = nomeField.getText();
        if (!nome.isEmpty()) {
            Vacina vacina = new Vacina();
            vacina.setNome(nome);
            VacinaDAO vacinaDAO = new VacinaDAO();
            try {
                vacinaDAO.salvar(vacina);
                JOptionPane.showMessageDialog(null, "Vacina cadastrada com sucesso!");
                nomeField.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar vacina: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, insira o nome da vacina.");
        }
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
            CadastroVacina cadastroVacina = new CadastroVacina();
            cadastroVacina.setVisible(true);
        });
    }
}
