package org.example;

import org.example.view.AgendamentoVisita;
import org.example.view.AgendarVacina;
import org.example.view.AlertasLembretes;
import org.example.view.CadastroUsuario;
import org.example.view.CadastroVacina;
import org.example.view.HistoricoSaude;
import org.example.view.CadastroIdoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Sistema de Saúde");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 15, 15)); // Adiciona espaço entre os botões
        getContentPane().setBackground(new Color(245, 245, 245)); // Define a cor de fundo da janela

        // Criação dos botões
        JButton cadastrarUsuarioButton = createStyledButton("Cadastrar Usuário");
        JButton historicoSaudeButton = createStyledButton("Histórico de Saúde");
        JButton agendarVisitaButton = createStyledButton("Agendar Visita");
        JButton alertasLembretesButton = createStyledButton("Alertas e Lembretes");
        JButton cadastrarIdosoButton = createStyledButton("Cadastrar Idoso");
        JButton cadastrarVacinaButton = createStyledButton("Cadastrar Vacina");
        JButton agendarVacinaButton = createStyledButton("Agendar Vacina");

        // Adiciona os botões à janela
        add(cadastrarUsuarioButton);
        add(historicoSaudeButton);
        add(agendarVisitaButton);
        add(alertasLembretesButton);
        add(cadastrarIdosoButton);
        add(cadastrarVacinaButton);
        add(agendarVacinaButton);

        // Define ações para cada botão
        cadastrarUsuarioButton.addActionListener(e -> abrirTelaCadastroUsuario());
        historicoSaudeButton.addActionListener(e -> abrirTelaHistoricoSaude());
        agendarVisitaButton.addActionListener(e -> abrirTelaAgendarVisita());
        alertasLembretesButton.addActionListener(e -> abrirTelaAlertasLembretes());
        cadastrarIdosoButton.addActionListener(e -> abrirTelaCadastroIdoso());
        cadastrarVacinaButton.addActionListener(e -> abrirTelaCadastroVacina());
        agendarVacinaButton.addActionListener(e -> abrirTelaAgendarVacina());

        // Ajusta o tamanho da janela e centraliza na tela
        pack();
        setLocationRelativeTo(null);
    }

    // Método para criar botões estilizados
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180)); // Azul aço
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(15, 30, 15, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Adiciona cantos arredondados
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

    // Métodos para abrir as telas correspondentes
    private void abrirTelaCadastroUsuario() {
        CadastroUsuario cadastroUsuario = new CadastroUsuario();
        cadastroUsuario.setVisible(true);
    }

    private void abrirTelaHistoricoSaude() {
        HistoricoSaude historicoSaude = new HistoricoSaude();
        historicoSaude.setVisible(true);
    }

    private void abrirTelaAgendarVisita() {
        AgendamentoVisita agendamentoVisita = new AgendamentoVisita();
        agendamentoVisita.setVisible(true);
    }

    private void abrirTelaAlertasLembretes() {
        AlertasLembretes alertasLembretes = new AlertasLembretes();
        alertasLembretes.setVisible(true);
    }

    private void abrirTelaCadastroIdoso() {
        CadastroIdoso cadastroIdoso = new CadastroIdoso();
        cadastroIdoso.setVisible(true);
    }

    private void abrirTelaCadastroVacina() {
        CadastroVacina cadastroVacina = new CadastroVacina();
        cadastroVacina.setVisible(true);
    }

    private void abrirTelaAgendarVacina() {
        AgendarVacina agendarVacina = new AgendarVacina();
        agendarVacina.setVisible(true);
    }

    // Método main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main telaPrincipal = new Main();
            telaPrincipal.setVisible(true);
        });
    }
}
