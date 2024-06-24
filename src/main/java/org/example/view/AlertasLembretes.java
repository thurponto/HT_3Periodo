package org.example.view;

import org.example.dao.VacinaAgendadaDAO;
import org.example.models.VacinaAgendada;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AlertasLembretes extends JFrame {
    private JTextArea alertasArea;

    public AlertasLembretes() {
        setTitle("Alertas e Lembretes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245));

        alertasArea = createStyledTextArea();
        JScrollPane scrollPane = createStyledScrollPane(alertasArea);

        add(scrollPane, BorderLayout.CENTER);

        carregarAlertas();
    }

    private JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(new Color(50, 50, 50));
        return textArea;
    }

    private JScrollPane createStyledScrollPane(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(new Color(245, 245, 245));

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbHighlightColor = new Color(70, 130, 180);
                this.thumbLightShadowColor = new Color(70, 130, 180);
                this.thumbDarkShadowColor = new Color(70, 130, 180);
                this.thumbColor = new Color(70, 130, 180);
                this.trackColor = new Color(230, 230, 230);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        return scrollPane;
    }

    private void carregarAlertas() {
        VacinaAgendadaDAO vacinaAgendadaDAO = new VacinaAgendadaDAO();
        try {
            List<VacinaAgendada> vacinasAgendadas = vacinaAgendadaDAO.listarTodas();
            StringBuilder alertasTexto = new StringBuilder();
            for (VacinaAgendada vacina : vacinasAgendadas) {
                alertasTexto.append("Idoso: ").append(vacina.getIdoso().getNome()).append("\n");
                alertasTexto.append("Vacina: ").append(vacina.getVacina().getNome()).append("\n");
                alertasTexto.append("Data: ").append(vacina.getDataAgendamento()).append("\n");
                alertasTexto.append("\n");
            }
            alertasArea.setText(alertasTexto.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar alertas: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            AlertasLembretes alertasLembretes = new AlertasLembretes();
            alertasLembretes.setVisible(true);
        });
    }
}
