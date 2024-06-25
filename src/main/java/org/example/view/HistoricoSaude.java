package org.example.view;

import org.example.dao.AgendaDAO;
import org.example.dao.HistoricoDAO;
import org.example.dao.IdosoDAO;
import org.example.dao.VacinaDAO;
import org.example.models.Agenda;
import org.example.models.Historico;
import org.example.models.Idoso;
import org.example.models.Vacina;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class HistoricoSaude extends JFrame {
    private JComboBox<Idoso> idosoComboBox;
    private JComboBox<Vacina> vacinaComboBox;
    private JComboBox<Agenda> agendaComboBox;
    private JTextField alergiasField;
    private JTextField condicoesField;
    private JTextField observacoesField;
    private JButton salvarButton;
    private JTextArea historicoArea;

    public HistoricoSaude() {
        setTitle("Histórico de Saúde do Idoso");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245));

        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        historicoArea = new JTextArea();
        historicoArea.setEditable(false);
        historicoArea.setBackground(new Color(255, 255, 255));
        historicoArea.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        salvarButton = createStyledButton("Salvar");
        alergiasField = createStyledTextField();
        condicoesField = createStyledTextField();
        observacoesField = createStyledTextField();

        try {
            List<Idoso> idosos = new IdosoDAO().listar();
            List<Vacina> vacinas = new VacinaDAO().listar();
            List<Agenda> agendas = new AgendaDAO().listar();

            idosoComboBox = new JComboBox<>(idosos.toArray(new Idoso[0]));
            vacinaComboBox = new JComboBox<>(vacinas.toArray(new Vacina[0]));
            agendaComboBox = new JComboBox<>(agendas.toArray(new Agenda[0]));

            inputPanel.add(new JLabel("Idoso:"));
            inputPanel.add(idosoComboBox);
            inputPanel.add(new JLabel("Vacina:"));
            inputPanel.add(vacinaComboBox);
            inputPanel.add(new JLabel("Agenda:"));
            inputPanel.add(agendaComboBox);
            inputPanel.add(new JLabel("Alergias:"));
            inputPanel.add(alergiasField);
            inputPanel.add(new JLabel("Condições Médicas:"));
            inputPanel.add(condicoesField);
            inputPanel.add(new JLabel("Observações:"));
            inputPanel.add(observacoesField);
            inputPanel.add(new JLabel(""));
            inputPanel.add(salvarButton);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Historico historico = new Historico();
                historico.setIdoso((Idoso) idosoComboBox.getSelectedItem());
                historico.setVacina((Vacina) vacinaComboBox.getSelectedItem());
                historico.setAgenda((Agenda) agendaComboBox.getSelectedItem());
                historico.setAlergias(alergiasField.getText());
                historico.setCondicoesMedicas(condicoesField.getText());
                historico.setObservacoes(observacoesField.getText());

                HistoricoDAO historicoDAO = new HistoricoDAO();
                try {
                    historicoDAO.salvar(historico);
                    JOptionPane.showMessageDialog(null, "Histórico salvo com sucesso!");
                    carregarHistorico();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar histórico: " + ex.getMessage());
                }
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(historicoArea), BorderLayout.CENTER);

        carregarHistorico();
    }

    private void carregarHistorico() {
        historicoArea.setText("");
        HistoricoDAO historicoDAO = new HistoricoDAO();
        try {
            List<Historico> historicos = historicoDAO.listar();
            for (Historico historico : historicos) {
                historicoArea.append(historico.toString() + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar histórico: " + e.getMessage());
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
            HistoricoSaude historicoSaude = new HistoricoSaude();
            historicoSaude.setVisible(true);
        });
    }
}
