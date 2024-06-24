package org.example.view;

import org.example.dao.IdosoDAO;
import org.example.dao.VacinaAgendadaDAO;
import org.example.dao.VacinaDAO;
import org.example.models.Idoso;
import org.example.models.Vacina;
import org.example.models.VacinaAgendada;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AgendarVacina extends JFrame {
    private JComboBox<Idoso> idosoComboBox;
    private JComboBox<Vacina> vacinaComboBox;
    private JTextField dataField;
    private JButton agendarButton;

    public AgendarVacina() {
        setTitle("Agendar Vacina");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        idosoComboBox = new JComboBox<>();
        vacinaComboBox = new JComboBox<>();
        dataField = createStyledTextField("yyyy-MM-dd");
        agendarButton = createStyledButton("Agendar");

        try {
            List<Idoso> idosos = new IdosoDAO().listar();
            for (Idoso idoso : idosos) {
                idosoComboBox.addItem(idoso);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados dos idosos: " + e.getMessage());
        }

        try {
            List<Vacina> vacinas = new VacinaDAO().listar();
            for (Vacina vacina : vacinas) {
                vacinaComboBox.addItem(vacina);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados das vacinas: " + e.getMessage());
        }

        formPanel.add(new JLabel("Nome do Idoso:"));
        formPanel.add(idosoComboBox);
        formPanel.add(new JLabel("Vacina:"));
        formPanel.add(vacinaComboBox);
        formPanel.add(new JLabel("Data do Agendamento (yyyy-MM-dd):"));
        formPanel.add(dataField);
        formPanel.add(new JLabel(""));
        formPanel.add(agendarButton);

        add(formPanel, BorderLayout.CENTER);

        agendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agendarVacina();
            }
        });
    }

    private JTextField createStyledTextField(String defaultText) {
        JTextField textField = new JTextField(defaultText);
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
        button.setBackground(new Color(50, 150, 250));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
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

    private void agendarVacina() {
        Idoso idosoSelecionado = (Idoso) idosoComboBox.getSelectedItem();
        Vacina vacinaSelecionada = (Vacina) vacinaComboBox.getSelectedItem();
        String dataStr = dataField.getText();
        LocalDate dataAgendamento = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        VacinaAgendada vacinaAgendada = new VacinaAgendada();
        vacinaAgendada.setIdoso(idosoSelecionado);
        vacinaAgendada.setVacina(vacinaSelecionada);
        vacinaAgendada.setDataAgendamento(dataAgendamento);

        VacinaAgendadaDAO vacinaAgendadaDAO = new VacinaAgendadaDAO();
        try {
            vacinaAgendadaDAO.salvar(vacinaAgendada);
            JOptionPane.showMessageDialog(null, "Vacina agendada com sucesso!");
            dataField.setText("yyyy-MM-dd");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao agendar vacina: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            AgendarVacina agendarVacina = new AgendarVacina();
            agendarVacina.setVisible(true);
        });
    }
}