package org.example.view;

import org.example.dao.AgendaDAO;
import org.example.dao.AgenteSaudeDAO;
import org.example.dao.IdosoDAO;
import org.example.models.Agenda;
import org.example.models.AgenteSaude;
import org.example.models.Idoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AgendamentoVisita extends JFrame {
    private JComboBox<AgenteSaude> agenteComboBox;
    private JComboBox<Idoso> idosoComboBox;
    private JTextField dataField;
    private JTextField horarioField;
    private JButton salvarButton;
    private JButton editarButton;
    private JButton cancelarButton;
    private JList<Agenda> agendaList;
    private DefaultListModel<Agenda> listModel;

    public AgendamentoVisita() {
        setTitle("Agendamento de Visita");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBackground(new Color(240, 240, 240));
        agenteComboBox = new JComboBox<>();
        idosoComboBox = new JComboBox<>();
        dataField = new JTextField();
        horarioField = new JTextField();
        salvarButton = createStyledButton("Salvar");
        editarButton = createStyledButton("Editar");
        cancelarButton = createStyledButton("Cancelar");

        try {
            List<AgenteSaude> agentes = new AgenteSaudeDAO().listar();
            for (AgenteSaude agente : agentes) {
                agenteComboBox.addItem(agente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados dos agentes de saúde: " + e.getMessage());
        }

        try {
            List<Idoso> idosos = new IdosoDAO().listar();
            for (Idoso idoso : idosos) {
                idosoComboBox.addItem(idoso);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados dos idosos: " + e.getMessage());
        }

        formPanel.add(new JLabel("Agente de Saúde:"));
        formPanel.add(agenteComboBox);
        formPanel.add(new JLabel("Idoso:"));
        formPanel.add(idosoComboBox);
        formPanel.add(new JLabel("Data (yyyy-MM-dd):"));
        formPanel.add(dataField);
        formPanel.add(new JLabel("Horário (HH:mm):"));
        formPanel.add(horarioField);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(salvarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(cancelarButton);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(new Color(240, 240, 240));
        listModel = new DefaultListModel<>();
        agendaList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(agendaList);
        listPanel.add(new JLabel("Agendas Agendadas:"), BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        add(listPanel, BorderLayout.SOUTH);

        try {
            refreshAgendaList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AgenteSaude agente = (AgenteSaude) agenteComboBox.getSelectedItem();
                    Idoso idoso = (Idoso) idosoComboBox.getSelectedItem();
                    java.sql.Date data = java.sql.Date.valueOf(dataField.getText());
                    String horario = horarioField.getText();

                    Agenda agenda = new Agenda();
                    agenda.setAgenteSaude(agente);
                    agenda.setIdoso(idoso);
                    agenda.setData(data);
                    agenda.setHorario(horario);

                    new AgendaDAO().salvar(agenda);
                    JOptionPane.showMessageDialog(null, "Visita agendada com sucesso!");
                    refreshAgendaList();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao agendar visita: " + ex.getMessage());
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agenda agendaSelecionada = agendaList.getSelectedValue();
                if (agendaSelecionada == null) {
                    JOptionPane.showMessageDialog(null, "Selecione uma visita para editar.");
                    return;
                }

                AgenteSaude novoAgente = (AgenteSaude) agenteComboBox.getSelectedItem();
                Idoso novoIdoso = (Idoso) idosoComboBox.getSelectedItem();
                String newData = dataField.getText();
                String newHorario = horarioField.getText();

                if (newData.isEmpty() || newHorario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos Data e Horário.");
                    return;
                }

                try {
                    agendaSelecionada.setAgenteSaude(novoAgente);
                    agendaSelecionada.setIdoso(novoIdoso);
                    agendaSelecionada.setData(java.sql.Date.valueOf(newData));
                    agendaSelecionada.setHorario(newHorario);
                    new AgendaDAO().editar(agendaSelecionada);
                    JOptionPane.showMessageDialog(null, "Visita editada com sucesso!");
                    refreshAgendaList();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar visita: " + ex.getMessage());
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agenda agendaSelecionada = agendaList.getSelectedValue();
                if (agendaSelecionada == null) {
                    JOptionPane.showMessageDialog(null, "Selecione uma visita para cancelar.");
                    return;
                }

                try {
                    new AgendaDAO().cancelar(agendaSelecionada.getId());
                    JOptionPane.showMessageDialog(null, "Visita cancelada com sucesso!");
                    refreshAgendaList();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cancelar visita: " + ex.getMessage());
                }
            }
        });
    }

    private void refreshAgendaList() throws SQLException {
        listModel.clear();
        List<Agenda> agendas = new AgendaDAO().listar();
        for (Agenda agenda : agendas) {
            listModel.addElement(agenda);
        }
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
            AgendamentoVisita agendamentoVisita = new AgendamentoVisita();
            agendamentoVisita.setVisible(true);
        });
    }
}
